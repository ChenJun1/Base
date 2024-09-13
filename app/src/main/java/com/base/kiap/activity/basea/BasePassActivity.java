package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.databinding.ActBasePayPassBinding;
import com.base.kiap.mvp.basepresenter.BaseForgetPassPresenter;
import com.base.kiap.mvp.baseviwe.IForgetPassView;
import com.base.kiap.utlis.ToastUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 */
public class BasePassActivity extends BaseMvpActivity<IForgetPassView, BaseForgetPassPresenter> implements IForgetPassView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_save_pic)
    Button btSavePic;

    private ActBasePayPassBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, BasePassActivity.class);
        context.startActivity(starter);
    }
    private CountDownTimerUtils mCountDownTimerUtils;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_pay_pass;
    }

    @Override
    public View attachLayoutView() {
        binding = ActBasePayPassBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        initImmersionBar();
        tvTitle.setText("Forget password");
        initViewTV();
    }

    private void initViewTV() {
        mCountDownTimerUtils = new CountDownTimerUtils(binding.tvGet, 60000, 1000);
        binding.tvGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.etPhone.getText().toString();
                if (phone.length() == 11) {
                    mCountDownTimerUtils.start();
                    getPresenter().onCodeSms(phone);
                }else{
                    ToastUtil.normal(R.string.str_input_error);
                }
            }
        });
        binding.btSavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.etPhone.getText().toString();
                String pas1 = binding.etPass1.getText().toString().trim();
                String pas2 = binding.etPass2.getText().toString().trim();
                String otp = binding.etOtp.getText().toString().trim();

                if (!pas1.isEmpty()&&!pas2.isEmpty()&&pas1.equals(pas2)) {
                    showLoading();
                    getPresenter().onForgetPass(binding.etPass1.getText().toString().trim());
                }else{
                    ToastUtil.normal(getString(R.string.str_pass_inut_tis));
                }
            }
        });
    }

    @Override
    protected BaseForgetPassPresenter createPresenter() {
        return new BaseForgetPassPresenter();
    }

    @Override
    public void onSuccess() {
        finish();
    }


    @Override
    public void onHideDialog() {
        hideLoading();
    }


    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        if (mClickHelper.click()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
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
