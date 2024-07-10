package com.mobielwa.diki.https.retrofit;
import android.annotation.SuppressLint
import android.util.Log
import com.mobielwa.diki.utlis.NLog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * @Author: June
 * @CreateDate: 2020-06-16 21:00
 * @Description: java类作用描述
 */
public class ApiSuStriation {
    private var mCompositeDisposable: CompositeDisposable? = null
    val mNoNetWork = "No Net Work"


    fun onUnSubscribe() {
        mCompositeDisposable?.clear()
        NLog.d("-----onUnSubscribe")
    }

    @SuppressLint("CheckResult")
    public fun <T> addSubStriation(observable: Observable<T>?, disposable: DisposableObserver<T>?) {
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
            disposable?.onError(Throwable(mNoNetWork))
        }
    }
}