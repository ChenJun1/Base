package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseWithdrawBean;
import com.base.kiap.bean.base.DepositInrInfoBean;
import com.base.kiap.bean.base.request.InrBuyRequest;
import com.base.kiap.bean.base.request.InrListRequest;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.mvp.baseviwe.IBaseDepositInrtView;
import com.base.kiap.utlis.ToastUtil;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class BaseDepositInrPresenter extends BasePresenter<IBaseDepositInrtView> {

    private ApiSuStriation mStriation;

    public BaseDepositInrPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }


    public void getInrList(InrListRequest request) {
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).geMoneyOrderInfo(request),
                new ApiCallBack<BaseResult<List<DepositInrInfoBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<DepositInrInfoBean>> model) {
                        if (model != null) {
                            List<DepositInrInfoBean> bean = model.data;
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

    public void buy(InrBuyRequest request) {
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).onCreatePayoutOrder(request),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        if (model != null && model.isSuccess()) {
                            getView().onBuySuccess();
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
