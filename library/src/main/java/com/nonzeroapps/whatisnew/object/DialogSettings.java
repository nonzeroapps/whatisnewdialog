package com.nonzeroapps.whatisnew.object;

import com.nonzeroapps.whatisnew.R;

/**
 * Created by berkayturanci on 02/08/2017.
 */

public class DialogSettings {

    private int titleResId = R.string.rate_dialog_title;
    private int messageResId = R.string.rate_dialog_message;
    private int textPositiveResId = R.string.rate_dialog_ok;
    private int textNeutralResId = R.string.rate_dialog_cancel;
    private int textNegativeResId = R.string.rate_dialog_no;

    private boolean showNeutralButton = true;
    private boolean showNegativeButton = true;
    private boolean showTitle = true;
    private boolean cancelable = false;

    private String titleText = null;
    private String positiveText = null;
    private String neutralText = null;
    private String negativeText = null;

    private String dialogTitle;
    private int close
}
