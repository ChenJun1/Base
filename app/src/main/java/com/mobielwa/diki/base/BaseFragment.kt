package com.mobielwa.diki.base;

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import com.gyf.immersionbar.components.ImmersionOwner
import com.gyf.immersionbar.components.ImmersionProxy
import com.mobielwa.diki.R
import com.mobielwa.diki.widget.WhiteProgressDialog
import org.greenrobot.eventbus.EventBus

/**
 * @author
 * @date
 * @desc BaseFragment
 */
abstract class BaseFragment : Fragment(), ImmersionOwner {
    /**
     * ImmersionBar代理类
     */
    private val mImmersionProxy = ImmersionProxy(this)
    protected var mWhiteDialog: WhiteProgressDialog? = null
    protected var mRootView: View? = null
    protected var statusBarView: View? = null
    private var mUnbinder: Unbinder? = null
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false

    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun attachLayoutRes(): Int

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化 View
     */
    abstract fun initView(view: View)

    /**
     * 懒加载
     */
    open fun lazyLoad(){}

    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), container, false)
        } else {
            val viewGroup = mRootView!!.getParent() as ViewGroup
            viewGroup?.removeView(mRootView)
        }
        return mRootView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mImmersionProxy.isUserVisibleHint = isVisibleToUser
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUnbinder = ButterKnife.bind(this, view)
        if (useEventBus()) EventBus.getDefault().register(this)
        statusBarView = view.findViewById(R.id.status_bar_view)
        if (mWhiteDialog == null) {
            mWhiteDialog = WhiteProgressDialog(activity)
            mWhiteDialog!!.setCancelable(true)
        }
        isViewPrepare = true
        initView(view)
        initData()
        lazyLoadDataIfPrepared()
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }


    protected open fun showLoading() {
        if (mWhiteDialog != null && !mWhiteDialog!!.isShowing) {
            mWhiteDialog!!.show()
        }
    }

    protected open fun hideLoading() {
        if (mWhiteDialog != null && mWhiteDialog!!.isShowing) {
            mWhiteDialog!!.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        mImmersionProxy.onResume()
    }

    override fun onPause() {
        super.onPause()
        mImmersionProxy.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionProxy.onDestroy()
        mUnbinder?.unbind()
        if (useEventBus()) EventBus.getDefault().unregister(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mImmersionProxy.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mImmersionProxy.onActivityCreated(savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        mImmersionProxy.onHiddenChanged(hidden)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mImmersionProxy.onConfigurationChanged(newConfig)
    }

    /**
     * 是否可以实现沉浸式，当为true的时候才可以执行initImmersionBar方法
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    override fun immersionBarEnabled(): Boolean {
        return true
    }

    override fun onLazyAfterView() {
        TODO("Not yet implemented")
    }

    override fun onInvisible() {
        TODO("Not yet implemented")
    }

    override fun onLazyBeforeView() {
        TODO("Not yet implemented")
    }

    override fun onVisible() {
        TODO("Not yet implemented")
    }

    override fun initImmersionBar() {
        TODO("Not yet implemented")
    }
}