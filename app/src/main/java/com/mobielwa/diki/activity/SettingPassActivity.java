package com.mobielwa.diki.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BaseMvpActivity;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.mvp.iview.ISetPayPass;
import com.mobielwa.diki.mvp.presenter.SetPayPassPresenter;
import com.mobielwa.diki.utlis.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/21/20 4:31 PM
 * @Description: 设置支付密码
 */
public class SettingPassActivity extends BaseMvpActivity<ISetPayPass, SetPayPassPresenter> implements ISetPayPass {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_old_pass)
    EditText etOldPass;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.et_pass2)
    EditText etPass2;
    @BindView(R.id.bt_save_pic)
    Button btSavePic;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingPassActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_pay_pass;
    }

    @Override
    protected void initData() {
        initImmersionBar();
        if (UserHelp.getPayPassword().isEmpty()) {
            etOldPass.setVisibility(View.GONE);
        }else{
            etOldPass.setVisibility(View.VISIBLE);
        }
        tvTitle.setText(R.string.str_zhifupas);
        initViewTV();
    }

    private void initViewTV() {
    }

    @Override
    protected SetPayPassPresenter createPresenter() {
        return new SetPayPassPresenter();
    }

    @Override
    public void onSuccess() {

        finish();
    }

    @Override
    public void onUsdt(String usdt) {

    }

    @Override
    public void onHideDialog() {
        hideLoading();
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
                String oldPass = etOldPass.getText().toString().trim();
                String pas1 = etPass.getText().toString().trim();
                String pas2 = etPass2.getText().toString().trim();
                if(!UserHelp.getPayPassword().isEmpty() && (oldPass.isEmpty() || !UserHelp.getPayPassword().equals(oldPass))){
                    ToastUtil.error(getString(R.string.str_pass_err));
                    break;
                }
                if (!pas1.isEmpty()&&!pas2.isEmpty()&&pas1.equals(pas2)) {
                    showLoading();
                    getPresenter().onSetPayPwd(etPass.getText().toString().trim());
                }else{
                    ToastUtil.normal(getString(R.string.str_pass_inut_tis));
                }
                break;
        }
    }
}
