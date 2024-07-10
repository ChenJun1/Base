package com.mobielwa.diki.utlis.permission;

import android.app.Activity;
import android.os.Build;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: 张
 * @Email: 1271396448@qq.com
 * @Date: 2019/8/1 6:16 PM
 * <p>
 * 权限请求辅助类
 */
public class PermissionRequestHelper {

    private OnRequestPermissionListener onRequestPermissionListener = null;

    /**
     * 请求权限
     *
     * @param activity
     * @param onRequestPermissionListener
     */
    public void requestPermissions(Activity activity, OnRequestPermissionListener onRequestPermissionListener, final String... permissions) {
        this.onRequestPermissionListener = onRequestPermissionListener;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RxPermissions rxPermissions = new RxPermissions(activity);
            rxPermissions.request(permissions).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Boolean aBoolean) {
                    if (aBoolean) {
                        //请求成功
                        if (onRequestPermissionListener != null)
                            onRequestPermissionListener.onRequestSuccess();
                    } else {
                        //请求失败
                        if (onRequestPermissionListener != null)
                            onRequestPermissionListener.onRequestFailed();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (onRequestPermissionListener != null)
                        onRequestPermissionListener.onRequestFailed();
                }

                @Override
                public void onComplete() {

                }
            });
        } else {
            //请求成功
            if (this.onRequestPermissionListener != null)
                onRequestPermissionListener.onRequestSuccess();
        }
    }

}
