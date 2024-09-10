package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.oldbean.UserBean;
import com.base.kiap.bean.request.LoginBean;
import com.base.kiap.config.SpCode;
import com.base.kiap.config.UserHelp;
import com.base.kiap.databinding.ActBaseLoginBinding;
import com.base.kiap.databinding.ActBasePayPassBinding;
import com.base.kiap.mvp.basepresenter.BaseLoginPresenter;
import com.base.kiap.mvp.baseviwe.IBaseLoginView;
import com.base.kiap.mvp.iview.ILoginView;
import com.base.kiap.utlis.SPUtils;
import com.base.kiap.utlis.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class BaseLoginActivity extends BaseMvpActivity<IBaseLoginView, BaseLoginPresenter> implements ILoginView {


    private ActBaseLoginBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View attachLayoutView() {
        binding = ActBaseLoginBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, BaseLoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_login;
    }

    @Override
    protected void initData() {
        initView();

    }

    private void isFristLogn() {
        boolean isFristLogn = (boolean) SPUtils.get(SpCode.INSTALL, true);
        if (isFristLogn) {
            getPresenter().onInstall();
        }
    }

    @Override
    protected BaseLoginPresenter createPresenter() {
        return new BaseLoginPresenter();
    }

    private void initView() {

        binding.edPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edPhone.getText().toString().trim().length() >= 6
                        && !binding.etPass.getText().toString().isEmpty()) {
                    binding.btLogin.setEnabled(true);
                } else {
                    binding.btLogin.setEnabled(false);
                }
            }
        });

        binding.etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edPhone.getText().toString().trim().length() >= 6
                        && !binding.etPass.getText().toString().isEmpty()) {
                    binding.btLogin.setEnabled(true);
                } else {
                    binding.btLogin.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.bt_login})
    public void onViewClicked(View view) {
        if (mClickHelper.click()) {
            return;
        }
        switch (view.getId()) {
            case R.id.bt_login:
//                    MainActivity.start(this);
                String voPass = binding.etPass.getText().toString();
                String tvPon =  binding.edPhone.getText().toString().trim();
                if(checkPkg(BaseLoginActivity.this)){
                    ToastUtil.error(getString(R.string.str_app_correctly));
                    break;
                }
                if (voPass.isEmpty() || tvPon.isEmpty()) {
                    ToastUtil.normal(getString(R.string.str_tos_login_2));
                } else {
                    showLoading();
                    binding.btLogin.setEnabled(false);
                    LoginBean bean = new LoginBean();
                    bean.phone = tvPon;
                    bean.passWord = voPass;
                    getPresenter().onLogin(bean);
                }
                break;
        }
    }

    /**
     * 检测多开
     * @param context
     * @return
     */
    private boolean checkPkg(Context context) {
        try {
            if (context == null) {
                return false;
            }
            int count = 0;
            String packageName = context.getPackageName();
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> pkgs = pm.getInstalledPackages(0);
            for (PackageInfo info : pkgs) {
                if (packageName.equals(info.packageName)) {
                    count++;
                }
            }
            return count > 1;
        } catch (Exception ignore) {}
        return false;
    }

    @Override
    public void onLoginSuccess(UserBean bean) {
        UserHelp.updateUser(bean);
        UserHelp.updateToken(bean.getToken());
        MainActivity.start(this);
        finish();
    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }

    public class CountDownTimerUtils extends CountDownTimer {
        private WeakReference<TextView> mTextView;

        public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.mTextView = new WeakReference(textView);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onTick(long millisUntilFinished) {
            //用弱引用 先判空 避免崩溃
            if (mTextView.get() == null) {
                cancle();
                return;
            }
            mTextView.get().setClickable(false); //设置不可点击
            mTextView.get().setText(millisUntilFinished / 999 + " S"); //设置倒计时时间
            mTextView.get().setTextColor(getColor(R.color.color_bd));

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onFinish() {
            //用软引用 先判空 避免崩溃
            if (mTextView.get() == null) {
                cancle();
                return;
            }
            mTextView.get().setText(R.string.str_login_send);
            mTextView.get().setClickable(true);//重新获得点击
            mTextView.get().setTextColor(getColor(R.color.color_font_black_00));
        }

        public void cancle() {
            if (this != null) {
                this.cancel();
            }
        }
    }
}
