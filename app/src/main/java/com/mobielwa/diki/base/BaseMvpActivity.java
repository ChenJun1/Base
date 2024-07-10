package com.mobielwa.diki.base;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.mobielwa.diki.R;
import com.mobielwa.diki.tool.ActivityManager;
import com.mobielwa.diki.utlis.MultiClickHelper;
import com.mobielwa.diki.utlis.languagelib.MultiLanguageUtil;
import com.mobielwa.diki.utlis.permission.OnRequestPermissionListener;
import com.mobielwa.diki.utlis.permission.PermissionRequestHelper;
import com.mobielwa.diki.widget.WhiteProgressDialog;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseMvpActivity<V extends IBaseView, P extends BasePresenter<V>> extends AppCompatActivity {

    private P mPresenter;
    private Unbinder mUnbinder;

    protected WhiteProgressDialog mWhiteDialog;

    private View statusBarView;
    protected MultiClickHelper mClickHelper=new MultiClickHelper();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (attachLayoutRes()!=0) {
            setContentView(attachLayoutRes());
        }
        statusBarView = findViewById(R.id.status_bar_view);

        ActivityManager.getAppManager().addActivity(this);

        mUnbinder = ButterKnife.bind(this);
        //基类设置默认值,这里是非沉浸,状态栏颜色值白色,字体颜色为黑色。
        setStatusColor();
        setSystemInvadeBlack();
        //设置状态栏白底黑字

        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        //判空防止继承AbsBaseActivity的类没有返回mPresenter
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }

        if (mWhiteDialog == null) {
            mWhiteDialog = new WhiteProgressDialog(this);
            mWhiteDialog.setCancelable(true);
        }
        if (mPresenter!=null) {
            getLifecycle().addObserver(mPresenter);
        }
        initData();

    }

    protected View getStatusView() {
        return statusBarView;
    }


    protected void setStatusColor() {

    }

    protected void setSystemInvadeBlack() {

    }

    @LayoutRes
    protected abstract int attachLayoutRes();

    protected abstract void initData();

    protected abstract P createPresenter();

    protected P getPresenter() {
        return mPresenter;
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        if (statusBarView != null) {
            ImmersionBar.with(this).statusBarView(getStatusView()).init();
        }else {
            ImmersionBar.with(this).statusBarColor(R.color.white_fa).init();
        }
    }

    protected void showLoading() {
        if (mWhiteDialog != null && !mWhiteDialog.isShowing()) {
            mWhiteDialog.show();
        }
    }

    protected void hideLoading() {
        if (mWhiteDialog != null && mWhiteDialog.isShowing()) {
            mWhiteDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mWhiteDialog != null) {
            mWhiteDialog.dismiss();
            mWhiteDialog = null;
        }
    }


    public void setCancleBack(boolean systemBack, boolean outsizeBack) {
        if (mWhiteDialog != null) {
            mWhiteDialog.setCancelable(systemBack);
            mWhiteDialog.setCanceledOnTouchOutside(outsizeBack);
        }
    }

    /**
     * 点击屏幕除TextView区域软键盘自动消失
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return super.dispatchTouchEvent(ev);
            }
            // 必不可少，否则所有的组件都不会有TouchEvent了
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
            return onTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取权限
     */
    public void getPermissions() {
        new PermissionRequestHelper()
                .requestPermissions(this, new OnRequestPermissionListener() {
                            @Override
                            public void onRequestSuccess() {
                                //请求成功
                            }

                            @Override
                            public void onRequestFailed() {
                            }
                        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE);
    }
}
