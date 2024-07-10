package com.mobielwa.diki.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.mobielwa.diki.R;
import com.mobielwa.diki.activity.CashRecordActivity;
import com.mobielwa.diki.activity.ExchangeDetailActivity;
import com.mobielwa.diki.activity.ExtractActivity;
import com.mobielwa.diki.activity.RechargeActivity;
import com.mobielwa.diki.activity.RechargeUsdtActivity;
import com.mobielwa.diki.adapter.OrdersAdapter;
import com.mobielwa.diki.base.BaseFragment2;
import com.mobielwa.diki.bean.DialogBean;
import com.mobielwa.diki.bean.OrderBean;
import com.mobielwa.diki.bean.UsdtIndexBean;
import com.mobielwa.diki.bean.event.Main2MemberEvent;
import com.mobielwa.diki.bean.event.MemberEvent;
import com.mobielwa.diki.bean.event.OrderSuccessEvent;
import com.mobielwa.diki.config.ConfigDate;
import com.mobielwa.diki.config.Constants;
import com.mobielwa.diki.config.SpCode;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.listen.onItemClickListen;
import com.mobielwa.diki.listen.onItemClickListen3;
import com.mobielwa.diki.mvp.iview.IOrderView;
import com.mobielwa.diki.mvp.presenter.OrderPresenter;
import com.mobielwa.diki.utlis.CommUtils;
import com.mobielwa.diki.utlis.DialogUtlis;
import com.mobielwa.diki.utlis.NLog;
import com.mobielwa.diki.utlis.RecyclerViewLoadUtil;
import com.mobielwa.diki.utlis.SPUtils;
import com.mobielwa.diki.utlis.ShareUtils;
import com.mobielwa.diki.widget.WhiteProgressBarDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class OrdersFragment extends BaseFragment2<IOrderView, OrderPresenter> implements IOrderView, onItemClickListen3 {

    @BindView(R.id.tv_lv)
    TextView tvLv;
    @BindView(R.id.tv_upgrade)
    TextView tvUpgrade;
    @BindView(R.id.tv_task_sum)
    TextView tvTaskSum;
    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    protected WhiteProgressBarDialog barDialog;

    public static OrdersFragment newInstance() {

        Bundle args = new Bundle();
        OrdersFragment fragment = new OrdersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private OrdersAdapter adapter;
    private List<OrderBean> mList = new ArrayList<>();
    private int page = 1;
    private int type = 0;

    @Override

    public int attachLayoutRes() {
        return R.layout.frm_orders;
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
        //查询信息
        getPresenter().onIndex();
        initTabs();
        initRv();
        showLoading();
        getPresenter().onOrderList(0, page);
    }

    private void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_pay_usdt_home)), true);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_pay_usdt_my)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.str_pay_usdt_3)));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                showLoading();
                type = tab.getPosition();
                getPresenter().onOrderList(type, page);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initRv() {
        adapter = new OrdersAdapter(this);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        homeRv.setLayoutManager(linearLayoutManager);
        homeRv.setAdapter(adapter);
        RecyclerViewLoadUtil.rvLoad(homeRv);
        homeRv.setAdapter(adapter);
        adapter.setPreLoadNumber(1);
        adapter.setNewData(mList);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshLayout.finishLoadmore(true);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                showLoading();
                refreshLayout.finishRefresh(Constants.RefreshTime, true);
                page = 1;
                getPresenter().onOrderList(type, page);
            }
        });
    }


    @OnClick({R.id.circle_button, R.id.tv_share12, R.id.tv_upgrade})
    public void onViewClicked(View view) {
        if (mClickHelper.click()) {
            return;
        }
        switch (view.getId()) {
            case R.id.circle_button:
                if (ConfigDate.sConfigBean != null) {
                    ShareUtils.addGroup(mActivity, ConfigDate.sConfigBean.getTelegram());
                }
                break;
            case R.id.tv_share12:
                ExtractActivity.start(mActivity);
                break;
            case R.id.tv_upgrade:
                CashRecordActivity.start(mActivity);
                break;
        }
    }

    @Override
    public void onIndex(UsdtIndexBean bean) {
        tvLv.setText(""+bean.getTodayAmount());
        tvTaskSum.setText(bean.getTodayCount()+"");
    }

    @Override
    public void onGetOrderSuccess(List<OrderBean> orderBeanList) {
        if (orderBeanList != null) {
            if (page == 1) {
                refreshLayout.finishRefresh(true);
                mList.clear();
                if (orderBeanList.size() == 0) {
                    refreshLayout.resetNoMoreData();
                    rlEmpty.setVisibility(View.VISIBLE);
                } else {
                    rlEmpty.setVisibility(View.GONE);
                }
            } else {
                refreshLayout.finishLoadmore(true);
            }
            mList.addAll(orderBeanList);
            adapter.setNewData(mList);
        }
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
//        if(bean.getPayStatus() <= 1){
//            //风险提示
//            onNewuserWecome(bean);
//        }else{
//            RechargeActivity.start(mActivity);
//        }
        //进详情页
        ExchangeDetailActivity.start(mActivity, bean);
    }

}
