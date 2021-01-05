package com.cj.trainticks.utils;

import android.app.Activity;
import android.app.Dialog;

import com.cj.trainticks.R;

public class Helpers {
    public static Dialog showLoadingDialog(Activity activity){
        Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_loading_dialog);
        return dialog;
    }
}
