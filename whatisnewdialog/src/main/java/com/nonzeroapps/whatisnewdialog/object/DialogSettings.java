package com.nonzeroapps.whatisnewdialog.object;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import com.nonzeroapps.whatisnewdialog.R;


/**
 * Created by berkayturanci on 02/08/2017.
 */

final public class DialogSettings implements Parcelable {

    private int versionNameId = R.string.version_name;
    private int titleResId = R.string.new_features_dialog_title;
    private int textPositiveResId = R.string.close;
    private int textNeutralResId = R.string.remind_me_later;

    private boolean usePaletteForDescBackground = true;
    private boolean usePaletteForImageBackground = true;
    private boolean showNeutralButton = true;
    private boolean showPositiveButton = true;
    private boolean showTitle = true;
    private boolean cancelable = true;

    private String versionName = null;
    private String titleText = null;
    private String positiveText = null;
    private String neutralText = null;

    public int getTitleResId() {
        return titleResId;
    }

    public void setTitleResId(int titleResId) {
        this.titleResId = titleResId;
    }

    public int getTextPositiveResId() {
        return textPositiveResId;
    }

    public void setTextPositiveResId(int textPositiveResId) {
        this.textPositiveResId = textPositiveResId;
    }

    public int getTextNeutralResId() {
        return textNeutralResId;
    }

    public void setTextNeutralResId(int textNeutralResId) {
        this.textNeutralResId = textNeutralResId;
    }

    public boolean usePaletteForDescBackground() {
        return usePaletteForDescBackground;
    }

    public void setUsePaletteForDescBackground(boolean usePaletteForDescBackground) {
        this.usePaletteForDescBackground = usePaletteForDescBackground;
    }

    public boolean usePaletteForImageBackground() {
        return usePaletteForImageBackground;
    }

    public void setUsePaletteForImageBackground(boolean usePaletteForImageBackground) {
        this.usePaletteForImageBackground = usePaletteForImageBackground;
    }

    public boolean isShowNeutralButton() {
        return showNeutralButton;
    }

    public void setShowNeutralButton(boolean showNeutralButton) {
        this.showNeutralButton = showNeutralButton;
    }

    public boolean isShowPositiveButton() {
        return showPositiveButton;
    }

    public void setShowPositiveButton(boolean showPositiveButton) {
        this.showPositiveButton = showPositiveButton;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public String getTitleText(@NonNull Context context) {
        if (titleText == null) {
            titleText = context.getString(titleResId);
        }
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getPositiveText(@NonNull Context context) {
        if (positiveText == null) {
            positiveText = context.getString(textPositiveResId);
        }
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String getNeutralText(@NonNull Context context) {
        if (neutralText == null) {
            neutralText = context.getString(textNeutralResId);
        }
        return neutralText;
    }

    public void setNeutralText(String neutralText) {
        this.neutralText = neutralText;
    }

    public String getVersionName(@NonNull Context context) {
        if (versionName == null) {
            versionName = context.getString(versionNameId);
        }
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.versionName);
        dest.writeInt(this.titleResId);
        dest.writeInt(this.textPositiveResId);
        dest.writeInt(this.textNeutralResId);
        dest.writeByte(this.usePaletteForDescBackground ? (byte) 1 : (byte) 0);
        dest.writeByte(this.usePaletteForImageBackground ? (byte) 1 : (byte) 0);
        dest.writeByte(this.showNeutralButton ? (byte) 1 : (byte) 0);
        dest.writeByte(this.showPositiveButton ? (byte) 1 : (byte) 0);
        dest.writeByte(this.showTitle ? (byte) 1 : (byte) 0);
        dest.writeByte(this.cancelable ? (byte) 1 : (byte) 0);
        dest.writeString(this.titleText);
        dest.writeString(this.positiveText);
        dest.writeString(this.neutralText);
    }

    public DialogSettings() {
    }

    protected DialogSettings(Parcel in) {
        this.versionName = in.readString();
        this.titleResId = in.readInt();
        this.textPositiveResId = in.readInt();
        this.textNeutralResId = in.readInt();
        this.usePaletteForDescBackground = in.readByte() != 0;
        this.usePaletteForImageBackground = in.readByte() != 0;
        this.showNeutralButton = in.readByte() != 0;
        this.showPositiveButton = in.readByte() != 0;
        this.showTitle = in.readByte() != 0;
        this.cancelable = in.readByte() != 0;
        this.titleText = in.readString();
        this.positiveText = in.readString();
        this.neutralText = in.readString();
    }

    public static final Parcelable.Creator<DialogSettings> CREATOR = new Parcelable.Creator<DialogSettings>() {
        @Override
        public DialogSettings createFromParcel(Parcel source) {
            return new DialogSettings(source);
        }

        @Override
        public DialogSettings[] newArray(int size) {
            return new DialogSettings[size];
        }
    };
}
