package com.example.whatisnew.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.whatisnew.R;
import com.example.whatisnew.listener.OnItemClickListener;
import com.example.whatisnew.listener.OnSetImageListener;
import com.example.whatisnew.object.NewFeatureItem;

import java.util.ArrayList;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public class ImageViewPagerAdapter extends ViewPagerAdapter {

    private Context mContext;
    private OnItemClickListener mOnPagerItemClick;
    private OnSetImageListener mOnSetImageListener;
    private ArrayList<NewFeatureItem> mNewFeatureItems;
    int finalHeight, finalWidth;

    public ImageViewPagerAdapter(Context context, @NonNull OnSetImageListener onSetImageListener, ArrayList<NewFeatureItem> newFeatureItems) {
        mContext = context;
        mOnSetImageListener = onSetImageListener;
        mNewFeatureItems = newFeatureItems;
    }

    @Override
    public View getItem(final int position) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_pager_image, null);

        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
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


        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                finalHeight = imageView.getMeasuredHeight();
                finalWidth = imageView.getMeasuredWidth();

                Glide.with(mContext)
                        .load(newFeatureItem.getImageUrl())
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

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
                                            imageView.setBackgroundColor(palette.getDominantColor(whiteColor));
                                            linearLayout.setBackgroundColor(palette.getDominantColor(whiteColor));

                                            Palette.Swatch swatch = palette.getMutedSwatch();

                                            if (swatch != null) {
                                                textViewTitle.setTextColor(swatch.getTitleTextColor());
                                                textViewDesc.setTextColor(swatch.getBodyTextColor());
                                            }
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
