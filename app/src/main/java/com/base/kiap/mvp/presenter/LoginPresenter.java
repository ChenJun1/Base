package com.base.kiap.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.R;
import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.oldbean.CodeBean;
import com.base.kiap.bean.oldbean.UserBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.bean.request.LoginBean;
import com.base.kiap.config.SpCode;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.iview.ILoginView;
import com.base.kiap.utlis.SPUtils;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/25/20 10:13 AM
 * @Description: java类作用描述
 */
public class LoginPresenter extends BasePresenter<ILoginView> {
    private ApiSuStriation mStriation;

    public LoginPresenter() {
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
    public int onCode(String phone) {//917543957131，9199024142
        final int[] code = {0};
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("phone", phone);
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).code(map),
                new ApiCallBack<BaseResult<CodeBean>>() {
                    @Override
                    public void onSuccess(BaseResult<CodeBean> model) {
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
        return code[0];
    }

    /**
     * 用户登录
     */
    public void onLogin(LoginBean bean) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("phone", bean.phone);
        map.put("code", bean.code);
        map.put("inviteCode", bean.inviteCode);

        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).login(map),
                new ApiCallBack<BaseResult<UserBean>>() {
                    @Override
                    public void onSuccess(BaseResult<UserBean> model) {
                        if (model != null) {
                            UserBean bean = model.data;
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
