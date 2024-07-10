package com.mobielwa.diki.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;
import com.gyf.immersionbar.components.SimpleImmersionProxy;
import com.mobielwa.diki.R;
import com.mobielwa.diki.utlis.MultiClickHelper;
import com.mobielwa.diki.widget.WhiteProgressDialog;
import org.greenrobot.eventbus.EventBus;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: June
 * @CreateDate: 12/11/20 4:10 PM
 * @Description: java类作用描述
 */
public abstract class BaseFragment2<V extends IBaseView, P extends BasePresenter<V>>  extends Fragment implements SimpleImmersionOwner {
    /**
     * ImmersionBar代理类
     */
    private SimpleImmersionProxy mSimpleImmersionProxy = new SimpleImmersionProxy(this);
    protected WhiteProgressDialog mWhiteDialog;
    protected View mRootView;
    protected View statusBarView;
    private Unbinder mUnbinder;
    protected Activity mActivity;
    private P mPresenter;
    protected MultiClickHelper mClickHelper=new MultiClickHelper();
    /**
     * 视图是否加载完毕
     */
    private boolean isViewPrepare = false;

    /**
     * 数据是否加载过了
     */
    private boolean hasLoadData = false;

    /**
     * 加载布局
     */

    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 初始化数据
     */


    protected void initData(){};

    /**
     * 初始化 View
     */
    protected void initView(View view){};

    /**
     * 懒加载
     */

    protected abstract void lazyLoad();

    /**
     * 是否使用 EventBus
     */

    protected boolean useEventBus() {
        return false;
    }
    protected abstract P createPresenter();

    protected P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), container, false);
        }
        return mRootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mSimpleImmersionProxy.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mRootView == null) {
            mRootView = view;
        }
        if (mWhiteDialog == null) {
            mWhiteDialog = new WhiteProgressDialog(getActivity());
            mWhiteDialog.setCancelable(true);
        }
        mUnbinder = ButterKnife.bind(this, view);
        if (useEventBus()) EventBus.getDefault().register(this);
        statusBarView = view.findViewById(R.id.status_bar_view);

        if (mWhiteDialog == null) {
            mWhiteDialog = new WhiteProgressDialog(mActivity);
            mWhiteDialog.setCancelable(true);
        }
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        //判空防止继承BaseActivity的类没有返回mPresenter
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
            getLifecycle().addObserver(mPresenter);
        }

        isViewPrepare = true;
        initView(view);
        initData();
        lazyLoadDataIfPrepared();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    void lazyLoadDataIfPrepared() {
        if (getUserVisibleHint() && isViewPrepare && !hasLoadData) {
            lazyLoad();
            hasLoadData = true;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSimpleImmersionProxy.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSimpleImmersionProxy.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mSimpleImmersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mSimpleImmersionProxy.onConfigurationChanged(newConfig);
    }

    /**
     * 是否可以实现沉浸式，当为true的时候才可以执行initImmersionBar方法
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean immersionBarEnabled() {
        return true;
    }

    @Override
    public void initImmersionBar() {
        if (statusBarView != null) {
            ImmersionBar.with(this)
                    .statusBarView(statusBarView)
                    .autoDarkModeEnable(true)
                    .init();
        }else{
            ImmersionBar.with(this).keyboardEnable(true).init();
        }

    }
}
