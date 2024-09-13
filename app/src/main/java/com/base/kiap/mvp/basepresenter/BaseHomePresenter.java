package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseBannerBean;
import com.base.kiap.bean.base.BaseIndexBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.mvp.baseviwe.IBaseHomeView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class BaseHomePresenter extends BasePresenter<IBaseHomeView> {

    private ApiSuStriation mStriation;

    public BaseHomePresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }



    public void findIndex() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).indexData(map),
                new ApiCallBack<BaseResult<BaseIndexBean>>() {
                    @Override
                    public void onSuccess(BaseResult<BaseIndexBean> model) {
                        if (model != null) {
                            BaseIndexBean bean = model.data;
                            if (bean != null) {
                                getView().onIndexSuccess(bean);
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

    public void onWithdrawSwitch(int state) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("stat", state);
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).withdrawSwitch(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        if (model != null) {
                            getView().onWithdrawSwitchSuccess();
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

    public void banner() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).banner(),
                new ApiCallBack<BaseResult<List<BaseBannerBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<BaseBannerBean>> model) {
                        if (model != null) {
                            List<BaseBannerBean> bean = model.data;
                            if (bean != null) {
                                getView().onBannerSuccess(bean);
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

}
