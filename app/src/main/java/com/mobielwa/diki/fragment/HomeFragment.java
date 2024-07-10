package com.mobielwa.diki.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mobielwa.diki.R;
import com.mobielwa.diki.activity.ExchangeDetailActivity;
import com.mobielwa.diki.activity.ExtractActivity;
import com.mobielwa.diki.activity.hkk.MeInfoActivity;
import com.mobielwa.diki.activity.RechargeActivity;
import com.mobielwa.diki.adapter.OrdersAdapter;
import com.mobielwa.diki.base.BaseFragment2;
import com.mobielwa.diki.bean.IndexBean;
import com.mobielwa.diki.bean.OrderBean;
import com.mobielwa.diki.bean.UserBean;
import com.mobielwa.diki.config.Constants;
import com.mobielwa.diki.listen.onItemClickListen3;
import com.mobielwa.diki.mvp.iview.IHomeView;
import com.mobielwa.diki.mvp.presenter.HomePresenter;
import com.mobielwa.diki.utlis.RecyclerViewLoadUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 首页
 */
public class HomeFragment extends BaseFragment2<IHomeView, HomePresenter> implements IHomeView, onItemClickListen3 {

    @BindView(R.id.rl_recharge)
    RelativeLayout rl_recharge;

    @BindView(R.id.tv_wazir_price)
    TextView tv_wazir_price;

    @BindView(R.id.tv_binance_price)
    TextView tv_binance_price;

    @BindView(R.id.tv_kucoin_price)
    TextView tv_kucoin_price;

    @BindView(R.id.tv_usdt_price)
    TextView tv_usdt_price;
    @BindView(R.id.tv_home_balance)
    TextView tv_home_balance;

    @BindView(R.id.tv_home_balance1)
    TextView tv_home_balance1;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;

    @BindView(R.id.home_rv)
    RecyclerView homeRv;


    private OrdersAdapter adapter;
    private List<OrderBean> mList = new ArrayList<>();

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int attachLayoutRes() {
        return R.layout.frm_home2;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void initView(@NotNull View view) {
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

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                showLoading();
                getPresenter().findUser();
                getPresenter().findIndex();
                getPresenter().onOrderList(3, 1);
                refreshLayout.finishRefresh(Constants.RefreshTime, true);

            }
        });
    }

    @Override
    protected void lazyLoad() {
        loadDate();
    }

    private void loadDate() {
        showLoading();
        getPresenter().findUser();
        getPresenter().findIndex();
        getPresenter().onOrderList(3, 1);
    }

    @Override
    public void onMineInfoSuccess(UserBean bean) {
        hideLoading();
    }

    @Override
    public void onIndexSuccess(IndexBean bean) {
        tv_wazir_price.setText("≈ "+bean.getWazirPrice()+" INR");
        tv_binance_price.setText("≈ "+bean.getBinancePrice()+" INR");
        tv_kucoin_price.setText("≈ "+bean.getKucoinPrice()+" INR");
        tv_usdt_price.setText(" "+bean.getUsdtPrice()/100D+" INR");
        tv_home_balance.setText(bean.getBalance());
        tv_home_balance1.setText(bean.getBalance1()+" INR");
    }

    @Override
    public void onGetOrderSuccess(List<OrderBean> orderBeanList) {
        if (orderBeanList != null) {
            refreshLayout.finishRefresh(true);
            mList.clear();
            if (orderBeanList.size() == 0) {
                refreshLayout.resetNoMoreData();
                rlEmpty.setVisibility(View.VISIBLE);
            } else {
                rlEmpty.setVisibility(View.GONE);
            }
            mList.addAll(orderBeanList);
            adapter.setNewData(mList);
        }
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

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().findUser();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @OnClick({R.id.rl_recharge, R.id.iv_team_t, R.id.tv_share12})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_recharge:
                RechargeActivity.start(mActivity);
                break;
            case R.id.iv_team_t:
                MeInfoActivity.start(mActivity);
                break;
            case R.id.tv_share12:
                ExtractActivity.start(mActivity);
                break;
        }
    }

    @Override
    public void onItemClick(View v, OrderBean bean) {
        ExchangeDetailActivity.start(mActivity, bean);
    }
}
