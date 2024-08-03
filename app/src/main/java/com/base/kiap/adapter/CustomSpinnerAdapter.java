package com.base.kiap.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] data;

    public CustomSpinnerAdapter(Context context, int resource, String[] data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);

        TextView textView = row.findViewById(android.R.id.text1);
        textView.setText(data[position]);

        // 设置选中时和未选中时的字体颜色
        if (position == 0) { // 例如第一个位置选中蓝色，其他位置未选中灰色
            textView.setTextColor(Color.BLUE);
        } else {
            textView.setTextColor(Color.GRAY);
        }

        return row;
    }
}

