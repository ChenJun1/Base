package com.base.kiap.fragment;

import android.annotation.SuppressLint;
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
import com.base.kiap.bean.base.BaseTeamIndexInfoBean;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.oldbean.UsdtIndexBean;
import com.base.kiap.databinding.BaseFrmTeamBinding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.basepresenter.BaseTeamPresenter;
import com.base.kiap.mvp.baseviwe.IBaseTeamView;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.base.kiap.utlis.CommUtils;
import com.base.kiap.utlis.DialogUtlis;
import com.base.kiap.utlis.ShareUtils;
import com.base.kiap.utlis.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class BaseTeamFragment extends BaseFragment2<IBaseTeamView, BaseTeamPresenter> implements IBaseTeamView, onItemClickListen3 {



    private BaseFrmTeamBinding binding;
    private BaseTeamIndexInfoBean indexInfoBean;

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
    protected BaseTeamPresenter createPresenter() {
        return new BaseTeamPresenter();
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
            if (indexInfoBean != null) {
                ShareUtils.shareTelegram(indexInfoBean.telegramLink);
            }
        });
        binding.llFacebook.setOnClickListener(v -> {
            if (indexInfoBean != null) {
                ShareUtils.shareCommonFacebook(indexInfoBean.facebookLink);
            }
        });
        binding.llWhatapp.setOnClickListener(v -> {
            if (indexInfoBean != null) {
                ShareUtils.shareWhat(indexInfoBean.whatsappLink);
            }
        });
        binding.llQr.setOnClickListener(v -> {
            if (indexInfoBean != null) {
                DialogUtlis.showShareDialog(getActivity());
            }
        });
        binding.llShare.setOnClickListener(v -> {
            if (indexInfoBean != null) {
                DialogUtlis.showShareDialog(getActivity());
            }
        });
        binding.ivCp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommUtils.copy(binding.invitationLink.getText().toString());
                ToastUtil.success("Copy Success");
            }
        });
    }


    @SuppressLint("SetTextI18n")
    private void handleData(BaseTeamIndexInfoBean bean) {
        binding.tvMoney.setText(bean.todayTeamDeposit+"");
        binding.tvTotalMoney.setText(bean.totalTeamDeposit+"");
        binding.myTotalCommissions.setText(bean.myTotalCommissions+"");
        binding.myYesterdayCommissions.setText(bean.myYesterdayCommissions+"");
        binding.myTodayCommissions.setText("+ " + bean.myTodayCommissions+"");
        binding.teamCount.setText(bean.teamCount+"");
        binding.todayNewTeam.setText("+ "+bean.todayNewTeam+"");
        binding.invitationLink.setText(bean.invitationLink);
        binding.tvLevel1Start.setText(bean.levelAUserNum + "");
        binding.tvLevel1Data.setText(bean.levelARewardRadio + "%");
        binding.tvLevel1End1.setText(bean.myLevelAReward + "");
        binding.tvLevel2Start.setText(bean.levelBUserNum + "");
        binding.tvLevel2Data.setText(bean.levelBRewardRadio + "%");
        binding.tvLevel2End1.setText(bean.myLevelBReward + "");
    }
    @Override
    public void onIndexSuccess(BaseTeamIndexInfoBean bean) {
        indexInfoBean = bean;
        handleData(indexInfoBean);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().findIndex();
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
