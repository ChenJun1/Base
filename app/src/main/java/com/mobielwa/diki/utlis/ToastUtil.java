package com.mobielwa.diki.utlis;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.mobielwa.diki.R;
import com.mobielwa.diki.config.AppConfig;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import es.dmoral.toasty.Toasty;

/**
 * @Author: June
 * @CreateDate: 12/9/20 2:03 PM
 * @Description: Toast封装
 */
public class ToastUtil {
    public static void normal(@NonNull String str) {
        Toasty.normal(AppConfig.INSTANCE.getApplication(), str).show();
    }

    public static void success(@NonNull String str) {
        Toasty.success(AppConfig.INSTANCE.getApplication(), str).show();
    }

    public static void error(@NonNull String str) {
        Toasty.error(AppConfig.INSTANCE.getApplication(), str).show();
    }

    public static void normal(@StringRes int str) {
        Toasty.normal(AppConfig.INSTANCE.getApplication(), str).show();
    }

    public static void success(@StringRes int str) {
        Toasty.success(AppConfig.INSTANCE.getApplication(), str).show();
    }

    public static void error(@StringRes int str) {
        Toasty.error(AppConfig.INSTANCE.getApplication(), str).show();
    }

    public static void showToast(Context context) {
        // 自定义土司显示位置
        // 创建土司
        Toast toast = new Toast(context);
        // 找到toast布局的位置
        View layout = View.inflate(context, R.layout.tose_layout, null);
        // 设置toast文本，把设置好的布局传进来
        toast.setView(layout);
        // 设置土司显示在屏幕的位置
        toast.setGravity(Gravity.CENTER,0,0);
        // 显示土司
        toast.show();
    }

}
