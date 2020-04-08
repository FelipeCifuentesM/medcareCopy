package com.imed.medcare.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.imed.medcare.R;

public class ErrorDialog {


    public static void ShowErrorDialog(Activity activity, String errorText){
        new MaterialDialog.Builder(activity)
                .title(R.string.error_title_text)
                .content(errorText)
                .titleColor(ContextCompat.getColor(activity, R.color.text_color_primary))
                .positiveText(R.string.ok_text)
                .positiveColor(ContextCompat.getColor(activity, R.color.text_color_primary))
                .theme(Theme.LIGHT)
                .show();
    }
}
