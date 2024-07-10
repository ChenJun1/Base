package com.mobielwa.diki.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jacksen.taggroup.ITag;
import com.jacksen.taggroup.OnTagClickListener;
import com.jacksen.taggroup.SuperTagGroup;
import com.jacksen.taggroup.SuperTagView;
import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BaseMvpActivity;
import com.mobielwa.diki.bean.ApplyBean;
import com.mobielwa.diki.bean.ApplyChannBean;
import com.mobielwa.diki.bean.DialogBean;
import com.mobielwa.diki.bean.request.RechargeRequestBean;
import com.mobielwa.diki.config.ConfigDate;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.listen.onItemClickListen;
import com.mobielwa.diki.mvp.iview.IRechargeView;
import com.mobielwa.diki.mvp.presenter.RechargePresenter;
import com.mobielwa.diki.utlis.CommUtils;
import com.mobielwa.diki.utlis.DialogUtlis;
import com.mobielwa.diki.utlis.StringFormatUtils;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.ArrayList;
import java.util.List;

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
