package com.base.kiap.mvp.presenter;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.config.UserHelp;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.iview.ISetNikeName;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class SetNamePresenter extends BasePresenter<ISetNikeName> {

    private ApiSuStriation mStriation;

    public SetNamePresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 获取任务
     */
    public void onSetNikeName(String name) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("username", name);
        map.put("id", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onSetName(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        getView().onSuccess();
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.error(msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        getView().onHideDialog();
                    }
                });
    }

}
