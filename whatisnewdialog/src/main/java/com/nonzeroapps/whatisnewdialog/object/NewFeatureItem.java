package com.nonzeroapps.whatisnewdialog.object;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public class NewFeatureItem implements Parcelable {

    private String featureTitle;
    private String featureDesc;
    private String imageResource;
    private int imageDrawableResource;

    public String getFeatureTitle() {
        return featureTitle;
    }

    public void setFeatureTitle(String featureTitle) {
        this.featureTitle = featureTitle;
    }

    public String getFeatureDesc() {
        return featureDesc;
    }

    public void setFeatureDesc(String featureDesc) {
        this.featureDesc = featureDesc;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public void setImageResource(@DrawableRes int imageResource) {
        imageDrawableResource = imageResource;
    }

    public int getImageDrawableResource() {
        return imageDrawableResource;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.featureTitle);
        dest.writeString(this.featureDesc);
        dest.writeString(this.imageResource);
        dest.writeInt(this.imageDrawableResource);
    }

    public NewFeatureItem() {
    }

    protected NewFeatureItem(Parcel in) {
        this.featureTitle = in.readString();
        this.featureDesc = in.readString();
        this.imageResource = in.readString();
        this.imageDrawableResource = in.readInt();
    }

    public static final Parcelable.Creator<NewFeatureItem> CREATOR = new Parcelable.Creator<NewFeatureItem>() {
        @Override
        public NewFeatureItem createFromParcel(Parcel source) {
            return new NewFeatureItem(source);
        }

        @Override
        public NewFeatureItem[] newArray(int size) {
            return new NewFeatureItem[size];
        }
    };
}
