package com.imed.medcare.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.imed.medcare.R;


/**
 * Created by ismael on 24-12-17.
 */

public class LoaderDialog {

    static MaterialDialog progressDialog;

    public static void ShowProgressDialog(String message, Activity activity) {
        progressDialog = new MaterialDialog.Builder(activity)
                .title(message)
                .progress(true, 0)
                .widgetColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                .progressIndeterminateStyle(true)
                .theme(Theme.LIGHT)
                .build();
        progressDialog.show();
    }

    public static void DismissProgressDialog(){
        if (progressDialog.isShowing()) progressDialog.dismiss();
    }
}
