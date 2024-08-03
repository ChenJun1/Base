package com.base.kiap.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.base.kiap.R;
import com.base.kiap.adapter.CashRecordAdapter;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.CashRecordBean;
import com.base.kiap.config.Constants;
import com.base.kiap.mvp.iview.ICashRecordListView;
import com.base.kiap.mvp.presenter.CashRecordPresenter;
import com.base.kiap.utlis.RecyclerViewLoadUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/21/20 4:31 PM
 * @Description: 收益明细
 */
public class CashRecordActivity extends BaseMvpActivity<ICashRecordListView, CashRecordPresenter> implements ICashRecordListView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CashRecordActivity.class);
        context.startActivity(starter);
    }

    private String[] tabs;
    private CashRecordAdapter adapter;
    private List<CashRecordBean> incomeList=new ArrayList<>();
    private List<CashRecordBean> payList=new ArrayList<>();
    private List<CashRecordBean> mList=new ArrayList<>();
    private int incomePage=1;
    private int PayPage=1;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_cash_record;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.str_shouzhijl);
        initImmersionBar();
        initTabs();
        initRv();
        showLoading();
        getPresenter().onIncomeRecord(incomePage);
        getPresenter().onPayRecord(PayPage);
    }

    @Override
    protected CashRecordPresenter createPresenter() {
        return new CashRecordPresenter();
    }

    private void initTabs() {
        tabs = getResources().getStringArray(R.array.cash_tabs);
        for (int i = 0; i < tabs.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        income();
                        break;
                    case 1:
                        pay();
                        break;
                }
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
        adapter = new CashRecordAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        RecyclerViewLoadUtil.rvLoad(recyclerview);
        recyclerview.setAdapter(adapter);
        adapter.setPreLoadNumber(1);
        adapter.setNewData(mList);
        refreshLayout.setEnableLoadmore(true);

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Loadmore();
                refreshLayout.finishLoadmore(Constants.RefreshTime,true);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh();
                refreshLayout.finishRefresh(Constants.RefreshTime,true);
            }
        });
    }

    protected void refresh() {
        showLoading();
        switch (tabLayout.getSelectedTabPosition()) {
            case 0:
                incomePage = 1;
                getPresenter().onIncomeRecord(incomePage);
                break;
            case 1:
                PayPage = 1;
                getPresenter().onPayRecord(PayPage);
                break;
        }
    }
    protected void Loadmore() {
        showLoading();
        switch (tabLayout.getSelectedTabPosition()) {
            case 0:
                incomePage++;
                getPresenter().onIncomeRecord(incomePage);
                break;
            case 1:
                PayPage++;
                getPresenter().onPayRecord(PayPage);
                break;
        }
    }

    //收入
    protected void income() {
        if (incomeList != null && incomeList.size() > 0) {
            rlEmpty.setVisibility(View.GONE);
            mList.clear();
            mList.addAll(incomeList);
            adapter.setNewData(mList);
        }else{
            mList.clear();
            adapter.setNewData(mList);
            rlEmpty.setVisibility(View.VISIBLE);
        }

    }
    // 支出
    protected void pay() {
        if (payList != null && payList.size() > 0) {
            rlEmpty.setVisibility(View.GONE);
            mList.clear();
            mList.addAll(payList);
            adapter.setNewData(mList);
        }else{
            mList.clear();
            adapter.setNewData(mList);
            rlEmpty.setVisibility(View.VISIBLE);
        }
    }



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onIncomeSuccess(List<CashRecordBean> cashRecordBeanList) {
        if (cashRecordBeanList != null) {
            if (incomePage == 1) {
                refreshLayout.finishRefresh(true);
                incomeList.clear();
                mList.clear();
                if (cashRecordBeanList.size() == 0) {
                    refreshLayout.resetNoMoreData();
                    rlEmpty.setVisibility(View.VISIBLE);
                } else {
                    rlEmpty.setVisibility(View.GONE);
                }
            } else {
                refreshLayout.finishLoadmore(true);
            }
            for (CashRecordBean cashRecordBean : cashRecordBeanList) {
                cashRecordBean.setIsflag(1);
                incomeList.add(cashRecordBean);
            }
            switch (tabLayout.getSelectedTabPosition()) {
                case 0:
                    income();
                    break;
                case 1:
                    pay();
                    break;
            }
        }
    }

    @Override
    public void onPaySuccess(List<CashRecordBean> cashRecordBeanList) {
        if (cashRecordBeanList != null) {
            if (PayPage == 1) {
                refreshLayout.finishRefresh(true);
                payList.clear();
                mList.clear();
                if (cashRecordBeanList.size() == 0) {
                    refreshLayout.resetNoMoreData();
                    rlEmpty.setVisibility(View.VISIBLE);
                } else {
                    rlEmpty.setVisibility(View.GONE);
                }
            } else {
                refreshLayout.finishLoadmore(true);
            }
            for (CashRecordBean cashRecordBean : cashRecordBeanList) {
                cashRecordBean.setIsflag(2);
                payList.add(cashRecordBean);
            }
            switch (tabLayout.getSelectedTabPosition()) {
                case 0:
                    income();
                    break;
                case 1:
                    pay();
                    break;
            }
        }
    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }
}
