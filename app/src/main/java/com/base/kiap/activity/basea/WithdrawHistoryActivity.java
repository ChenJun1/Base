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
import com.base.kiap.activity.TeamDetailActivity;
import com.base.kiap.adapter.TeamAdapter;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.config.Constants;
import com.base.kiap.databinding.ActBaseDepositListBinding;
import com.base.kiap.databinding.ActBaseQuotaListBinding;
import com.base.kiap.databinding.ActBaseWithdrawListBinding;
import com.base.kiap.mvp.iview.IMessgListView;
import com.base.kiap.mvp.presenter.MessgListPresenter;
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
public class WithdrawHistoryActivity extends BaseMvpActivity<IMessgListView, MessgListPresenter> implements IMessgListView {
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

    private ActBaseWithdrawListBinding binding;

    public static void start(Context context) {
        Intent starter = new Intent(context, WithdrawHistoryActivity.class);
        context.startActivity(starter);
    }

    private TeamAdapter adapter;
    private List<MessageBean> mList = new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_withdraw_list;
    }

    @Override
    public View attachLayoutView() {
        binding = ActBaseWithdrawListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        tvTitle.setText("Withdraw history");
        initImmersionBar();
//        showLoading();
        MessageBean bean = new MessageBean();
        mList.add(bean);
        mList.add(bean);
        initTabs();
        initRv();
    }

    private void initRv() {
        adapter = new TeamAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        RecyclerViewLoadUtil.rvLoad(recyclerview);
        recyclerview.setAdapter(adapter);
        adapter.setPreLoadNumber(1);
        adapter.setNewData(mList);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh(Constants.RefreshTime, true);
                showLoading();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TeamDetailActivity.start(WithdrawHistoryActivity.this);
            }
        });
    }

    private void initTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Paying"), true);
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Success"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Timeout"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Offline"));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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
    protected MessgListPresenter createPresenter() {
        return new MessgListPresenter();
    }

    @Override
    public void onSuccess(List<MessageBean> list) {
        if (list != null) {
            refreshLayout.finishRefresh(true);
            mList.clear();
            if (list.size() == 0) {
                refreshLayout.resetNoMoreData();
                rlEmpty.setVisibility(View.VISIBLE);
            } else {
                rlEmpty.setVisibility(View.GONE);
            }
            mList.addAll(list);
            adapter.setNewData(mList);
        }
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
