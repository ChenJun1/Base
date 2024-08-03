package com.base.kiap.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.base.kiap.BuildConfig;
import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.base.BasePresenter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/31/20 4:21 PM
 * @Description: java类作用描述
 */
public class WebActivity extends BaseMvpActivity {

    public static final String URLCODE = "url_code";

    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.llPlay)
    LinearLayout llPlay;


    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra(URLCODE, url);
        context.startActivity(starter);
    }

    private AgentWeb mAgentWeb;
    private String url;


    @Override
    protected int attachLayoutRes() {
        return R.layout.act_webview;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.str_chongzhi);
        url = getIntent().getStringExtra(URLCODE);
        showLoading();
        initWebView1(url);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void initWebView1(String url1) {
//        WebView.loadUrl(url1);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llPlay, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .setWebChromeClient(webChromeClient)
                .setWebViewClient(webViewClient)
                .createAgentWeb()
                .ready()
                .go(url);
        mAgentWeb.getWebCreator().getWebView().loadUrl(url1);
    }

    private void initWebView() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llPlay, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .setWebChromeClient(webChromeClient)
                .setWebViewClient(webViewClient)
                .createAgentWeb()
                .ready()
                .go(url);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAgentWeb.getWebCreator().getWebView().getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

//        mAgentWeb.getWebCreator().getWebView().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {//禁止长按复制
//                return true;
//            }
//        });
        WebSettings webSettings = mAgentWeb.getAgentWebSettings().getWebSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setDomStorageEnabled(true);//开启DOM storage API功能
        webSettings.setDatabaseEnabled(true);//开启database storeage API功能
        String cacheDirPath = this.getFilesDir().getAbsolutePath() + "/webcache";//缓存路径
        webSettings.setDatabasePath(cacheDirPath);//设置数据库缓存路径
        webSettings.setAppCachePath(cacheDirPath);//设置AppCaches缓存路径
        webSettings.setAppCacheEnabled(true);//开启AppCaches功能
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        if (Build.VERSION.SDK_INT >= 16) {
            Class<?> clazz = webSettings.getClass();
            Method method = null;
            try {
                method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (method != null) {
                try {
                    method.invoke(webSettings, true);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        //开启调试
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mAgentWeb.getWebCreator().getWebView().setWebContentsDebuggingEnabled(true);
        }

//        AppUtils.Companion.MD5Sign("amount=10.00&orderid=20210111123&pay=zfb&shid=sh01&url=www.baidu.com&key=8888888");
//        String sign = AppUtils.Companion.MD5Sign("amount=10.00&orderid=2019042212547782329&pay=zfb&shid=happyEarn01&url=http://47.116.76.202:8899/getnenePay/callback&key=8CU7KC3OZ7DNWB86CV6VYIFINQ7356FK");
//        String str = "shid=happyEarn01&sign=" + sign +
//                "&orderid=2019042212547782329&amount=10.00&pay=zfb&url=http://47.116.76.202:8899/getnenePay/callback";
//        mAgentWeb.getWebCreator().getWebView().postUrl("http://getnene.com/app/apiss",str.getBytes());

    }


    WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);//屏蔽下载
        }
    };

    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showLoading();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            hideLoading();
        }

    };

    /**
     * 拦截物理返回键
     *
     * @param keyCode:
     * @param event:
     * @return:
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mAgentWeb.getWebCreator().getWebView().canGoBack()) {
            mAgentWeb.getWebCreator().getWebView().goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    public void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.clearWebCache();
            mAgentWeb.getWebLifeCycle().onDestroy();
            mAgentWeb = null;
        }
        super.onDestroy();
    }

}
