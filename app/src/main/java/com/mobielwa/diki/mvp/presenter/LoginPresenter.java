package com.mobielwa.diki.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.CodeBean;
import com.mobielwa.diki.bean.UserBean;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.bean.request.LoginBean;
import com.mobielwa.diki.config.SpCode;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.ILoginView;
import com.mobielwa.diki.utlis.SPUtils;
import com.mobielwa.diki.utlis.ToastUtil;

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
