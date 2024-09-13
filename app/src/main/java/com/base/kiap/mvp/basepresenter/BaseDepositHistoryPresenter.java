package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseUserInfoBean;
import com.base.kiap.bean.base.DepositInrHistoryBean;
import com.base.kiap.bean.base.DepositUstdHistoryBean;
import com.base.kiap.bean.base.request.DepositHistoryRequest;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.mvp.baseviwe.IBaseDepositHistoryView;
import com.base.kiap.mvp.baseviwe.IBaseUserInfoView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class BaseDepositHistoryPresenter extends BasePresenter<IBaseDepositHistoryView> {

    private ApiSuStriation mStriation;

    public BaseDepositHistoryPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }


    public void getMoneyHistoryOrderList(int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("page", page);
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).getMoneyHistoryOrderList(map),
                new ApiCallBack<BaseResult<List<DepositInrHistoryBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<DepositInrHistoryBean>> model) {
                        if (model != null) {
                            List<DepositInrHistoryBean> bean = model.data;
                            if (bean != null) {
                                getView().onSuccessInrList(bean);
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

    public void getMoneyHistoryUstdList(int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("page", page);
        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).getUsdtOrderList(map),
                new ApiCallBack<BaseResult<List<DepositInrHistoryBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<DepositInrHistoryBean>> model) {
                        if (model != null) {
                            List<DepositInrHistoryBean> bean = model.data;
                            if (bean != null) {
                                getView().onSuccessInrList(bean);
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
