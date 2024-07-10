package com.mobielwa.diki.utlis;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

/**
 * @Author: June
 * @CreateDate: 2020-05-08 17:56
 * @Description: java类作用描述
 */
public class KasDialog extends AlertDialog {
    public View mInflateView;

    protected KasDialog(Context context) {
        super(context);
    }

    protected KasDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected KasDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public View getmInflateView() {
        return mInflateView;
    }

    public void setmInflateView(View mInflateView) {
        this.mInflateView = mInflateView;
    }


}
