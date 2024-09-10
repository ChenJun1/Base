package com.base.kiap.fragment;

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
import com.base.kiap.databinding.FrmHomeBinding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.basepresenter.BaseHomePresenter;
import com.base.kiap.mvp.baseviwe.IBaseHomeView;
import com.bumptech.glide.Glide;
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
        initBanner();
        initView();
    }

    private void initView() {
        binding.ivSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSwch) {
                    isSwch = true;
                    binding.ivSw.setImageResource(R.drawable.icon_kai);
                }else{
                    isSwch = false;
                    binding.ivSw.setImageResource(R.drawable.icon_guan);
                }

            }
        });
    }

    private void initBanner() {
        listUrls = new ArrayList<>();
        listUrls.add("https://pics3.baidu.com/feed/9345d688d43f8794105499b2ead60ef819d53ad8.jpeg@f_auto?token=d70fe4868ac1356d70833be721199be3");
        listUrls.add("https://pics3.baidu.com/feed/9345d688d43f8794105499b2ead60ef819d53ad8.jpeg@f_auto?token=d70fe4868ac1356d70833be721199be3");
        listUrls.add("https://pics3.baidu.com/feed/9345d688d43f8794105499b2ead60ef819d53ad8.jpeg@f_auto?token=d70fe4868ac1356d70833be721199be3");
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



    @Override
    public void onItemClick(View v, OrderBean bean) {
    }

    @Override
    public void onBanner(BaseBannerBean bean) {

    }

    @Override
    public void onIndexSuccess(BaseIndexBean bean) {

    }
}
