package com.mobielwa.diki.https.retrofit;

import com.mobielwa.diki.BuildConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

//      private static final String API_URL = "https://api.pickwallet.vip";//USDT
    private static final String API_URL = "http://147.139.1.59:8899";//USDT

    public static Retrofit mRetrofit;
    public static Map<String, Retrofit> mRetrofitMap = new HashMap<>();
    private static final int DEFAULT_TIMEOUT = 60000;

    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true);//取消错误重联
            //服务器如果使用了证书的话，那对于移动端来说，直接可以忽略此证书
            //自定义cookie拦截器
//            builder.addInterceptor(new AddCookiesInterceptor());
            //自定义header拦截
            builder.addInterceptor(new AddHeadersInterceptor());
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

}
