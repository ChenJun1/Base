package com.base.kiap.utlis;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

import com.base.kiap.R;


public class DialogManager {
    public static AlertDialog newTransDialog(Context context, View view, boolean isScance) {
        AlertDialog dialog = new KasDialog.Builder(context, R.style.style_bg_transparent_dialog)
                .setView(view)
                .setCancelable(isScance).create();
        Window window = dialog.getWindow();
//        if (window != null) {
//            dialog.getWindow().setWindowAnimations(R.style.AnimTheme);
//            WindowManager.LayoutParams params = window.getAttributes();
//            params.width = ViewGroup.LayoutParams.MATCH_PARENT;//使用这种方式更改了dialog的框宽
//            window.setAttributes(params);
//        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
        return dialog;
    }
}
