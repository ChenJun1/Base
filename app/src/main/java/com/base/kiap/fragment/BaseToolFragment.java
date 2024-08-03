package com.base.kiap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.base.kiap.R;
import com.base.kiap.activity.ExchangeDetailActivity;
import com.base.kiap.base.BaseFragment2;
import com.base.kiap.bean.OrderBean;
import com.base.kiap.bean.UsdtIndexBean;
import com.base.kiap.databinding.BaseFrmToolBinding;
import com.base.kiap.listen.onItemClickListen3;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.mvp.presenter.OrderPresenter;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 接单
 */
public class BaseToolFragment extends BaseFragment2<IOrderView, OrderPresenter> implements IOrderView, onItemClickListen3 {


    public static BaseToolFragment newInstance() {

        Bundle args = new Bundle();
        BaseToolFragment fragment = new BaseToolFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private BaseFrmToolBinding binding;

    private ArrayList<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BaseFrmToolBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override

    public int attachLayoutRes() {
        return R.layout.base_frm_tool;
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
        initFragments();
        initTabs();
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        BaseToolFragment01 toolFragment01 = BaseToolFragment01.newInstance();
        BaseToolFragment02 toolFragment02 = BaseToolFragment02.newInstance();

        mFragments.add(toolFragment01);
        mFragments.add(toolFragment02);

        binding.vpTool.setAdapter(new MyAdapter(getFragmentManager()));
        binding.vpTool.setCurrentItem(0);
        binding.vpTool.setOffscreenPageLimit(1);
    }

    private void initTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("UPI"), true);
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Bank"));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpTool.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);//防止销毁fr
        }
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
