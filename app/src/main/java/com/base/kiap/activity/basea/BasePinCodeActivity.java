package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.databinding.ActBasePinCodeBinding;
import com.base.kiap.mvp.iview.ISetPayPass;
import com.base.kiap.mvp.presenter.SetPayPassPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 */
public class BasePinCodeActivity extends BaseMvpActivity<ISetPayPass, SetPayPassPresenter> implements ISetPayPass {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    private ActBasePinCodeBinding binding;


    public static void start(Context context) {
        Intent starter = new Intent(context, BasePinCodeActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_pin_code;
    }

    @Override
    public View attachLayoutView() {
        binding = ActBasePinCodeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        initImmersionBar();
        tvTitle.setText("Pin Code");
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

                break;
        }
    }
}
