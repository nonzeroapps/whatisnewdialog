package com.example.whatisnew;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.whatisnew.fragment.WhatIsNewDialogFragment;
import com.example.whatisnew.object.NewFeatureItem;

import java.util.ArrayList;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public class NewItemDialog {

    private static volatile NewItemDialog mNewItemDialog;

    private AppCompatActivity mActivity;
    private ArrayList<NewFeatureItem> mNewFeatureItemArrayList;

    public static NewItemDialog init(@NonNull AppCompatActivity activity) {
        if (mNewItemDialog == null) {
            mNewItemDialog = new NewItemDialog(activity);
        }

        return mNewItemDialog;
    }

    private NewItemDialog(@NonNull AppCompatActivity activity) {
        mActivity = activity;
    }

    public NewItemDialog setItems(ArrayList<NewFeatureItem> newFeatureItemArraylist) {
        mNewFeatureItemArrayList = newFeatureItemArraylist;
        return this;
    }

    public void showDialog() {

        try {
            WhatIsNewDialogFragment newFragment = WhatIsNewDialogFragment.newInstance(mNewFeatureItemArrayList);

            FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
            transaction.add(newFragment, "dialog");
            transaction.commitAllowingStateLoss();
        } catch (IllegalStateException ex) {

        }
    }

}
