package com.base.kiap.activity;

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

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.PayoutGetBean;
import com.base.kiap.bean.event.ConfigEvent;
import com.base.kiap.config.UserHelp;
import com.base.kiap.mvp.iview.IExtractView;
import com.base.kiap.mvp.presenter.ExtractPresenter;
import com.base.kiap.utlis.ToastUtil;

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
public class ExtractActivity extends BaseMvpActivity<IExtractView, ExtractPresenter> implements IExtractView {
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
    @BindView(R.id.tv_ti1)
    TextView tvTi1;
    @BindView(R.id.tv_c_money)
    TextView tvCMoney;
    @BindView(R.id.et_account_no)
    EditText etAccountNo;
    @BindView(R.id.et_account_holder)
    EditText etAccountHolder;
    @BindView(R.id.et_ifsc)
    EditText etIfsc;
    @BindView(R.id.tv_amount_view)
    TextView tvAmountView;
    @BindView(R.id.rl_account)
    LinearLayout rlAccount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ExtractActivity.class);
        context.startActivity(starter);
    }

    private String price;//选择的金额
    private float balance;//余额
    private long rate = 0;
    private String pass;//密码
    private int type=1;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_extract;
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


        /**
         * 实时监听金额，计算扣除手续费后的金额
         */
        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.toString().length() > 0 && Integer.parseInt(s.toString()) > 0){
                    DecimalFormat decimalFormat =new DecimalFormat("0.00");
                    tvAmountView.setText("Rs "+decimalFormat.format(Integer.parseInt(s.toString())*rate/100F));
                }
            }
        };

        tvCMoney.addTextChangedListener(textWatcher);
    }

    private void initView() {
        if (UserHelp.getPayPassword().isEmpty()) {
            etPass.setClickable(true);
            etPass.setFocusableInTouchMode(false);
            etPass.setOnClickListener(v -> SettingPassActivity.start(ExtractActivity.this));
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
        price = tvCMoney.getText().length() == 0?"0":tvCMoney.getText().toString();
        String accountNo = etAccountNo.getText().toString();
        String accountHolder = etAccountHolder.getText().toString();
        String ifsc = etIfsc.getText().toString();
        if(price.equals("0")){
            ToastUtil.normal(getString(R.string.str_extra_ac_413));
            return;
        }
        if(accountNo.isEmpty()){
            ToastUtil.normal(getString(R.string.str_extra_ac_hint_1));
            return;
        }
        if(accountHolder.isEmpty()){
            ToastUtil.normal(getString(R.string.str_extra_ac_hint_2));
            return;
        }
        if(ifsc.isEmpty()){
            ToastUtil.normal(getString(R.string.str_extra_ac_hint_3));
            return;
        }
        if (balance >= Double.parseDouble(price)*10000) {
            if (UserHelp.getPayPassword().equals(pass)) {
                showLoading();
                getPresenter().onApplyWithdrawal(price, pass, accountNo, accountHolder, ifsc);
            } else {
                ToastUtil.error(getString(R.string.str_pass_err));
            }
        } else {
            ToastUtil.normal(getString(R.string.str_yuebuzu));
        }
    }

    @Override
    public void onSuccess() {
        getPresenter().onWithdrawalSuccessDialog(this);
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
