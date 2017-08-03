package com.nonzeroapps.whatisnew.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by berkayturanci on 03/08/2017.
 */

final public class SharedPrefHelper {
    private static final String SHARED_PREF_FILE_NAME = "what_is_new_dialog_pref_file";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getPreferencesEditor(Context context) {
        return getPreferences(context).edit();
    }

    /**
     * Clear data in shared preferences.<br/>
     *
     * @param context context
     */
    public static void clearSharedPreferences(Context context) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.clear();
        editor.apply();
    }

    public static void setSeenBefore(Context context, String versionName, boolean isSeenBefore) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putBoolean(versionName, isSeenBefore);
        editor.apply();
    }

    public static boolean isSeenBefore(Context context, String versionName) {
        return getPreferences(context).getBoolean(versionName, false);
    }

}
