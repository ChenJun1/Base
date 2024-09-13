package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.kiap.R;
import com.base.kiap.adapter.BaseDepositHistoryAdapter;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.base.DepositInrHistoryBean;
import com.base.kiap.config.Constants;
import com.base.kiap.databinding.ActBaseDepositListBinding;
import com.base.kiap.mvp.basepresenter.BaseDepositHistoryPresenter;
import com.base.kiap.mvp.baseviwe.IBaseDepositHistoryView;
import com.base.kiap.utlis.RecyclerViewLoadUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 2/25/21 3:53 PM
 * @Description: DepositHistory
 */
public class DepositHistoryActivity extends BaseMvpActivity<IBaseDepositHistoryView, BaseDepositHistoryPresenter> implements IBaseDepositHistoryView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.home_rv)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;

    private ActBaseDepositListBinding binding;

    public static void start(Context context) {
        Intent starter = new Intent(context, DepositHistoryActivity.class);
        context.startActivity(starter);
    }

    private BaseDepositHistoryAdapter adapter;
    public int page = 1;
    public int type = 1;
    private List<DepositInrHistoryBean> mList = new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_deposit_list;
    }
    @Override
    public View attachLayoutView() {
        binding = ActBaseDepositListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    protected void initData() {
        tvTitle.setText("Deposit history");
        initImmersionBar();
        showLoading();
        getPresenter().getMoneyHistoryOrderList(page);
        initTabs();
        initRv();
    }

    private void initRv() {
        adapter = new BaseDepositHistoryAdapter();
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
                page = page + 1;
                if (type == 1) {
                    getPresenter().getMoneyHistoryOrderList(page);
                }else{
                    getPresenter().getMoneyHistoryUstdList(page);
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh(Constants.RefreshTime, true);
                showLoading();
                page = 1;
                if (type == 1) {
                    getPresenter().getMoneyHistoryOrderList(page);
                }else{
                    getPresenter().getMoneyHistoryUstdList(page);
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DepositHistoryDetailActivity.start(DepositHistoryActivity.this,mList.get(position),type);
            }
        });
    }

    private void initTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("INR"), true);
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("USDT"));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        type = 1;
                        break;
                    case 1:
                        type = 2;
                        break;
                }
                page = 1;
                if (type == 1) {
                    getPresenter().getMoneyHistoryOrderList(page);
                }else{
                    getPresenter().getMoneyHistoryUstdList(page);
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

    @Override
    protected BaseDepositHistoryPresenter createPresenter() {
        return new BaseDepositHistoryPresenter();
    }



    @Override
    public void onHideDialog() {
        hideLoading();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onSuccessInrList(List<DepositInrHistoryBean> list) {
        refreshLayout.finishRefresh(true);
        refreshLayout.finishLoadmore();
        if (list != null) {
            if (page == 1) {
                mList.clear();
            }
            mList.addAll(list);
            adapter.setNewData(mList);
            if (mList.size() == 0) {
                rlEmpty.setVisibility(View.VISIBLE);
            } else {
                rlEmpty.setVisibility(View.GONE);
            }
        }
    }

}
