package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseUserInfoBean;
import com.base.kiap.bean.base.DepositUsdtInfoBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.mvp.baseviwe.IBaseDepositUsdtView;
import com.base.kiap.mvp.baseviwe.IBaseUserInfoView;
import com.base.kiap.utlis.ToastUtil;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class BaseDepositUsdtPresenter extends BasePresenter<IBaseDepositUsdtView> {

    private ApiSuStriation mStriation;

    public BaseDepositUsdtPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }


    public void getUsdInfo() {
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).onCreateUsdtOrder(),
                new ApiCallBack<BaseResult<DepositUsdtInfoBean>>() {
                    @Override
                    public void onSuccess(BaseResult<DepositUsdtInfoBean> model) {
                        if (model != null) {
                            DepositUsdtInfoBean bean = model.data;
                            if (bean != null) {
                                getView().onSuccess(bean);
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
