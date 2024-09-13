package com.base.kiap.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.kiap.R;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.base.BaseBannerBean;
import com.base.kiap.bean.base.BaseIndexBean;
import com.base.kiap.config.Constants;
import com.base.kiap.databinding.FrmHomeBinding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.basepresenter.BaseHomePresenter;
import com.base.kiap.mvp.baseviwe.IBaseHomeView;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BaseHomeFragment extends BaseFragment2<IBaseHomeView, BaseHomePresenter> implements IBaseHomeView, onItemClickListen3 {


    private FrmHomeBinding binding;

    private List<String> listUrls = new ArrayList<String>();

    public static BaseHomeFragment newInstance() {

        Bundle args = new Bundle();
        BaseHomeFragment fragment = new BaseHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private boolean isSwch = false;

    @Override
    public int attachLayoutRes() {
        return R.layout.frm_home;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FrmHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected BaseHomePresenter createPresenter() {
        return new BaseHomePresenter();
    }

    @Override
    public void initView(@NotNull View view) {

    }

    @Override
    protected void lazyLoad() {
        initView();
    }

    private void initView() {
        binding.ivSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSwch) {
                    isSwch = true;
                    binding.ivSw.setImageResource(R.drawable.icon_kai);
                    getPresenter().onWithdrawSwitch(1);
                }else{
                    isSwch = false;
                    binding.ivSw.setImageResource(R.drawable.icon_guan);
                    getPresenter().onWithdrawSwitch(2);
                }

            }
        });
        binding.refreshLayout.setEnableLoadmore(false);
        binding.refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                binding.refreshLayout.finishRefresh(Constants.RefreshTime, true);
                getPresenter().findIndex();
            }
        });
        getPresenter().banner();
    }

    private void initBanner() {
        binding.banner.setAdapter(new BannerImageAdapter<String>(listUrls) {
            @Override
            public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                Glide.with(getActivity().getApplicationContext()).load(s).into(bannerImageHolder.imageView);
            }
        });
        // 开启自动循环轮播
        binding.banner.isAutoLoop(true);
        //设置指示器    为圆指示器(CircleIndicator)
        binding.banner.setIndicator(new CircleIndicator(getContext()));
        //设置滚动条淡入淡出持续时间
        binding.banner.setScrollBarFadeDuration(1000);
        // 设置指示器颜色(TODO 即选中时那个小点的颜色)
        binding.banner.setIndicatorSelectedColor(Color.GREEN);
        // 开始轮播
        binding.banner.start();

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
    public void onResume() {
        super.onResume();
        getPresenter().findIndex();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    private void handleData(BaseIndexBean bean) {
        if (bean.withdrawSwitch == 1) {
            binding.ivSw.setImageDrawable(getResources().getDrawable(R.drawable.icon_kai));
            binding.tvSwStatus.setText(" (Opening)");
        }else{
            binding.ivSw.setImageDrawable(getResources().getDrawable(R.drawable.icon_guan));
            binding.tvSwStatus.setText(" (Closing)");
        }
        binding.inTransactionWithdrawAmountTotal.setText(bean.inTransactionWithdrawAmountTotal+"");
        binding.todayWithdrawSuccessAmountTotal.setText(bean.todayWithdrawSuccessAmountTotal+"");
        binding.tvUpiTools.setText(bean.upiTools+"");
        binding.tvBankTools.setText(bean.bankTools+"");
        BaseIndexBean.NewbieTaskActivityDTO newbieTaskActivity = bean.newbieTaskActivity;
        if (newbieTaskActivity != null) {
            binding.tvAmount.setText(newbieTaskActivity.rechargeTotal+"");
            binding.tvDay.setText(newbieTaskActivity.day+"");
            if (newbieTaskActivity.rewardList != null && newbieTaskActivity.rewardList.size() >= 5) {
                binding.tvMoney1.setText(newbieTaskActivity.rewardList.get(0)/100+"");
                binding.tvMoney2.setText(newbieTaskActivity.rewardList.get(1)/100+"");
                binding.tvMoney3.setText(newbieTaskActivity.rewardList.get(2)/100+"");
                binding.tvMoney4.setText(newbieTaskActivity.rewardList.get(3)/100+"");
                binding.tvMoney5.setText(newbieTaskActivity.rewardList.get(4)/100+"");
            }
            if (newbieTaskActivity.targetList != null && newbieTaskActivity.targetList.size() >= 5) {
                binding.tvMoneyK1.setText((newbieTaskActivity.targetList.get(0)/1000)+"k");
                binding.tvMoneyK2.setText(newbieTaskActivity.targetList.get(1)/1000+"k");
                binding.tvMoneyK3.setText(newbieTaskActivity.targetList.get(2)/1000+"k");
                binding.tvMoneyK4.setText(newbieTaskActivity.targetList.get(3)/1000+"k");
                binding.tvMoneyK5.setText(newbieTaskActivity.targetList.get(4)/1000+"k");
            }
        }
    }

    @Override
    public void onItemClick(View v, OrderBean bean) {
    }


    @Override
    public void onIndexSuccess(BaseIndexBean bean) {
        handleData(bean);
    }

    @Override
    public void onBannerSuccess(List<BaseBannerBean> bean) {
        listUrls.add("https://pics3.baidu.com/feed/9345d688d43f8794105499b2ead60ef819d53ad8.jpeg@f_auto?token=d70fe4868ac1356d70833be721199be3");
        listUrls.add("https://pics3.baidu.com/feed/9345d688d43f8794105499b2ead60ef819d53ad8.jpeg@f_auto?token=d70fe4868ac1356d70833be721199be3");
        listUrls.add("https://pics3.baidu.com/feed/9345d688d43f8794105499b2ead60ef819d53ad8.jpeg@f_auto?token=d70fe4868ac1356d70833be721199be3");
        listUrls.clear();
        for (BaseBannerBean baseBannerBean : bean) {
            listUrls.add(baseBannerBean.imgUrl);
        }
        initBanner();
    }

    @Override
    public void onWithdrawSwitchSuccess() {
        getPresenter().findIndex();
    }
}
