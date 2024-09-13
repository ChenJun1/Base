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
import com.base.kiap.activity.basea.AddBankActivity;
import com.base.kiap.activity.basea.EditBankActivity;
import com.base.kiap.adapter.BaseDeposit01Adapter;
import com.base.kiap.adapter.BaseTool02Adapter;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.base.BaseBankInfoBean;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.bean.oldbean.DialogBean;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.oldbean.UsdtIndexBean;
import com.base.kiap.config.Constants;
import com.base.kiap.databinding.BaseFrmTool02Binding;
import com.base.kiap.listen.onItemClickListen;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.basepresenter.BaseBankListPresenter;
import com.base.kiap.mvp.baseviwe.IBaseBankListView;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.base.kiap.utlis.DialogUtlis;
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
public class BaseToolFragment02 extends BaseFragment2<IBaseBankListView, BaseBankListPresenter> implements IBaseBankListView, onItemClickListen3 {

    private BaseFrmTool02Binding binding;

    public static BaseToolFragment02 newInstance() {

        Bundle args = new Bundle();
        BaseToolFragment02 fragment = new BaseToolFragment02();
        fragment.setArguments(args);
        return fragment;
    }

    private List<BaseBankInfoBean> mList = new ArrayList<>();
    private BaseTool02Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmTool02Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_tool_02;
    }

    @Override
    protected BaseBankListPresenter createPresenter() {
        return new BaseBankListPresenter();
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
        binding.llAddBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBankActivity.start(getActivity());
            }
        });
        initRv();
    }

    private void initRv() {
        adapter = new BaseTool02Adapter();
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
                getPresenter().onBankList();
                showLoading();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EditBankActivity.start(getActivity(),mList.get(position));
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showDialog(mList.get(position));
                return false;
            }
        });
    }

    private void showDialog(BaseBankInfoBean bankInfoBean) {
        DialogBean bean = new DialogBean();
        bean.title = "Tips";
        bean.content = "Are you sure to delete the bank card?";
        bean.btntext = "yes";
        bean.btntext2 = "no";
        DialogUtlis.showTaskDialogTis14(getActivity(), bean, new onItemClickListen() {
            @Override
            public void onItemClick(View v) {
                getPresenter().onDeleteBank(bankInfoBean);
                showLoading();
            }

            @Override
            public void onClose() {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onBankList();
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

    @Override
    public void onSuccess(List<BaseBankInfoBean> list) {
        binding.refreshLayout.finishRefresh(true);
        binding.refreshLayout.resetNoMoreData();
        if (list != null) {
            mList.clear();
            mList.addAll(list);
        }
        adapter.setNewData(mList);
    }

    @Override
    public void onDeleteSuccess() {
        getPresenter().onBankList();
    }
}
