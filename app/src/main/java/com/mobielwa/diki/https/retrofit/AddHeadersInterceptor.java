package com.mobielwa.diki.https.retrofit;


import com.mobielwa.diki.config.AppConfig;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.utlis.AppUtils;
import com.mobielwa.diki.utlis.GetDeviceId;
import com.mobielwa.diki.utlis.languagelib.MultiLanguageUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddHeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String createAt = System.currentTimeMillis() + "";
        Request.Builder builder = chain.request().newBuilder();
        builder.
//                addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cookie", "add cookies here");
//                addHeader("Authorization", "Bearer " + SharedPreferencesHelper.getSharedPreference(CodeCons.TOKEN, ""))
                addHeader("AppVersion", AppUtils.Companion.getVerName(AppConfig.INSTANCE.getApplication()))
                .addHeader("Channel", AppUtils.Companion.getChannel())
                .addHeader("AppName", AppUtils.Companion.getAppName())
                .addHeader("Device", GetDeviceId.getDevice(AppConfig.INSTANCE.getApplication()))
                .addHeader("pkgName", AppUtils.Companion.packageName())
                .addHeader("languageType", MultiLanguageUtil.getInstance().getLanguageType()+"")
                .addHeader("timestamp", createAt)
                .addHeader("Authorization", UserHelp.getToken())
                .addHeader("versionCode", AppUtils.Companion.getVerCode(AppConfig.INSTANCE.getApplication())+"")
                .addHeader("lang", MultiLanguageUtil.getInstance().getLanguageType()+"")
                .addHeader("auth", AppUtils.Companion.MD5Sign("yedi" + createAt))
//                .addHeader("Patch", String.valueOf(PatchVersionUtils.getSharedPreference(CodeCons.PATCH_CODE, 0)))
//                .addHeader("UUID", (String) SharedPreferencesHelper.getSharedPreference(CodeCons.UUID, ""))//原来的,不保证卸载重装
//                .addHeader("UUID", GetDeviceId.getDevice(AppConfig.INSTANCE.getApplication()))//设备唯一性
                //表明当前正在使用的tcp链接在请求处理完毕后会被断掉。以后client再进行新的请求时就必须创建新的tcp链接了
                .addHeader("Connection", "close");

        return chain.proceed(builder.build());
    }
}
