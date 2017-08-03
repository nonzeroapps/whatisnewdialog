package com.nonzeroapps.whatisnew.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by berkayturanci on 01/08/2017.
 */

public class NewFeatureItem implements Parcelable {

    private String featureTitle;
    private String featureDesc;
    private String imageUrl;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.featureTitle);
        dest.writeString(this.featureDesc);
        dest.writeString(this.imageUrl);
    }

    public NewFeatureItem() {
    }

    protected NewFeatureItem(Parcel in) {
        this.featureTitle = in.readString();
        this.featureDesc = in.readString();
        this.imageUrl = in.readString();
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
