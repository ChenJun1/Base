package com.mobielwa.diki.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BaseFragment2;
import com.mobielwa.diki.bean.BannerBean;
import com.mobielwa.diki.bean.UserBean;
import com.mobielwa.diki.mvp.iview.IMineView;
import com.mobielwa.diki.mvp.presenter.MinePresenter;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 我的页面
 */
public class MineFragment extends BaseFragment2<IMineView, MinePresenter> implements IMineView {


		@BindView(R.id.tv_phone)
		TextView tvPhone;
		@BindView(R.id.tv_download)
		TextView tvDownload;
		@BindView(R.id.tv_call)
		TextView tvCall;
		@BindView(R.id.banner)
		Banner banner;

    private List<String> listUrls = new ArrayList<String>();
		public static MineFragment newInstance() {

				Bundle args = new Bundle();
				MineFragment fragment = new MineFragment();
				fragment.setArguments(args);
				return fragment;
		}

		@Override
		public int attachLayoutRes() {
				return R.layout.act_meinfo;
		}

		@Override
		protected MinePresenter createPresenter() {
				return new MinePresenter();
		}

		@Override
		public void initView(@NotNull View view) {
        initBanner();

		}
    private void initBanner() {
        listUrls = new ArrayList<>();
        listUrls.add("https://pics3.baidu.com/feed/9345d688d43f8794105499b2ead60ef819d53ad8.jpeg@f_auto?token=d70fe4868ac1356d70833be721199be3");
        listUrls.add("http://pic1.win4000.com/wallpaper/7/584a4d119eef5.jpg");
        banner.setAdapter(new BannerImageAdapter<String>(listUrls) {
            @Override
            public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                Glide.with(getActivity().getApplicationContext()).load(s).into(bannerImageHolder.imageView);
            }
        });
        // 开启自动循环轮播
        banner.isAutoLoop(true);
        //设置指示器    为圆指示器(CircleIndicator)
        banner.setIndicator(new CircleIndicator(getContext()));
        //设置滚动条淡入淡出持续时间
        banner.setScrollBarFadeDuration(1000);
        // 设置指示器颜色(TODO 即选中时那个小点的颜色)
        banner.setIndicatorSelectedColor(Color.GREEN);
        // 开始轮播
        banner.start();
    }

		/**
		 * 消息角标
		 *
		 * @param number
		 */
		private void showBadge(int number) {
		}

		@Override
		protected void lazyLoad() {
		}


		@Override
		public void onMineInfoSuccess(UserBean bean) {


		}

		@Override
		public void onMsgCount(int msgCount) {
		}

		@Override
		public void onResume() {
				super.onResume();
		}

		@Override
		public void onDestroy() {
				super.onDestroy();
				EventBus.getDefault().unregister(this);
		}

		@Override
		public void onHideDialog() {
		}

		@Override
		public void onGetBottomSuccess(List<BannerBean> beanList) {
		}

		@OnClick({R.id.tv_download, R.id.tv_call})
		public void onViewClicked(View view) {
				switch (view.getId()) {
						case R.id.tv_download:
                //        R.layout.act_down_load
								break;
						case R.id.tv_call:
								break;
				}
		}
}
