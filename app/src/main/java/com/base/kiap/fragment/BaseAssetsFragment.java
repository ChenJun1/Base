package com.base.kiap.fragment;

import static com.base.kiap.utlis.SPUtils.put;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.activity.basea.BaseLoginActivity;
import com.base.kiap.activity.basea.BasePassActivity;
import com.base.kiap.activity.basea.BasePinCodeActivity;
import com.base.kiap.activity.basea.DepositHistoryActivity;
import com.base.kiap.activity.basea.InBoxActivity;
import com.base.kiap.activity.basea.QuotaHistoryActivity;
import com.base.kiap.activity.basea.ServiceActivity;
import com.base.kiap.activity.basea.BaseUserInfoActivity;
import com.base.kiap.activity.basea.WithdrawHistoryActivity;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.base.BaseUserInfoBean;
import com.base.kiap.config.AppConfig;
import com.base.kiap.config.SpCode;
import com.base.kiap.config.UserHelp;
import com.base.kiap.databinding.BaseFrmAssetsBinding;
import com.base.kiap.mvp.basepresenter.BaseUserInfoPresenter;
import com.base.kiap.mvp.baseviwe.IBaseUserInfoView;
import com.base.kiap.tool.ActivityManager;
import com.base.kiap.utlis.CommUtils;
import com.base.kiap.utlis.SPUtils;
import com.base.kiap.utlis.ToastUtil;

import org.greenrobot.eventbus.EventBus;

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
                getPresenter().onOut();
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
                BaseUserInfoActivity.start(getActivity());
            }
        });
        binding.tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommUtils.copy(binding.tvPhone.getText().toString());
                ToastUtil.success("Copy Success");
            }
        });

        binding.tvUserId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommUtils.copy(binding.tvUserId.getText().toString());
                ToastUtil.success("Copy Success");
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onUserInfo();
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

    @SuppressLint("SetTextI18n")
    private void handleData(BaseUserInfoBean bean) {
        binding.tvPhone.setText(bean.phone);
        binding.tvUserId.setText(bean.userId + "");
        binding.tvRatio.setText(bean.rewardRadio+"");
        binding.balance.setText(CommUtils.coverMoney(bean.balance));
        binding.todayEarning.setText(CommUtils.coverMoney(bean.todayEarning));
        new QBadgeView(getContext()).bindTarget(binding.ivMsg).setBadgeNumber(bean.unReadMessageNum);
    }

    @Override
    public void onUserInfo(BaseUserInfoBean bean) {
        handleData(bean);
        UserHelp.updateBaseUser(bean);
    }

    @Override
    public void onOutSuccess() {
        SPUtils.clear();
        Context context = AppConfig.INSTANCE.getApplication();
        Intent intent = new Intent();
        intent.setClass(context, BaseLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
