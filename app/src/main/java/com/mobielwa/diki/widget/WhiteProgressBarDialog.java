package com.mobielwa.diki.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.mobielwa.diki.R;

public class WhiteProgressBarDialog extends Dialog {
    public ProgressBar bar;

    public WhiteProgressBarDialog(Context context) {
        super(context, R.style.DialogStyle);
        View view = getLayoutInflater().inflate(R.layout.progress_dialog_view_bar, null);
        setContentView(view);
        bar = view.findViewById(R.id.bar_progress);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
