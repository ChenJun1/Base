package com.base.kiap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.activity.ExchangeDetailActivity;
import com.base.kiap.activity.basea.AddBankActivity;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.OrderBean;
import com.base.kiap.bean.UsdtIndexBean;
import com.base.kiap.databinding.BaseFrmTool01Binding;
import com.base.kiap.databinding.BaseFrmTool02Binding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class BaseToolFragment02 extends BaseFragment2<IOrderView, OrderPresenter> implements IOrderView, onItemClickListen3 {

    private BaseFrmTool02Binding binding;

    public static BaseToolFragment02 newInstance() {

        Bundle args = new Bundle();
        BaseToolFragment02 fragment = new BaseToolFragment02();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmTool02Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_tool_02;
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void lazyLoad() {
        initView();
    }

    private void initView() {
        binding.llAddBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBankActivity.start(getActivity());
            }
        });

    }


    @Override
    public void onIndex(UsdtIndexBean bean) {

    }

    @Override
    public void onGetOrderSuccess(List<OrderBean> orderBeanList) {

    }

    @Override
    public void onChangelv() {
    }

    @Override
    public void onResume() {
        super.onResume();
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

    //接单点击
    @Override
    public void onItemClick(View v, OrderBean bean) {
        ExchangeDetailActivity.start(mActivity, bean);
    }

}
