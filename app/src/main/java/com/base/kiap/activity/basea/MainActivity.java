package com.base.kiap.activity.basea;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.event.Main2MemberEvent;
import com.base.kiap.bean.event.Main2OrderEvent;
import com.base.kiap.bean.event.Ore2HomeEvent;
import com.base.kiap.bean.event.ReshMainEvent;
import com.base.kiap.bean.event.Staus2HomeEvent;
import com.base.kiap.config.AppConfig;
import com.base.kiap.config.ConfigDate;
import com.base.kiap.config.SpCode;
import com.base.kiap.fragment.BaseAssetsFragment;
import com.base.kiap.fragment.BaseDepositFragment;
import com.base.kiap.fragment.BaseHomeFragment;
import com.base.kiap.fragment.BaseTeamFragment;
import com.base.kiap.fragment.BaseToolFragment;
import com.base.kiap.https.retrofit.PostHttp;
import com.base.kiap.tool.ActivityManager;
import com.base.kiap.utlis.AppUtils;
import com.base.kiap.utlis.CommUtils;
import com.base.kiap.utlis.DownloadUtil;
import com.base.kiap.utlis.NLog;
import com.base.kiap.utlis.PathUtils;
import com.base.kiap.utlis.SPUtils;
import com.base.kiap.widget.CustomViewPager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;


public class MainActivity extends BaseMvpActivity {
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.main_home_rb_1)
    RadioButton mainHomeRb1;
    @BindView(R.id.main_home_rb_2)
    RadioButton mainHomeRb2;

    @BindView(R.id.main_home_rb_3)
    RadioButton mainHomeRb3;

    @BindView(R.id.main_home_rb_4)
    RadioButton mainHomeRb4;
    @BindView(R.id.main_home_rb_5)
    RadioButton mainHomeRb5;


    @BindView(R.id.ll_radio)
    LinearLayout llRadio;
    private ArrayList<Fragment> mFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    private PostHttp mPostHttp = new PostHttp();
    private PopupWindow mPopupWindow;//更新下载APP 弹窗

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_main;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        getPermissions();
//        if (!UserHelp.isLogin()||UserHelp.getSTAT()==2) {
//            LoginActivity.start(this);
//            finish();
//            return;
//        }
        getLifecycle().addObserver(mPostHttp);
        initFragments();
