package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.activity.HelpDetailActivity;
import com.base.kiap.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, UserInfoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_user_info;
    }

    @Override
    protected void initData() {
        tvTitle.setText("User Info");
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
                break;
            case R.id.rl_help2:
                break;
            case R.id.rl_help3:
                break;
        }
    }

}
