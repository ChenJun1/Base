package com.base.kiap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.activity.basea.BasePassActivity;
import com.base.kiap.activity.basea.BasePinCodeActivity;
import com.base.kiap.activity.basea.DepositHistoryActivity;
import com.base.kiap.activity.basea.InBoxActivity;
import com.base.kiap.activity.basea.QuotaHistoryActivity;
import com.base.kiap.activity.basea.ServiceActivity;
import com.base.kiap.activity.basea.UserInfoActivity;
import com.base.kiap.activity.basea.WithdrawHistoryActivity;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.base.BaseUserInfoBean;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.oldbean.UsdtIndexBean;
import com.base.kiap.databinding.BaseFrmAssetsBinding;
import com.base.kiap.mvp.basepresenter.BaseUserInfoPresenter;
import com.base.kiap.mvp.baseviwe.IBaseUserInfoView;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.base.kiap.tool.ActivityManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import q.rorbin.badgeview.QBadgeView;


public class BaseAssetsFragment extends BaseFragment2<IBaseUserInfoView, BaseUserInfoPresenter> implements IBaseUserInfoView {



    private BaseFrmAssetsBinding binding;

    public static BaseAssetsFragment newInstance() {

        Bundle args = new Bundle();
        BaseAssetsFragment fragment = new BaseAssetsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmAssetsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_assets;
    }

    @Override
    protected BaseUserInfoPresenter createPresenter() {
        return new BaseUserInfoPresenter();
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
        binding.llQuota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuotaHistoryActivity.start(getActivity());
            }
        });
        binding.llDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepositHistoryActivity.start(getActivity());
            }
        });
        binding.llWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithdrawHistoryActivity.start(getActivity());
            }
        });
        binding.llService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.start(getActivity());
            }
        });
        binding.llBonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuotaHistoryActivity.start(getActivity());
            }
        });
        binding.llInBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InBoxActivity.start(getActivity());
            }
        });
        binding.llPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasePassActivity.start(getActivity());
            }
        });
        binding.llPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasePinCodeActivity.start(getActivity());
            }
        });
        binding.llOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getAppManager().AppExit(getActivity());
            }
        });
        binding.tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuotaHistoryActivity.start(getActivity());
            }
        });
        binding.llIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoActivity.start(getActivity());
            }
        });
        new QBadgeView(getContext()).bindTarget(binding.ivMsg).setBadgeNumber(5);
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

    @Override
    public void onUserInfo(BaseUserInfoBean bean) {

    }
}
