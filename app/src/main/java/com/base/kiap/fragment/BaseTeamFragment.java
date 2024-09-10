package com.base.kiap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.activity.ExchangeDetailActivity;
import com.base.kiap.activity.basea.BaseTeamDetailActivity;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.oldbean.UsdtIndexBean;
import com.base.kiap.databinding.BaseFrmTeamBinding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.base.kiap.utlis.DialogUtlis;
import com.base.kiap.utlis.ShareUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class BaseTeamFragment extends BaseFragment2<IOrderView, OrderPresenter> implements IOrderView, onItemClickListen3 {



    private BaseFrmTeamBinding binding;

    public static BaseTeamFragment newInstance() {

        Bundle args = new Bundle();
        BaseTeamFragment fragment = new BaseTeamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmTeamBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_team;
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
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
        binding.tvView.setOnClickListener(v -> {
            BaseTeamDetailActivity.start(getActivity());
        });
        binding.llTelegram.setOnClickListener(v -> {
            ShareUtils.shareTelegram("");
        });
        binding.llFacebook.setOnClickListener(v -> {
            ShareUtils.shareCommonFacebook("");
        });
        binding.llWhatapp.setOnClickListener(v -> {
            ShareUtils.shareWhat("");
        });
        binding.llQr.setOnClickListener(v -> {
            DialogUtlis.showShareDialog(getActivity());
        });
        binding.llShare.setOnClickListener(v -> {
            DialogUtlis.showShareDialog(getActivity());
        });

    }


    @Override
    public void onIndex(UsdtIndexBean bean) {

    }

    @Override
    public void onGetOrderSuccess(List<OrderBean> orderBeanList) {

    }

    @Override
    public void onChangelv() {
    }

    @Override
    public void onResume() {
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

    //接单点击
    @Override
    public void onItemClick(View v, OrderBean bean) {
        ExchangeDetailActivity.start(mActivity, bean);
    }

}
