package com.nonzeroapps.whatisnew;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nonzeroapps.whatisnew.fragment.WhatIsNewDialogFragment;
import com.nonzeroapps.whatisnew.object.DialogSettings;
import com.nonzeroapps.whatisnew.object.NewFeatureItem;
import com.nonzeroapps.whatisnew.util.SharedPrefHelper;

import java.util.ArrayList;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public final class NewItemDialog {

    private static volatile NewItemDialog mNewItemDialog;

    private ArrayList<NewFeatureItem> mNewFeatureItemArrayList;
    private DialogSettings mDialogSettings;
    private DialogInterface.OnClickListener mPositiveButtonListener;
    private DialogInterface.OnClickListener mNegativeButtonListener;
    private Context mContext;

    public static NewItemDialog init(@NonNull Context context) {
        if (mNewItemDialog == null) {
            synchronized (NewItemDialog.class) {
                if (mNewItemDialog == null) {
                    mNewItemDialog = new NewItemDialog(context);
                }
            }
        }

        return mNewItemDialog;
    }

    private NewItemDialog(@NonNull Context context) {
        mContext = context;
        mDialogSettings = new DialogSettings();
    }

    public NewItemDialog setDialogTitle(String dialogTitle) {
        mDialogSettings.setTitleText(dialogTitle);
        return this;
    }

    public NewItemDialog setPositiveButtonTitle(String positiveButtonTitle) {
        mDialogSettings.setPositiveText(positiveButtonTitle);
        return this;
    }

    public NewItemDialog setNeutralButtonTitle(String neutralButtonTitle) {
        mDialogSettings.setNeutralText(neutralButtonTitle);
        return this;
    }

    public NewItemDialog setShowLaterButton(boolean isShowNeutralButton) {
        mDialogSettings.setShowNeutralButton(isShowNeutralButton);
        return this;
    }

    public NewItemDialog setCancelButton(boolean isShowCancelButton) {
        mDialogSettings.setShowPositiveButton(isShowCancelButton);
        return this;
    }

    public NewItemDialog setCancelable(boolean cancelable) {
        mDialogSettings.setCancelable(cancelable);
        return this;
    }

    public NewItemDialog setVersionName(String versionName) {
        mDialogSettings.setVersionName(versionName);
        return this;
    }

    public NewItemDialog setCancelButtonListener(DialogInterface.OnClickListener cancelButtonListener) {
        this.mPositiveButtonListener = cancelButtonListener;
        return this;
    }

    public NewItemDialog setShowLaterButtonListener(DialogInterface.OnClickListener showLaterButtonListener) {
        this.mNegativeButtonListener = showLaterButtonListener;
        return this;
    }

    public NewItemDialog setItems(ArrayList<NewFeatureItem> newFeatureItemArraylist) {
        mNewFeatureItemArrayList = newFeatureItemArraylist;
        return this;
    }

    public void clearSharedPref() {
        SharedPrefHelper.clearSharedPreferences(mContext);
    }

    public boolean isConditionsSuitable() {
        return !SharedPrefHelper.isSeenBefore(mContext, mDialogSettings.getVersionName(mContext));
    }

    public void showDialogIfConditionsSuitable(@NonNull AppCompatActivity activity) {

        if (activity.isFinishing()) {
            return;
        }

        if (isConditionsSuitable()) {
            showDialog(activity);
        }
    }

    public void showDialog(@NonNull AppCompatActivity activity) {
        try {
            WhatIsNewDialogFragment newFragment = WhatIsNewDialogFragment.newInstance(mNewFeatureItemArrayList, mDialogSettings, mPositiveButtonListener, mNegativeButtonListener);

            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.add(newFragment, "dialog");
            transaction.commitAllowingStateLoss();
        } catch (IllegalStateException ex) {

        }
    }

}
