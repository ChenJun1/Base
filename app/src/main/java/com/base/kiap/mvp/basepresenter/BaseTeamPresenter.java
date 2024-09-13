package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseIndexBean;
import com.base.kiap.bean.base.BaseTeamIndexInfoBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.mvp.baseviwe.IBaseHomeView;
import com.base.kiap.mvp.baseviwe.IBaseTeamView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class BaseTeamPresenter extends BasePresenter<IBaseTeamView> {

    private ApiSuStriation mStriation;

    public BaseTeamPresenter() {
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
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).teamIndex(map),
                new ApiCallBack<BaseResult<BaseTeamIndexInfoBean>>() {
                    @Override
                    public void onSuccess(BaseResult<BaseTeamIndexInfoBean> model) {
                        if (model != null) {
                            BaseTeamIndexInfoBean bean = model.data;
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
                        getView().onHideDialog();
                    }
                });
    }



}
