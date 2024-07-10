package com.mobielwa.diki.base;

import android.view.View

/**
 * @author chenxz
 * @date 2018/11/19
 * @desc BaseMvpFragment
 */
abstract class BaseMvpFragment<in V : IBaseView, P : IPresenter<V>> : BaseFragment() {

    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView(view: View) {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
        this.mPresenter = null
    }

}