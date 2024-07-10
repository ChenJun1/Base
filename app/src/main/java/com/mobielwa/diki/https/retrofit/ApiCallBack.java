package com.mobielwa.diki.https.retrofit;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.mobielwa.diki.BuildConfig;
import com.mobielwa.diki.R;
import com.mobielwa.diki.activity.LoginActivity;
import com.mobielwa.diki.config.AppConfig;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.utlis.SPUtils;
import com.mobielwa.diki.utlis.ToastUtil;

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
        try {
            BaseResult baseResult = (BaseResult) m;
            if (baseResult.isSuccess()) {
                onSuccess(m);
                return;
            } else if (baseResult.code == 500) {
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
                onFailure(baseResult.msg);
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
