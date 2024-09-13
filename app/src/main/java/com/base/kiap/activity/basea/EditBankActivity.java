package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.base.BaseBankInfoBean;
import com.base.kiap.bean.base.request.AddBankRequest;
import com.base.kiap.config.UserHelp;
import com.base.kiap.databinding.ActBaseAddBankBinding;
import com.base.kiap.databinding.ActBaseEditBankBinding;
import com.base.kiap.mvp.basepresenter.BaseAddBankPresenter;
import com.base.kiap.mvp.baseviwe.IBaseAddBankView;
import com.base.kiap.utlis.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class EditBankActivity extends BaseMvpActivity<IBaseAddBankView, BaseAddBankPresenter> implements IBaseAddBankView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private static BaseBankInfoBean mBankInfoBean;

    public static void start(Context context,BaseBankInfoBean bankInfoBean) {
        mBankInfoBean = bankInfoBean;
        Intent starter = new Intent(context, EditBankActivity.class);
        context.startActivity(starter);
    }

    private ActBaseEditBankBinding binding;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_edit_bank;
    }
    @Override
    public View attachLayoutView() {
        binding = ActBaseEditBankBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    protected void initData() {
        tvTitle.setText("Edit Bank");
        initImmersionBar();

       binding.etPass.setText(mBankInfoBean.userName);
       binding.etPass2.setText(mBankInfoBean.bankName);
       binding.etNumber.setText(mBankInfoBean.bankAccount);
       binding.etIfsc.setText(mBankInfoBean.ifsc);
       binding.address.setText(mBankInfoBean.address);

        binding.btSavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                String userName = binding.etPass.getText().toString();
                String bankName = binding.etPass2.getText().toString();
                String bankAccount = binding.etNumber.getText().toString();
                String ifsc = binding.etIfsc.getText().toString();
                String address = binding.address.getText().toString();
                if (userName.isEmpty() || bankName.isEmpty() || bankAccount.isEmpty()
                ||ifsc.isEmpty() || address.isEmpty()) {
                    ToastUtil.normal("Input Error");
                }else{
                    AddBankRequest request = new AddBankRequest();
                    request.id = mBankInfoBean.id;
                    request.email = mBankInfoBean.email;

                    request.address = address;
                    request.userName = userName;
                    request.userId = Integer.parseInt(UserHelp.getUserId());
                    request.bankAccount = bankAccount;
                    request.bankName = bankName;
                    request.ifsc = ifsc;
                    getPresenter().onUpdateBank(request);
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
