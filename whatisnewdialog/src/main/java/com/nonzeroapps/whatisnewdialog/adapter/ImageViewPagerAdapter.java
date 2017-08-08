package com.nonzeroapps.whatisnewdialog.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nonzeroapps.whatisnewdialog.R;
import com.nonzeroapps.whatisnewdialog.listener.OnItemClickListener;
import com.nonzeroapps.whatisnewdialog.listener.OnSetImageListener;
import com.nonzeroapps.whatisnewdialog.object.NewFeatureItem;
import com.nonzeroapps.whatisnewdialog.util.Util;

import java.util.ArrayList;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public class ImageViewPagerAdapter extends ViewPagerAdapter {

    private Context mContext;
    private OnItemClickListener mOnPagerItemClick;
    private OnSetImageListener mOnSetImageListener;
    private ArrayList<NewFeatureItem> mNewFeatureItems;
    private View mBackground;
    private int finalHeight, finalWidth;


    public ImageViewPagerAdapter(Context context, @NonNull OnSetImageListener onSetImageListener, ArrayList<NewFeatureItem> newFeatureItems) {
        mContext = context;
        mOnSetImageListener = onSetImageListener;
        mNewFeatureItems = newFeatureItems;
    }

    @Override
    public View getItem(final int position) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_pager_image, null);

        final ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollViewTextHolder);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        final View progress = view.findViewById(R.id.progress);
        final TextView textViewDesc = (TextView) view.findViewById(R.id.textViewDesc);
        final TextView textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);

        final NewFeatureItem newFeatureItem = mNewFeatureItems.get(position);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnPagerItemClick != null) {
//                    mOnPagerItemClick.onPagerItemClick(v, position);
//                }
//            }
//        });


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


                RequestManager requestManager = Glide.with(mContext);

                DrawableTypeRequest drawableTypeRequest;
                if (newFeatureItem.getImageResource() == null) {
                    drawableTypeRequest = requestManager.load(newFeatureItem.getImageDrawableResource());
                } else {
                    drawableTypeRequest = requestManager.load(newFeatureItem.getImageResource());
                }
                drawableTypeRequest
                        .listener(new RequestListener<Object, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, Object model, Target<GlideDrawable> target, boolean isFirstResource) {
                                progress.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, Object model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                progress.setVisibility(View.GONE);
                                Bitmap bitmap;

                                if (resource instanceof GifDrawable) {
                                    bitmap = ((GifDrawable) resource).getFirstFrame();
                                } else {
                                    bitmap = ((GlideBitmapDrawable) resource).getBitmap();
                                }

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

                                return false;
                            }
                        })
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .fitCenter()
                        .override(finalWidth, finalHeight)
                        .into(imageView);

                return true;
            }
        });


//        if (mOnSetImageListener != null) {
//
//            mOnSetImageListener.setImage(imageView, position);
//        } else {
//
//            imageView.setImageResource(mImageResources[position]);
//        }
        return view;
    }


    @Override
    public int getCount() {
        return mNewFeatureItems.size();
    }
}
