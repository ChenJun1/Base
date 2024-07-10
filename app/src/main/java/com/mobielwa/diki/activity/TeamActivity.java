package com.mobielwa.diki.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mobielwa.diki.R;
import com.mobielwa.diki.adapter.TeamAdapter;
import com.mobielwa.diki.base.BaseMvpActivity;
import com.mobielwa.diki.bean.dao.MessageBean;
import com.mobielwa.diki.config.Constants;
import com.mobielwa.diki.mvp.iview.IMessgListView;
import com.mobielwa.diki.mvp.presenter.MessgListPresenter;
import com.mobielwa.diki.utlis.RecyclerViewLoadUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 2/25/21 3:53 PM
 * @Description: Team
 */
public class TeamActivity extends BaseMvpActivity<IMessgListView, MessgListPresenter> implements IMessgListView {
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
        Intent starter = new Intent(context, TeamActivity.class);
        context.startActivity(starter);
    }

    private TeamAdapter adapter;
    private List<MessageBean> mList = new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_team_list;
    }

    @Override
    protected void initData() {
        tvTitle.setText("Team");
        initImmersionBar();
//        showLoading();
        MessageBean bean = new MessageBean();
        mList.add(bean);
        mList.add(bean);
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
                TeamDetailActivity.start(TeamActivity.this);
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
