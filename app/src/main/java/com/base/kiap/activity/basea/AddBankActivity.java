package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.base.request.AddBankRequest;
import com.base.kiap.config.UserHelp;
import com.base.kiap.databinding.ActBaseAddBankBinding;
import com.base.kiap.mvp.basepresenter.BaseAddBankPresenter;
import com.base.kiap.mvp.baseviwe.IBaseAddBankView;
import com.base.kiap.utlis.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class AddBankActivity extends BaseMvpActivity<IBaseAddBankView, BaseAddBankPresenter> implements IBaseAddBankView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddBankActivity.class);
        context.startActivity(starter);
    }

    private ActBaseAddBankBinding binding;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_add_bank;
    }
    @Override
    public View attachLayoutView() {
        binding = ActBaseAddBankBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    protected void initData() {
        tvTitle.setText("Add Bank");
        initImmersionBar();

        binding.btSavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                String userName = binding.etPass.getText().toString();
                String bankName = binding.etPass2.getText().toString();
                String bankAccount = binding.etNumber.getText().toString();
                String ifsc = binding.etOtp.getText().toString();
                String address = binding.address.getText().toString();
                if (userName.isEmpty() || bankName.isEmpty() || bankAccount.isEmpty()
                ||ifsc.isEmpty() || address.isEmpty()) {
                    ToastUtil.normal("Input Error");
                }else{
                    AddBankRequest request = new AddBankRequest();
                    request.address = address;
                    request.userName = userName;
                    request.userId = Integer.parseInt(UserHelp.getUserId());
                    request.bankAccount = bankAccount;
                    request.bankName = bankName;
                    request.ifsc = ifsc;
                    getPresenter().onAddBank(request);
                }
            }
        });
    }



    @Override
    protected BaseAddBankPresenter createPresenter() {
        return new BaseAddBankPresenter();
    }

    @Override
    public void onSuccess() {
        finish();
        ToastUtil.normal("Success");

    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


}
