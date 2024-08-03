package com.base.kiap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.activity.ExchangeDetailActivity;
import com.base.kiap.activity.basea.AddToolSetpActivity;
import com.base.kiap.activity.basea.BaseTeamDetailActivity;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.OrderBean;
import com.base.kiap.bean.UsdtIndexBean;
import com.base.kiap.databinding.BaseFrmTeamBinding;
import com.base.kiap.databinding.BaseFrmUpi2Binding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.base.kiap.utlis.DialogUtlis;
import com.base.kiap.utlis.ShareUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class BaseToolSetp2Fragment extends BaseFragment2<IOrderView, OrderPresenter> implements IOrderView, onItemClickListen3 {



    private BaseFrmUpi2Binding binding;

    public static BaseToolSetp2Fragment newInstance() {

        Bundle args = new Bundle();
        BaseToolSetp2Fragment fragment = new BaseToolSetp2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmUpi2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_upi_2;
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initView();
    }

    @Override
    protected void lazyLoad() {

    }

    private void initView() {
        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddToolSetpActivity) getActivity()).replaceFragment(2);
            }
        });
        binding.btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddToolSetpActivity) getActivity()).replaceFragment(0);
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
