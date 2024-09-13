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
import com.base.kiap.adapter.BaseTeamAdapter;
import com.base.kiap.adapter.BaseTeamDetailAdapter;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.base.BaseTeamDetailBean;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.config.Constants;
import com.base.kiap.databinding.ActBaseTeamDetailListBinding;
import com.base.kiap.mvp.basepresenter.BaseTeamDetailPresenter;
import com.base.kiap.mvp.baseviwe.IBaseTeamDetailView;
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

 */
public class BaseTeamDetailActivity extends BaseMvpActivity<IBaseTeamDetailView, BaseTeamDetailPresenter> implements IBaseTeamDetailView {
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

    public static void start(Context context) {
        Intent starter = new Intent(context, BaseTeamDetailActivity.class);
        context.startActivity(starter);
    }
    private ActBaseTeamDetailListBinding binding;
    private BaseTeamDetailAdapter adapter;
    private List<BaseTeamDetailBean> mList = new ArrayList<>();

    private int type = 1;
    private int page = 1;

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_team_detail_list;
    }
    @Override
    public View attachLayoutView() {
        binding = ActBaseTeamDetailListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        tvTitle.setText("Team Detail");
        initImmersionBar();
        initTabs();
        initRv();
        showLoading();
        getPresenter().getDetailList(type,page);
    }

    private void initRv() {
        adapter = new BaseTeamDetailAdapter();
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
                getPresenter().getDetailList(type,page+1);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh(Constants.RefreshTime, true);
                showLoading();
                getPresenter().getDetailList(type,page);
            }
        });
    }

    private void initTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Level A"), true);
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Level B"));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        type = 1;
                        page = 1;
                        break;
                    case 1:
                        type = 2;
                        page = 1;
                        break;
                }
                getPresenter().getDetailList(type,page);
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
    protected BaseTeamDetailPresenter createPresenter() {
        return new BaseTeamDetailPresenter();
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
    public void onDetailListSuccess(List<BaseTeamDetailBean> list) {

        if (list != null) {
            refreshLayout.finishRefresh(true);
            if (page == 1) {
                mList.clear();
            }
            if (list.size() == 0) {
                refreshLayout.resetNoMoreData();
                rlEmpty.setVisibility(View.VISIBLE);
            } else {
                rlEmpty.setVisibility(View.GONE);
            }
            mList.addAll(list);
        }
    }
}