//        mPostHttp.findUser();
//        if (!UserHelp.isLogin()||UserHelp.getSTAT()==2) {
//            LoginActivity.start(this);
//            finish();
//            return;
//        }
//        mPostHttp.onSettingFind();
//        checkFristLogn();
//        checkUpdate();
    }




    /**
     * 检查第一次登陆
     */
    private void checkFristLogn() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isFristLogn();
            }
        }, 500);
    }

    /**
     * 检测版本更新
     */
    private void checkUpdate() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (ConfigDate.sConfigBean == null||
                        ConfigDate.sConfigBean.getUpdateContent()==null||ConfigDate.sConfigBean.getAppDownAddr()==null) {
                    return;
                }
                if (ConfigDate.sConfigBean.getAppVersion() >
                        CommUtils.getAppVersionCode(AppConfig.INSTANCE.getApplication())) {
                    if(CommUtils.getAppVersionCode(AppConfig.INSTANCE.getApplication()) < 3){
                        SPUtils.del(SpCode.CARD_NUM);
                    }
                    initAPpUpdateWindow(ConfigDate.sConfigBean.getUpdateContent(), ConfigDate.sConfigBean.getAppDownAddr());
                }
            }
        }, 3000);
    }

    /**
     * 是否是第一次登陆
     */
    private void isFristLogn() {
        boolean isInstall = (boolean) SPUtils.get(SpCode.INSTALL, true);
        if (isInstall) {
            mPostHttp.onInstall();
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        BaseHomeFragment homeFragment = BaseHomeFragment.newInstance();
        BaseDepositFragment depositFragment = BaseDepositFragment.newInstance();
        BaseToolFragment toolFragment = BaseToolFragment.newInstance();
        BaseTeamFragment teamFragment = BaseTeamFragment.newInstance();
        BaseAssetsFragment assetsFragment = BaseAssetsFragment.newInstance();
        mFragments.add(homeFragment);
        mFragments.add(depositFragment);
        mFragments.add(toolFragment);
        mFragments.add(teamFragment);
        mFragments.add(assetsFragment);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(5);
        mainHomeRb1.setSelected(true);
    }

    @OnClick({R.id.main_home_rb_1, R.id.main_home_rb_2,R.id.main_home_rb_3,R.id.main_home_rb_4,
            R.id.main_home_rb_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_home_rb_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.main_home_rb_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_home_rb_3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_home_rb_4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.main_home_rb_5:
                viewPager.setCurrentItem(4);
                break;

        }
    }


    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime < 2000) {
                ActivityManager.getAppManager().finishAllActivity();
                Process.killProcess(Process.myPid());
                System.exit(0);
//                moveTaskToBack(true);
            } else {
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Main2MemberEvent memberEvent) {
        mainHomeRb4.setChecked(true);
        viewPager.setCurrentItem(2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Main2OrderEvent main2OrderEvent) {
        mainHomeRb4.setChecked(true);
        viewPager.setCurrentItem(3);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Ore2HomeEvent ore2HomeEvent) {
        mainHomeRb1.setChecked(true);
        viewPager.setCurrentItem(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Staus2HomeEvent staus2HomeEvent) {
        mainHomeRb2.setChecked(true);
        viewPager.setCurrentItem(1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ReshMainEvent reshMainEvent) {
        mPostHttp.findUser();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    /**
     * @param content
     * @param url
     */
    @SuppressLint("WrongConstant")
    private void initAPpUpdateWindow(String content, final String url) {
        if (url == null || url.isEmpty()) {
            return;
        }
        //加载pop框的视图布局view
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_app_apk_update, null);
        // 创建一个PopupWindow
        // 参数1：contentView 指定PopupWindow的内容
        // 参数2：width 指定PopupWindow的width
        // 参数3：height 指定PopupWindow的height
        mPopupWindow = new PopupWindow(view, MATCH_PARENT, MATCH_PARENT);
        // 需要设置一下此参数，点击外边可消失
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        final WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = 0.5f;
        this.getWindow().setAttributes(lp);
        mPopupWindow.setOutsideTouchable(false);
        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        mPopupWindow.setFocusable(false);
        Button button = view.findViewById(R.id.bt_update);
        Button downLoad = view.findViewById(R.id.bt_download);
        ZzHorizontalProgressBar progressBar = view.findViewById(R.id.tvProgress);

        downLoad.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                if(ConfigDate.sConfigBean != null){
                    Uri uri = Uri.parse(ConfigDate.sConfigBean.getDomainUrl()+"/download/Rich.apk");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else{
                    Uri uri = Uri.parse("https://www.richapp.vip/download/Rich.apk");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean granted) throws Exception {
                                if (granted) {
                                    DownloadUtil.get().download(url, PathUtils.getRootPath(), getString(R.string.app_name) + ".apk", new DownloadUtil.OnDownloadListener() {
                                        @Override
                                        public void onDownloadSuccess(File file) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    AppUtils.Companion.installApk(file);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onDownloading(final int progress) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    if (progress > 0) {
                                                        progressBar.setProgress(progress);
                                                    }
                                                }
                                            });
                                        }

                                        @Override
                                        public void onDownloadFailed(Exception e) {
                                            NLog.INSTANCE.d(e.getMessage());
                                        }
                                    });
                                }
                            }
                        });
            }
        });


        mPopupWindow.showAtLocation(MainActivity.this.findViewById(R.id.activity_main), Gravity.CENTER, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                MainActivity.this.getWindow().setAttributes(lp);
            }
        });


    }

}
