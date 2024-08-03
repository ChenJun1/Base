package com.base.kiap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.kiap.R;
import com.base.kiap.activity.ExchangeDetailActivity;
import com.base.kiap.activity.TeamDetailActivity;
import com.base.kiap.activity.basea.AddToolActivity;
import com.base.kiap.activity.basea.InBoxActivity;
import com.base.kiap.adapter.BaseDeposit01Adapter;
import com.base.kiap.adapter.TeamAdapter;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.OrderBean;
import com.base.kiap.bean.UsdtIndexBean;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.config.Constants;
import com.base.kiap.databinding.BaseFrmTool01Binding;
import com.base.kiap.databinding.BaseFrmToolBinding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.base.kiap.utlis.RecyclerViewLoadUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class BaseToolFragment01 extends BaseFragment2<IOrderView, OrderPresenter> implements IOrderView, onItemClickListen3 {


    private BaseFrmTool01Binding binding;

    private BaseDeposit01Adapter adapter;
    private List<MessageBean> mList = new ArrayList<>();

    public static BaseToolFragment01 newInstance() {

        Bundle args = new Bundle();
        BaseToolFragment01 fragment = new BaseToolFragment01();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmTool01Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_tool_01;
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
        binding.llAddTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToolActivity.start(getActivity());
            }
        });
        initRv();
    }

    private void initRv() {
        MessageBean bean = new MessageBean();
        mList.add(bean);
        mList.add(bean);
        mList.add(bean);
        mList.add(bean);
        mList.add(bean);
        mList.add(bean);
        mList.add(bean);
        adapter = new BaseDeposit01Adapter();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.homeRv.setLayoutManager(linearLayoutManager);
        RecyclerViewLoadUtil.rvLoad(binding.homeRv);
        binding.homeRv.setAdapter(adapter);
        adapter.setPreLoadNumber(1);
        adapter.setNewData(mList);
        binding.refreshLayout.setEnableLoadmore(false);
        binding.refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                binding.refreshLayout.finishRefresh(Constants.RefreshTime, true);
                showLoading();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
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
