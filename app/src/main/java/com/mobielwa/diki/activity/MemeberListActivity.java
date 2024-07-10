package com.mobielwa.diki.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mobielwa.diki.R;
import com.mobielwa.diki.adapter.MemberAdapter;
import com.mobielwa.diki.adapter.MemberListAdapter;
import com.mobielwa.diki.base.BaseMvpActivity;
import com.mobielwa.diki.bean.DialogBean;
import com.mobielwa.diki.bean.MemberBean;
import com.mobielwa.diki.bean.MemberCardBean;
import com.mobielwa.diki.bean.event.Main2OrderEvent;
import com.mobielwa.diki.bean.event.ReshUserEvent;
import com.mobielwa.diki.config.Constants;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.listen.onItemClickListen;
import com.mobielwa.diki.listen.onItemClickListen4;
import com.mobielwa.diki.mvp.iview.IMemberListView;
import com.mobielwa.diki.mvp.iview.IMemberView;
import com.mobielwa.diki.mvp.presenter.MemberListPresenter;
import com.mobielwa.diki.mvp.presenter.MemberPresenter;
import com.mobielwa.diki.utlis.DialogUtlis;
import com.mobielwa.diki.utlis.RecyclerViewLoadUtil;
import com.mobielwa.diki.utlis.StringFormatUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;


/**
 * @Author: June
 * @CreateDate: 3/15/21 2:13 PM
 * @Description: java类作用描述
 */
public class MemeberListActivity extends BaseMvpActivity<IMemberListView, MemberListPresenter> implements IMemberListView {

    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private MemberListAdapter adapter;
    private List<MemberCardBean> mBeanList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MemeberListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.frm_member_list;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.str_pay_card_number2);
        initRv();
        showLoading();
        getPresenter().cardList();
    }

    private void initViewDate() {
        if (mBeanList == null || mBeanList.size() == 0) {
            getPresenter().cardList();
            return;
        }
        adapter.setNewData(mBeanList);
    }

    @Override
    protected MemberListPresenter createPresenter() {
        return new MemberListPresenter();
    }


    private void initRv() {
        adapter = new MemberListAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        homeRv.setLayoutManager(linearLayoutManager);
        RecyclerViewLoadUtil.rvLoad(homeRv);
        homeRv.setAdapter(adapter);
        adapter.setPreLoadNumber(1);
        adapter.setNewData(mBeanList);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            refreshLayout.finishRefresh(Constants.RefreshTime, true);
            showLoading();
            getPresenter().cardList();
        });

        initViewDate();
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
    public void onMemberList(List<MemberCardBean> beanList) {

        if (beanList == null || beanList.size() == 0) {
            return;
        }
        adapter.setNewData(beanList);
    }
}
