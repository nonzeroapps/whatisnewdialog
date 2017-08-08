package com.nonzeroapps.whatisnew.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.nonzeroapps.whatisnew.R;
import com.nonzeroapps.whatisnew.adapter.ImageViewPagerAdapter;
import com.nonzeroapps.whatisnew.listener.OnSetImageListener;
import com.nonzeroapps.whatisnew.object.DialogSettings;
import com.nonzeroapps.whatisnew.object.NewFeatureItem;
import com.nonzeroapps.whatisnew.util.SharedPrefHelper;
import com.nonzeroapps.whatisnew.view.InkPageIndicator;
import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;

import java.util.ArrayList;

import static com.nonzeroapps.whatisnew.R.id.viewPager;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public class WhatIsNewDialogFragment extends DialogFragment {

    private static final String NEW_FEATURE_ITEM_LIST = "newFeatureItemList";
    private static final String DIALOG_SETTINGS = "dialogSettings";

    private ViewPager mImageViewPager;
    private InkPageIndicator mInkPageIndicator;
    private ArrayList<NewFeatureItem> mNewFeatureItemArrayList;
    private DialogInterface.OnClickListener mPositiveButtonListener;
    private DialogInterface.OnClickListener mNeutralButtonListener;

    public static WhatIsNewDialogFragment newInstance(ArrayList<NewFeatureItem> newFeatureItemArrayList, DialogSettings dialogSettings, DialogInterface.OnClickListener positiveButtonListener, DialogInterface.OnClickListener neutralButtonListener) {
        WhatIsNewDialogFragment whatIsNewDialogFragment = new WhatIsNewDialogFragment();

        whatIsNewDialogFragment.setPositiveButtonListener(positiveButtonListener);
        whatIsNewDialogFragment.setNeutralButtonListener(neutralButtonListener);

        Bundle args = new Bundle();
        args.putParcelableArrayList(NEW_FEATURE_ITEM_LIST, newFeatureItemArrayList);
        args.putParcelable(DIALOG_SETTINGS, dialogSettings);
        whatIsNewDialogFragment.setArguments(args);

        return whatIsNewDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Context context = getContext();

        View view = getActivity().getLayoutInflater().inflate(R.layout.newfeaturedialog, null);

        mNewFeatureItemArrayList = getArguments().getParcelableArrayList(NEW_FEATURE_ITEM_LIST);
        final DialogSettings dialogSettings = getArguments().getParcelable(DIALOG_SETTINGS);
        mImageViewPager = (ViewPager) view.findViewById(viewPager);
        mInkPageIndicator = (InkPageIndicator) view.findViewById(R.id.indicator);
        mImageViewPager.setPageTransformer(false, new ParallaxPagerTransformer(R.id.imageView));

        initPage();

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view);

        if (dialogSettings != null) {
            if (dialogSettings.isShowTitle()) {
                builder.setTitle(dialogSettings.getTitleText(context));
            }

            if (dialogSettings.isShowPositiveButton()) {
                builder.setPositiveButton(dialogSettings.getPositiveText(context), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPrefHelper.setSeenBefore(context, dialogSettings.getVersionName(context), true);

                        if (mPositiveButtonListener != null) {
                            mPositiveButtonListener.onClick(dialog, which);
                        }
                    }
                });
            }

            if (dialogSettings.isShowNeutralButton()) {
                builder.setNeutralButton(dialogSettings.getNeutralText(context), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (mNeutralButtonListener != null) {
                            mNeutralButtonListener.onClick(dialog, which);
                        }
                    }
                });
            }

            builder.setCancelable(dialogSettings.isCancelable());
        }

        return builder.create();
    }

    private void initPage() {
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(getContext(), new OnSetImageListener() {
            @Override
            public void setImage(ImageView imageView, int position) {

            }
        }, mNewFeatureItemArrayList);

        mImageViewPager.setAdapter(adapter);
        mInkPageIndicator.setViewPager(mImageViewPager);
    }

    public void setPositiveButtonListener(DialogInterface.OnClickListener positiveButtonListener) {
        mPositiveButtonListener = positiveButtonListener;
    }

    public void setNeutralButtonListener(DialogInterface.OnClickListener neutralButtonListener) {
        mNeutralButtonListener = neutralButtonListener;
    }
}
