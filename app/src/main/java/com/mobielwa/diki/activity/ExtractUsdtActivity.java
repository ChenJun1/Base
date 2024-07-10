package com.mobielwa.diki.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BaseMvpActivity;
import com.mobielwa.diki.bean.PayoutGetBean;
import com.mobielwa.diki.bean.event.ConfigEvent;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.mvp.iview.IExtractView;
import com.mobielwa.diki.mvp.presenter.ExtractPresenter;
import com.mobielwa.diki.utlis.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/29/20 10:56 AM
 * @Description: 提现
 */
public class ExtractUsdtActivity extends BaseMvpActivity<IExtractView, ExtractPresenter> implements IExtractView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.tv_c_money)
    TextView tvCMoney;
    @BindView(R.id.et_usdt)
    EditText et_usdt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ExtractUsdtActivity.class);
        context.startActivity(starter);
    }

    private int price;//选择的金额
    private float balance;//余额
    private long rate = 0;
    private String pass;//密码
    private int type=1;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_extract_usdt;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        initViewTV();

        type = 1;

        tvTitle.setText(R.string.str_tixian);
        balance = UserHelp.getBalance();
        tvBalance.setText(UserHelp.getBalance()/10000D+"");
        addPriceTag();

        getPresenter().getWithdrawalInfo();
    }

    private void initViewTV() {

        SpannableString et1;
        if (UserHelp.getPayPassword().isEmpty()) {
            et1 = new SpannableString(getString(R.string.str_extra_ac_4_1));//定义hint的值
        } else {
            et1 = new SpannableString(getString(R.string.str_extra_ac_4));//定义hint的值
        }
        AbsoluteSizeSpan ass1 = new AbsoluteSizeSpan(14, true);//设置字体大小 true表示单位是sp
        et1.setSpan(ass1, 0, et1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        etPass.setHint(new SpannedString(et1));

    }

    private void initView() {
        if (UserHelp.getPayPassword().isEmpty()) {
            etPass.setClickable(true);
            etPass.setFocusableInTouchMode(false);
            etPass.setOnClickListener(v -> SettingPassActivity.start(ExtractUsdtActivity.this));
        } else {
            etPass.setClickable(false);
            etPass.setFocusableInTouchMode(true);
            etPass.setOnClickListener(null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void addPriceTag() {}


    @Override
    protected ExtractPresenter createPresenter() {
        return new ExtractPresenter();
    }

    @OnClick({R.id.iv_back, R.id.bt_commit})
    public void onViewClicked(View view) {
        if (mClickHelper.click()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_commit:
                onApplyWithdrawal();
                break;
        }
    }

    private void onApplyWithdrawal() {
        pass = etPass.getText().toString().trim();
        price = tvCMoney.getText().length() == 0?0:Integer.parseInt(tvCMoney.getText().toString());
        String usdtAddr = et_usdt.getText().toString();
        if(price == 0){
            ToastUtil.normal(getString(R.string.str_extra_ac_413));
            return;
        }
        if(usdtAddr.isEmpty()){
            ToastUtil.normal(getString(R.string.str_withdrawal_sudt_addr1));
            return;
        }
        if (balance >= price*10000) {
            if (UserHelp.getPayPassword().equals(pass)) {
                showLoading();
                getPresenter().onApplyWithdrawalUsdt(price, pass, usdtAddr);
            } else {
                ToastUtil.error(getString(R.string.str_pass_err));
            }
        } else {
            ToastUtil.normal(getString(R.string.str_yuebuzu));
        }
    }

    @Override
    public void onSuccess() {
        getPresenter().onWithdrawalSuccessUsdtDialog(this);
    }

    @Override
    public void finishSuccess(PayoutGetBean bean) {
        tvBalance.setText(bean.getBalance()/10000D+"");
        this.rate = bean.getRate();
    }

    //获取系统配置后更新
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ConfigEvent configEvent) {
        addPriceTag();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onfinish() {
        this.finish();
    }

    @Override
    public void onFailure(String content) {

    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }

}
