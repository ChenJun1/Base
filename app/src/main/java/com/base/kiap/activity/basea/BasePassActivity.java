package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.databinding.ActBasePayPassBinding;
import com.base.kiap.mvp.basepresenter.BaseForgetPassPresenter;
import com.base.kiap.mvp.baseviwe.IForgetPassView;
import com.base.kiap.utlis.ToastUtil;

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
                String pas1 = binding.etPass1.getText().toString().trim();
                String pas2 = binding.etPass2.getText().toString().trim();

                if (!pas1.isEmpty()&&!pas2.isEmpty()&&pas1.equals(pas2)) {
                    showLoading();
                    getPresenter().onForgetPass(binding.etPass1.getText().toString().trim());
                }else{
                    ToastUtil.normal(getString(R.string.str_pass_inut_tis));
                }
                break;
        }
    }
}
