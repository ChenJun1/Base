package com.mobielwa.diki.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mobielwa.diki.R;
import com.mobielwa.diki.adapter.ExtractRecordAdapter;
import com.mobielwa.diki.base.BaseMvpActivity;
import com.mobielwa.diki.bean.DialogBean;
import com.mobielwa.diki.bean.ExtractRecordBean;
import com.mobielwa.diki.config.Constants;
import com.mobielwa.diki.listen.onItemClickListen;
import com.mobielwa.diki.mvp.iview.IExtractRecordListView;
import com.mobielwa.diki.mvp.presenter.ExtractRecordPresenter;
import com.mobielwa.diki.utlis.DialogUtlis;
import com.mobielwa.diki.utlis.RecyclerViewLoadUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * @Author: June
 * @CreateDate: 12/21/20 4:30 PM
 * @Description: 提现记录
 */
public class ExtractRecordActivity extends BaseMvpActivity<IExtractRecordListView, ExtractRecordPresenter> implements IExtractRecordListView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;

    public static void start(Context context) {
        Intent starter = new Intent(context, ExtractRecordActivity.class);
        context.startActivity(starter);
    }

    private ExtractRecordAdapter adapter;
    private List<ExtractRecordBean> mList = new ArrayList<>();
    private int page = 1;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_extract_record;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.str_tixianjl);
        initImmersionBar();
        showLoading();
        initRv();
        getPresenter().onWithdrawal(page);
    }

    @Override
    protected ExtractRecordPresenter createPresenter() {
        return new ExtractRecordPresenter();
    }

    private void initRv() {
        adapter = new ExtractRecordAdapter();
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
                refreshLayout.finishLoadmore(Constants.RefreshTime, true);
                page++;
                showLoading();
                getPresenter().onWithdrawal(page);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh(Constants.RefreshTime, true);
                page = 1;
                showLoading();
                getPresenter().onWithdrawal(page);
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ExtractRecordBean extractRecordBean = mList.get(position);
                DialogBean diabean = new DialogBean();
                if (extractRecordBean.getStatus() == 0) {//提现中
                    diabean.title = getString(R.string.str_extr_0);
                    diabean.content = getString(R.string.str_extr_4);
                    diabean.btntext = getString(R.string.str_extr_9);
                    DialogUtlis.showTaskDialog(ExtractRecordActivity.this, diabean, null);
                } else if (extractRecordBean.getStatus() == 1) {//提现成功
                    diabean.title = getString(R.string.str_extr_1);
                    diabean.content = getString(R.string.str_extr_5);
                    diabean.btntext = getString(R.string.str_extr_9);
                    DialogUtlis.showTaskDialog(ExtractRecordActivity.this, diabean, null);
                } else if (extractRecordBean.getStatus() == 2) {//提现失败
                    diabean.title = getString(R.string.str_extr_2);
                    if (extractRecordBean.getFailureStatus() == 1) {
                        diabean.content = getString(R.string.str_extr_6);
                    } else if (extractRecordBean.getFailureStatus() == 2) {
                        if(extractRecordBean.getRemark() != null && extractRecordBean.getRemark().length() > 0){
                            diabean.content = extractRecordBean.getRemark();
                        }else{
                            diabean.content = getString(R.string.str_extr_7);
                        }
                    } else if (extractRecordBean.getFailureStatus() == 3) {
                        if(extractRecordBean.getRemark() != null && extractRecordBean.getRemark().length() > 0){
                            diabean.content = extractRecordBean.getRemark();
                        }else{
                            diabean.content = getString(R.string.str_extr_7_1);
                        }
                    }
                    diabean.btntext = getString(R.string.str_extr_8);

                    DialogUtlis.showTaskDialogExtract2(ExtractRecordActivity.this, diabean, new onItemClickListen() {
                        @Override
                        public void onItemClick(View v) {
                            if (extractRecordBean.getStatus() == 2) {
                                CashRecordActivity.start(ExtractRecordActivity.this);
                            }
                        }

                        @Override
                        public void onClose() {

                        }
                    });
                }

            }
        });
    }


    @Override
    public void onSuccess(List<ExtractRecordBean> recordBeanList) {
        if (recordBeanList != null) {
            if (page == 1) {
                refreshLayout.finishRefresh(true);
                mList.clear();
                if (recordBeanList.size() == 0) {
                    refreshLayout.resetNoMoreData();
                    rlEmpty.setVisibility(View.VISIBLE);
                } else {
                    rlEmpty.setVisibility(View.GONE);
                }
            } else {
                refreshLayout.finishLoadmore(true);
            }
            mList.addAll(recordBeanList);
            adapter.setNewData(mList);
        }
    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }



    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        showBadge();
    }
}
