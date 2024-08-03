package com.base.kiap.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/21/20 3:39 PM
 * @Description: FAQ
 */
public class HelpActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, HelpActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_help;
    }

    @Override
    protected void initData() {
        tvTitle.setText("FAQ");
    }

    @OnClick({R.id.iv_back, R.id.rl_help1,R.id.rl_help2,R.id.rl_help3})
    public void onViewClicked(View view) {
        if (mClickHelper.click()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_help1:
                HelpDetailActivity.start(this);
                break;
            case R.id.rl_help2:
                HelpDetailActivity.start(this);
                break;
            case R.id.rl_help3:
                HelpDetailActivity.start(this);
                break;
        }
    }

}
