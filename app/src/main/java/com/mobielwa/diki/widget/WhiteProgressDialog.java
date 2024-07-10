package com.mobielwa.diki.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.mobielwa.diki.R;

public class WhiteProgressDialog extends Dialog {

    public WhiteProgressDialog(Context context) {
        super(context, R.style.DialogStyle);
        View view = getLayoutInflater().inflate(R.layout.progress_dialog_view, null);
        setContentView(view);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
