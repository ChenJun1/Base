package com.base.kiap.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.kiap.R;
import com.base.kiap.activity.ExchangeDetailActivity;
import com.base.kiap.adapter.BaseDeposit01Adapter;
import com.base.kiap.adapter.BaseDepositInrAdapter;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.base.BaseBankInfoBean;
import com.base.kiap.bean.base.BaseWithdrawBean;
import com.base.kiap.bean.base.DepositInrInfoBean;
import com.base.kiap.bean.base.request.InrBuyRequest;
import com.base.kiap.bean.base.request.InrListRequest;
import com.base.kiap.bean.oldbean.DialogBean;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.oldbean.UsdtIndexBean;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.config.Constants;
import com.base.kiap.config.UserHelp;
import com.base.kiap.databinding.BaseFrmDeposit01Binding;
import com.base.kiap.listen.onItemClickListen;
import com.base.kiap.listen.onItemClickListen11;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.basepresenter.BaseDepositInrPresenter;
import com.base.kiap.mvp.baseviwe.IBaseDepositInrtView;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.base.kiap.utlis.CommUtils;
import com.base.kiap.utlis.DialogUtlis;
import com.base.kiap.utlis.NLog;
import com.base.kiap.utlis.RecyclerViewLoadUtil;
import com.base.kiap.utlis.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class DepositFragment01 extends BaseFragment2<IBaseDepositInrtView, BaseDepositInrPresenter> implements IBaseDepositInrtView, onItemClickListen11 {
    private static final String TAG = "DepositFragment01";
    private BaseFrmDeposit01Binding binding;
    private BaseDepositInrAdapter adapter;
    private List<DepositInrInfoBean> mList = new ArrayList<>();

    private int page = 1;
    private InrListRequest request = new InrListRequest();

    private String[] data = {"Form low to high", "Form high to low"};
    private int sortType = 0;

    public static DepositFragment01 newInstance() {

        Bundle args = new Bundle();
        DepositFragment01 fragment = new DepositFragment01();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmDeposit01Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_deposit_01;
    }

    @Override
    protected BaseDepositInrPresenter createPresenter() {
        return new BaseDepositInrPresenter();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void lazyLoad() {
        NLog.INSTANCE.d(TAG, "lazyLoad");
        initRv();
        getPresenter().getInrList(request);
        initViewDate();
    }

    private void initViewDate() {
        binding.llQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String min = binding.edMin.getText().toString();
                String max = binding.edMax.getText().toString();
                request.minAmount = Integer.parseInt(min) * 100L;
                request.maxAmount = Integer.parseInt(max) * 100L;
                request.page = 1;
                getPresenter().getInrList(request);
                showLoading();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortType = position;
                loadData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initRv() {
        adapter = new BaseDepositInrAdapter();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.homeRv.setLayoutManager(linearLayoutManager);
        RecyclerViewLoadUtil.rvLoad(binding.homeRv);
        binding.homeRv.setAdapter(adapter);
        adapter.setPreLoadNumber(1);
        adapter.setNewData(mList);
        binding.refreshLayout.setEnableLoadmore(true);
        binding.refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page = page + 1;
                request.page = page;
                getPresenter().getInrList(request);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                binding.refreshLayout.finishRefresh(Constants.RefreshTime, true);
                request.page = 1;
                getPresenter().getInrList(request);
                showLoading();
            }
        });
        adapter.setListen(this);
    }
    private void showDialog(DepositInrInfoBean  infoBean) {
        DialogBean bean = new DialogBean();
        bean.title = "Tips";
        bean.content = "Confirm purchase of "+ CommUtils.coverMoney(infoBean.amountOrder)  +" INR";
        bean.btntext = "yes";
        bean.btntext2 = "no";
        DialogUtlis.showTaskDialogTis14(getActivity(), bean, new onItemClickListen() {
            @Override
            public void onItemClick(View v) {
                showLoading();
                getPresenter().buy(new InrBuyRequest(infoBean.orderId));
            }

            @Override
            public void onClose() {

            }
        });
    }

    @Override
    public void onResume() {
        NLog.INSTANCE.d(TAG, "onResume");
        binding.tvQuota.setText(CommUtils.coverMoney(UserHelp.getRsBalance()));
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

    @Override
    public void onSuccess(List<DepositInrInfoBean> list) {
        binding.refreshLayout.finishRefresh(true);
        binding.refreshLayout.finishLoadmore();
        if (list.size() == 0) {
            ToastUtil.normal(R.string.str_no_more);
        }
        if (page == 1) {
            mList.clear();
        }
        mList.addAll(list);

        loadData();
    }

    private void loadData() {
        Comparator<DepositInrInfoBean> comparator = new Comparator<DepositInrInfoBean>() {
            @Override
            public int compare(DepositInrInfoBean o1, DepositInrInfoBean o2) {
                if (sortType == 0) {
                    return Long.compare(o1.amountOrder, o2.amountOrder);
                }else{
                    return Long.compare(o2.amountOrder, o1.amountOrder);
                }
            }
        };
        Collections.sort(mList,comparator);

        adapter.setNewData(mList);
    }

    @Override
    public void onBuySuccess() {
        ToastUtil.normal("Buy Success");
    }

    @Override
    public void onItemClick(View v, DepositInrInfoBean bean) {
        showDialog(bean);
    }
}
