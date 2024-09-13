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
import com.base.kiap.bean.base.BaseUserBean;
import com.base.kiap.bean.base.request.RegisterRequest;
import com.base.kiap.bean.oldbean.UserBean;
import com.base.kiap.bean.request.LoginBean;
import com.base.kiap.config.SpCode;
import com.base.kiap.config.UserHelp;
import com.base.kiap.databinding.ActBaseRegisterBinding;
import com.base.kiap.mvp.basepresenter.BaseLoginPresenter;
import com.base.kiap.mvp.baseviwe.IBaseLoginView;
import com.base.kiap.mvp.iview.ILoginView;
import com.base.kiap.utlis.SPUtils;
import com.base.kiap.utlis.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class BaseRegisterActivity extends BaseMvpActivity<IBaseLoginView, BaseLoginPresenter> implements IBaseLoginView {
    @BindView(R.id.tv_pone)
    EditText tvPone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.et_invite_code)
    EditText et_invite_code;
    @BindView(R.id.tv_get)
    TextView tvGet;

    @BindView(R.id.tv_country)
    TextView tvCountry;

    private ActBaseRegisterBinding binding;
    private CountDownTimerUtils mCountDownTimerUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View attachLayoutView() {
        binding = ActBaseRegisterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, BaseRegisterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_register;
    }

    @Override
    protected void initData() {
        initView();
//        new Handler().postDelayed(() -> isFristLogn(), 500);
        mCountDownTimerUtils = new CountDownTimerUtils(tvGet, 60000, 1000);
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

        tvPone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (tvPone.getText().toString().trim().length() == 11
//                        && code.getText().toString().trim().length() == 4) {
//                    binding.btRegister.setEnabled(true);
//                } else {
//                    binding.btRegister.setEnabled(false);
//                }
            }
        });

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (tvPone.getText().toString().trim().length() == 11
//                        && code.getText().toString().trim().length() == 4) {
//                    binding.btRegister.setEnabled(true);
//                } else {
//                    binding.btRegister.setEnabled(false);
//                }
            }
        });
        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsCode = code.getText().toString();
                String tvPon = tvPone.getText().toString().trim();
                String inviteCode = et_invite_code.getText().toString().trim();
                String passWork = binding.password.toString();
                if(checkPkg(BaseRegisterActivity.this)){
                    ToastUtil.error(getString(R.string.str_app_correctly));
                    return;
                }
                if (smsCode.isEmpty() || tvPon.isEmpty() || passWork.isEmpty()) {
                    ToastUtil.normal(getString(R.string.str_tos_login_2));
                } else {
                    showLoading();
                    RegisterRequest bean = new RegisterRequest();
                    bean.phone = tvCountry.getText().toString().substring(1)+tvPon;
                    bean.smsCode = smsCode;
                    bean.inviteCode = inviteCode;
                    bean.passwd = passWork;
                    getPresenter().onRegister(bean);
                }
            }
        });
        binding.tvGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mPone = tvPone.getText().toString();
                if (mPone.length() == 11) {
                    mCountDownTimerUtils.start();
                    getPresenter().onRegisterCode(mPone,binding.etInviteCode.getText().toString());
                } else {
                    ToastUtil.normal(R.string.str_tos_login_1);
                }
            }
        });
        binding.ivBack.setOnClickListener(v -> finish());
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
    public void onHideDialog() {
        hideLoading();
    }

    @Override
    public void onLoginSuccess(BaseUserBean bean) {

    }

    @Override
    public void onRegisterSuccess(BaseUserBean bean) {
        UserHelp.updateBaseUser(bean);
        UserHelp.updateToken(bean.token);
        MainActivity.start(this);
        finish();
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
