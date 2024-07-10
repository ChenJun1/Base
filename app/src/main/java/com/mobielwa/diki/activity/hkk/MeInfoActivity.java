package com.mobielwa.diki.activity.hkk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BaseActivity;
import com.mobielwa.diki.config.UserHelp;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/21/20 3:39 PM
 * @Description: 个人信息
 */
public class MeInfoActivity extends BaseActivity {

		@BindView(R.id.tv_phone)
		TextView tvPhone;
		@BindView(R.id.tv_download)
		TextView tvDownload;
		@BindView(R.id.tv_call)
		TextView tvCall;
		@BindView(R.id.banner)
		Banner banner;

		private List<String> listUrls = new ArrayList<String>();

		public static void start(Context context) {
				Intent starter = new Intent(context, MeInfoActivity.class);
				context.startActivity(starter);
		}

		@Override
		protected int attachLayoutRes() {
				return R.layout.act_meinfo;
		}

		@Override
		protected void initData() {
				tvPhone.setText(UserHelp.getPhone() + "");
        initBanner();
    }

		private void initBanner() {
				listUrls = new ArrayList<>();
        listUrls.add("https://pics3.baidu.com/feed/9345d688d43f8794105499b2ead60ef819d53ad8.jpeg@f_auto?token=d70fe4868ac1356d70833be721199be3");
				banner.setAdapter(new BannerImageAdapter<String>(listUrls) {
						@Override
						public void onBindView(BannerImageHolder bannerImageHolder, String s, int i, int i1) {
                Glide.with(getApplicationContext()).load(s).into(bannerImageHolder.imageView);
						}
				});
				// 开启自动循环轮播
				banner.isAutoLoop(true);
				//设置指示器    为圆指示器(CircleIndicator)
				banner.setIndicator(new CircleIndicator(getBaseContext()));
				//设置滚动条淡入淡出持续时间
				banner.setScrollBarFadeDuration(1000);
				// 设置指示器颜色(TODO 即选中时那个小点的颜色)
				banner.setIndicatorSelectedColor(Color.GREEN);
				// 开始轮播
				banner.start();

		}


		@OnClick({R.id.tv_download, R.id.tv_call})
		public void onViewClicked(View view) {
				if (mClickHelper.click()) {
						return;
				}
				switch (view.getId()) {
						case R.id.tv_download:
//        R.layout.act_down_load
								break;
						case R.id.tv_call:
								break;
				}
		}

}
