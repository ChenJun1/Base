package com.base.kiap.https.retrofit;


import android.widget.Toast;

import com.base.kiap.BuildConfig;
import com.base.kiap.R;
import com.base.kiap.config.AppConfig;
import com.base.kiap.bean.request.BaseResult;

import java.net.SocketException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * 把Model传进来，进行网路状态的判断
 *
 * @param <M>
 */
public abstract class ApiCallBack<M> extends DisposableObserver<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public void onFinish() {
    }

    public static final int NETWORD_504 = 504;
    public static final int NETWORD_502 = 502;
    public static final int NETWORD_404 = 404;

    @Override
    public void onNext(M m) {
        Toast.makeText(AppConfig.INSTANCE.getApplication(), "onSuccess error", Toast.LENGTH_SHORT).show();
        try {
            BaseResult baseResult = (BaseResult) m;
            if (baseResult.isSuccess()) {
                Toast.makeText(AppConfig.INSTANCE.getApplication(), "onSuccess error", Toast.LENGTH_SHORT).show();
                onSuccess(m);
                return;
            } else if (baseResult.status == 500) {
                Toast.makeText(AppConfig.INSTANCE.getApplication(), "server error", Toast.LENGTH_SHORT).show();
                if (BuildConfig.DEBUG) {
                    onFailure(AppConfig.INSTANCE.getApplication().getString(R.string.app_name));
                }
                return;
            }else {
//                if(baseResult.code == 33){//用户无效,清除用户然后退到登录界面
//                    SPUtils.clear();
//                    Context context = AppConfig.INSTANCE.getApplication();
//                    Intent intent = new Intent();
//                    intent.setClass(context, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                    return;
//                }
//                if(baseResult.code == 34){//版本更新提示
//
//                    LoginActivity.start();
//                    return;
//                }
                onFailure(baseResult.statusInfo);
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.message();
            if (code == NETWORD_504) {
                msg = NETWORD_504+"";
            }
            if (code == NETWORD_502 || code == NETWORD_404) {
                msg = NETWORD_502+"||"+NETWORD_404;
            }
            if (BuildConfig.DEBUG) {
                onFailure(msg);
            }

        } else if (e instanceof UnknownHostException || e instanceof SocketException || e instanceof SSLHandshakeException) {
            if (BuildConfig.DEBUG) {
                onFailure("HostException");
            }
        } else {
            if (BuildConfig.DEBUG) {
                onFailure(e.getMessage());
            }
        }

    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
