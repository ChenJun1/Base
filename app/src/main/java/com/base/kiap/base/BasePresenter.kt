package com.base.kiap.base;

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleObserver
import com.base.kiap.https.retrofit.NetWorkUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 * @Author: June
 * @CreateDate: 2020-04-27 16:12
 * @Description: java类作用描述
 */
abstract class BasePresenter<V : IBaseView> : IPresenter<V>, LifecycleObserver {
    var mView: V? = null

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun detachView() {
        this.mView = null
        onUnSubscribe()
    }

    override fun attachView(view: V) {
        this.mView=view
    }

    protected fun onUnSubscribe() {
        mCompositeDisposable?.clear()
    }

    @SuppressLint("CheckResult")
    protected fun <T> addSubStriation(observable: Observable<T>?, disposable:DisposableObserver<T>?) {
        if (NetWorkUtil.isConnected()) {
            if (mCompositeDisposable == null) {
                mCompositeDisposable = CompositeDisposable()
            }
            disposable?.let { it -> mCompositeDisposable?.add(it) }

         observable?.let { it.compose { upstream ->
             upstream.subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
         }.subscribeWith(disposable)}

        } else {
            disposable?.onError(Throwable(""))
        }
    }

    fun getView(): V? {
        return mView
    }

}


