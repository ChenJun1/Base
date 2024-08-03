package com.base.kiap.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.base.kiap.R;

import java.util.ArrayList;

public class PasswordInputView extends LinearLayout {
    private ArrayList<EditText> editTexts = new ArrayList<>();

    public PasswordInputView(Context context) {
        super(context);
        init(context);
    }

    public PasswordInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswordInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_password_input, this, true);
        setOrientation(HORIZONTAL);

        editTexts.add(findViewById(R.id.edit_text_1));
        editTexts.add(findViewById(R.id.edit_text_2));
        editTexts.add(findViewById(R.id.edit_text_3));
        editTexts.add(findViewById(R.id.edit_text_4));
        editTexts.add(findViewById(R.id.edit_text_5));
        editTexts.add(findViewById(R.id.edit_text_6));

        for (int i = 0; i < editTexts.size(); i++) {
            final int index = i;
            EditText editText = editTexts.get(i);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1 && index < 5) {
                        editTexts.get(index + 1).requestFocus();
                    } else if (s.length() == 0 && index > 0) {
                        editTexts.get(index - 1).requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }

    public String getPassword() {
        StringBuilder stringBuilder = new StringBuilder();
        for (EditText editText : editTexts) {
            stringBuilder.append(editText.getText().toString());
        }
        return stringBuilder.toString();
    }

    public void clearPassword() {
        for (EditText editText : editTexts) {
            editText.setText("");
        }
        editTexts.get(0).requestFocus();
    }
}
