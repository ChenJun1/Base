package com.base.kiap.utlis;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

/**
 * @Author: June
 * @CreateDate: 12/25/20 11:00 AM
 * @Description: java类作用描述
 */
public class DialogFactory {
    //基本信息填写时的基本弹框
    //基本信息填写时的基本弹框
    public static void createAlertDialog(final Context context, String[] strArray, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(strArray, listener);
        builder.create().show();
    }
}
