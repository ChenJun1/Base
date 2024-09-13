package com.base.kiap.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.activity.ExchangeDetailActivity;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.base.DepositUsdtInfoBean;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.oldbean.UsdtIndexBean;
import com.base.kiap.config.UserHelp;
import com.base.kiap.databinding.BaseFrmDeposit02Binding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.basepresenter.BaseDepositUsdtPresenter;
import com.base.kiap.mvp.baseviwe.IBaseDepositUsdtView;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.base.kiap.utlis.CommUtils;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class DepositFragment02 extends BaseFragment2<IBaseDepositUsdtView, BaseDepositUsdtPresenter> implements IBaseDepositUsdtView{



    private BaseFrmDeposit02Binding binding;

    private long inr;
    private long bonus;

    public static DepositFragment02 newInstance() {

        Bundle args = new Bundle();
        DepositFragment02 fragment = new DepositFragment02();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmDeposit02Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_deposit_02;
    }

    @Override
    protected BaseDepositUsdtPresenter createPresenter() {
        return new BaseDepositUsdtPresenter();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void lazyLoad() {
        binding.edUsd.setText("1");
        getPresenter().getUsdInfo();
        binding.edUsd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = binding.edUsd.getText().toString();
                if (text.isEmpty()) {
                    binding.tvReceiveInr.setText("0");
                    binding.tvBonusInr.setText("0");
                }else{
                    long lo = Long.parseLong(text);
                    binding.tvBonusInr.setText(lo * bonus +"");
                    binding.tvReceiveInr.setText(lo * (bonus+inr) +"");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        binding.edUsd.setText("1");
        getPresenter().getUsdInfo();
        binding.tvQuota.setText(CommUtils.coverMoney(UserHelp.getRsBalance()));
    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccess(DepositUsdtInfoBean bean) {
        binding.tvAddress.setText(bean.usdtAddress);
        binding.tvExchangeValue.setText(bean.exchangeValue +"");
        binding.tvBonusInr.setText(bean.bonus +"");
        Glide.with(getActivity().getApplicationContext()).load(bean.qrCode).into(binding.ivSq);
        inr = bean.exchangeValue;
        bonus = bean.bonus;
        String text = binding.edUsd.getText().toString();
        if (text.isEmpty()) {
            binding.tvReceiveInr.setText(0);
            binding.tvBonusInr.setText(0);
        }else{
            long lo = Long.parseLong(text);
            binding.tvBonusInr.setText(lo * bonus +"");
            binding.tvReceiveInr.setText(lo * (bonus+inr) +"");
        }
    }
}
