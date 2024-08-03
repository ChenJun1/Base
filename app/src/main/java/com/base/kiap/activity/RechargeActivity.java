package com.base.kiap.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.ApplyBean;
import com.base.kiap.mvp.iview.IRechargeView;
import com.base.kiap.mvp.presenter.RechargePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/25/20 4:13 PM
 * @Description: 充值
 */
public class RechargeActivity extends BaseMvpActivity<IRechargeView, RechargePresenter> implements IRechargeView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RechargeActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_recharge;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.str_pay_usdt_sell_title);
        initImmersionBar();
    }

    @Override
    protected RechargePresenter createPresenter() {
        return new RechargePresenter();
    }


    @OnClick({R.id.iv_back, R.id.bt_save_pic})
    public void onViewClicked(View view) {
        if (mClickHelper.click()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_save_pic:
                RechargeUsdtActivity.start(this);
                break;
        }
    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }

    @Override
    public void onSuccess(ApplyBean bean) {

    }
}
