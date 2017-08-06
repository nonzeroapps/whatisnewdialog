package com.nonzeroapps.whatisnew.util;

import android.graphics.Color;
import android.support.annotation.ColorInt;

/**
 * Created by berkayturanci on 04/08/2017.
 */

public class Util {

    @ColorInt
    public static int getContrastColor(@ColorInt int color) {
        // Counting the perceptive luminance - human eye favors green color...
        double a = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return a < 0.5 ? Color.BLACK : Color.WHITE;
    }
}
