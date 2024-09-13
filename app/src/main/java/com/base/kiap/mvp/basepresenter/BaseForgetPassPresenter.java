package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseUserBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.mvp.baseviwe.IForgetPassView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class BaseForgetPassPresenter extends BasePresenter<IForgetPassView> {

    private ApiSuStriation mStriation;

    public BaseForgetPassPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }


    public void onForgetPass(String password) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("phone", password);
        map.put("smsCode", password);
        map.put("newPassword", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).forgetPass(map),
                new ApiCallBack<BaseResult<BaseUserBean>>() {
                    @Override
                    public void onSuccess(BaseResult<BaseUserBean> model) {
                        ToastUtil.success(model.statusInfo);
                        UserHelp.setPayPassword(password);
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

    public void onCodeSms(String password) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("phone", password);
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).smsCode(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        ToastUtil.success(model.statusInfo);
                        UserHelp.setPayPassword(password);
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
