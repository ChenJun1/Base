package com.mobielwa.diki.https.retrofit;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.mobielwa.diki.bean.ConfigBean;
import com.mobielwa.diki.bean.MemberBean;
import com.mobielwa.diki.bean.UserBean;
import com.mobielwa.diki.bean.event.ConfigEvent;
import com.mobielwa.diki.bean.event.MemberEvent;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.config.ConfigDate;
import com.mobielwa.diki.config.SpCode;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.utlis.SPUtils;
import com.mobielwa.diki.utlis.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/7/20 10:55 AM
 * @Description: java类作用描述
 */
public class PostHttp implements LifecycleObserver {
    private ApiSuStriation mStriation;

    public PostHttp() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }


    /**
     * 获取用户信息
     */
    public void findUser() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).findUser(map),
                new ApiCallBack<BaseResult<UserBean>>() {
                    @Override
                    public void onSuccess(BaseResult<UserBean> model) {
                        if (model != null) {
                            UserBean bean = model.data;
                            if (bean != null) {
                                UserHelp.updateUser(bean);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.error(msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }


    /**
     * 获取系统配置
     */
    public void onSettingFind() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onSettingFind(map),
                new ApiCallBack<BaseResult<ConfigBean>>() {
                    @Override
                    public void onSuccess(BaseResult<ConfigBean> model) {
                        if (model != null) {
                            ConfigBean bean = model.data;
                            if (bean != null) {
                                ConfigDate.sConfigBean = bean;
                                UserHelp.updateConfig(bean);
                                EventBus.getDefault().post(new ConfigEvent());
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.normal(msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * 第一次安装
     */
    public void onInstall() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).install(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        SPUtils.put(SpCode.INSTALL ,false);
                    }

                    @Override
                    public void onFailure(String msg) {
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }


}
