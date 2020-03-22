package com.nonzeroapps.whatisnewdialog.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nonzeroapps.whatisnewdialog.R;
import com.nonzeroapps.whatisnewdialog.object.NewFeatureItem;
import com.nonzeroapps.whatisnewdialog.util.Util;

import java.util.ArrayList;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public class ImageViewPagerAdapter extends ViewPagerAdapter {

    private Context mContext;
    private ArrayList<NewFeatureItem> mNewFeatureItems;
    private int finalHeight, finalWidth;
    private boolean mUsePaletteForDescBackground, mUsePaletteForImageBackground;

    public ImageViewPagerAdapter(Context context, ArrayList<NewFeatureItem> newFeatureItems,
                                 boolean usePaletteForDescBackground,
                                 boolean usePaletteForImageBackground) {
        mContext = context;
        mNewFeatureItems = newFeatureItems;
        mUsePaletteForDescBackground = usePaletteForDescBackground;
        mUsePaletteForImageBackground = usePaletteForImageBackground;
    }

    @Override
    public View getItem(final int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_pager_image, null);

        final LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        final ImageView imageView = view.findViewById(R.id.imageView);
        final View progress = view.findViewById(R.id.progress);
        final TextView textViewDesc = view.findViewById(R.id.textViewDesc);
        final TextView textViewTitle = view.findViewById(R.id.textViewTitle);

        final NewFeatureItem newFeatureItem = mNewFeatureItems.get(position);
        textViewDesc.setText(newFeatureItem.getFeatureDesc());
        textViewTitle.setText(newFeatureItem.getFeatureTitle());
        imageView.setContentDescription(newFeatureItem.getFeatureTitle());

        progress.setVisibility(View.VISIBLE);
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                finalHeight = imageView.getMeasuredHeight();
                finalWidth = imageView.getMeasuredWidth();

                boolean isGif = newFeatureItem.getImageResource() != null && newFeatureItem.getImageResource().toLowerCase().endsWith(".gif");

                RequestManager requestManager = Glide.with(mContext);
                RequestOptions requestOptions = new RequestOptions()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true);

                if (finalHeight != 0 && finalWidth != 0) {
                    requestOptions = requestOptions.override(finalWidth, finalHeight);
                }

                if (isGif) {
                    RequestBuilder<GifDrawable> drawableTypeRequest = requestManager
                            .setDefaultRequestOptions(requestOptions)
                            .asGif()
                            .load(newFeatureItem.getImageResource())
                            .listener(new RequestListener<GifDrawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                                    progress.setVisibility(View.GONE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                                    progress.setVisibility(View.GONE);
                                    Bitmap bitmap = resource.getFirstFrame();
                                    putBackgroundColors(bitmap, imageView, linearLayout, textViewTitle, textViewDesc);
                                    return false;
                                }
                            });

                    drawableTypeRequest.into(imageView);
                } else {
                    RequestBuilder<Drawable> drawableTypeRequest;

                    if (newFeatureItem.getImageResource() == null) {
                        drawableTypeRequest = requestManager.setDefaultRequestOptions(requestOptions).load(newFeatureItem.getImageDrawableResource());
                    } else {
                        drawableTypeRequest = requestManager.setDefaultRequestOptions(requestOptions).load(newFeatureItem.getImageResource());
                    }
                    drawableTypeRequest = drawableTypeRequest
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    progress.setVisibility(View.GONE);
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    progress.setVisibility(View.GONE);
                                    Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                    putBackgroundColors(bitmap, imageView, linearLayout, textViewTitle, textViewDesc);
                                    return false;
                                }
                            });

                    drawableTypeRequest.into(imageView);
                }
                return true;
            }
        });

        return view;
    }


    @Override
    public int getCount() {
        return mNewFeatureItems.size();
    }

    private void putBackgroundColors(Bitmap bitmap, final ImageView imageView, final LinearLayout linearLayout, final TextView textViewTitle, final TextView textViewDesc) {
        if (bitmap != null && !bitmap.isRecycled()) {

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    int whiteColor = mContext.getResources().getColor(android.R.color.white);
                    int blackColor = mContext.getResources().getColor(android.R.color.black);
                    int dominantColor = palette.getDominantColor(whiteColor);
                    int contrastColor = Util.getContrastColor(palette.getDominantColor(whiteColor));

                    ObjectAnimator colorAnim = ObjectAnimator.ofInt(imageView, "backgroundColor", whiteColor, dominantColor);
                    colorAnim.setEvaluator(new ArgbEvaluator());
                    colorAnim.setDuration(500)
                            .start();

                    ObjectAnimator colorAnim2 = ObjectAnimator.ofInt(linearLayout, "backgroundColor", whiteColor, dominantColor);
                    colorAnim2.setEvaluator(new ArgbEvaluator());
                    colorAnim2.setDuration(500)
                            .start();

                    ObjectAnimator colorAnim3 = ObjectAnimator.ofInt(textViewTitle, "textColor", blackColor, contrastColor);
                    colorAnim3.setEvaluator(new ArgbEvaluator());
                    colorAnim3.setDuration(500)
                            .start();

                    ObjectAnimator colorAnim4 = ObjectAnimator.ofInt(textViewDesc, "textColor", blackColor, contrastColor);
                    colorAnim4.setEvaluator(new ArgbEvaluator());
                    colorAnim4.setDuration(500)
                            .start();
                }
            });
        }
    }
}
