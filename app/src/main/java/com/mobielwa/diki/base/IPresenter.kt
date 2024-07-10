package com.mobielwa.diki.base;

import com.mobielwa.diki.base.IBaseView


/**
 * @Author: June
 * @CreateDate: 2020-04-30 17:01
 * @Description: java类作用描述
 */
interface IPresenter<in V : IBaseView> {
    /**
     * 绑定 View
     */
    fun attachView(mView: V)

    /**
     * 解绑 View
     */
    fun detachView()
}