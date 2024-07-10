package com.mobielwa.diki.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/21/20 3:39 PM
 * @Description: Order Detail
 */
public class TeamDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, TeamDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_team_detail;
    }

    @Override
    protected void initData() {
        tvTitle.setText("Detail");
        initImmersionBar();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
