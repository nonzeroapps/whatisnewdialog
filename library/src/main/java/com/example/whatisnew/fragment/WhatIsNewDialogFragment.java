package com.example.whatisnew.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.example.whatisnew.R;
import com.example.whatisnew.adapter.ImageViewPagerAdapter;
import com.example.whatisnew.listener.OnSetImageListener;
import com.example.whatisnew.object.NewFeatureItem;

import java.util.ArrayList;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public class WhatIsNewDialogFragment extends DialogFragment {

    private static final String NEW_FEATURE_ITEM_LIST = "newFeatureItemList";
    private static final String DIALOG_TITLE = "dialogTitle";

    private ViewPager mImageViewPager;
    private ArrayList<NewFeatureItem> mNewFeatureItemArrayList;
    private String mDialogTitle;

    public static WhatIsNewDialogFragment newInstance(ArrayList<NewFeatureItem> newFeatureItemArrayList, String dialogTitle) {
        WhatIsNewDialogFragment f = new WhatIsNewDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putParcelableArrayList(NEW_FEATURE_ITEM_LIST, newFeatureItemArrayList);
        args.putString(DIALOG_TITLE, dialogTitle);
        f.setArguments(args);

        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.newfeaturedialog, null);

        mNewFeatureItemArrayList = getArguments().getParcelableArrayList(NEW_FEATURE_ITEM_LIST);
        mDialogTitle = getArguments().getString(DIALOG_TITLE);
        mImageViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        initPage();

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(mDialogTitle)
                .setPositiveButton("Kapat", null)
                .setNegativeButton("Sonra Tekrar Göster", null)
                .create();
        return alertDialog;
    }

    private void initPage() {
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(getContext(), new OnSetImageListener() {
            @Override
            public void setImage(ImageView imageView, int position) {

            }
        }, mNewFeatureItemArrayList);

        mImageViewPager.setAdapter(adapter);
    }
}
