package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.R;
import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseUserBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.bean.request.LoginBean;
import com.base.kiap.config.SpCode;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.baseviwe.IBaseLoginView;
import com.base.kiap.utlis.SPUtils;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/25/20 10:13 AM
 * @Description: java类作用描述
 */
public class BaseLoginPresenter extends BasePresenter<IBaseLoginView> {
    private ApiSuStriation mStriation;

    public BaseLoginPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 获取验证码
     */
    public void onRegisterCode(String phone,String inviteCode) {//917543957131，9199024142
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("phone", phone);
        map.put("inviteCode", inviteCode);
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).registerCode(map),
                new ApiCallBack<BaseResult<Integer>>() {
                    @Override
                    public void onSuccess(BaseResult<Integer> model) {
                        ToastUtil.success(R.string.str_login_sencsu);
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

    /**
     * 获取验证码
     */
    public void onSmsCode(String phone,String inviteCode) {//917543957131，9199024142
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("phone", phone);
        map.put("inviteCode", inviteCode);
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).registerCode(map),
                new ApiCallBack<BaseResult<Integer>>() {
                    @Override
                    public void onSuccess(BaseResult<Integer> model) {
                        ToastUtil.success(R.string.str_login_sencsu);
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

    /**
     * 注册
     */
    public void onRegister(String phone,String pass,String inviteCode,String smsCode) {//917543957131，9199024142
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("phone", phone);
        map.put("passwd", pass);
        map.put("inviteCode", inviteCode);
        map.put("smsCode", smsCode);
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).register(map),
                new ApiCallBack<BaseResult<Integer>>() {
                    @Override
                    public void onSuccess(BaseResult<Integer> model) {
                        ToastUtil.success(R.string.str_login_sencsu);
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

    /**
     * 用户登录
     */
    public void onLogin(LoginBean bean) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("phone", bean.phone);
        map.put("passwd", bean.passWord);

        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).login(map),
                new ApiCallBack<BaseResult<BaseUserBean>>() {
                    @Override
                    public void onSuccess(BaseResult<BaseUserBean> model) {
                        if (model != null) {
                            BaseUserBean bean = model.data;
                            if (bean != null) {
                                getView().onLoginSuccess(bean);
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
                        getView().onHideDialog();
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
                        SPUtils.put(SpCode.INSTALL, false);
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
