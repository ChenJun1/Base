package com.base.kiap.mvp.presenter;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.FinancialOrderBean;
import com.base.kiap.bean.FinancialSumBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.iview.IFinanciaDetailView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: 理财列表
 */
public class FinancialDetailPresenter extends BasePresenter<IFinanciaDetailView> {

    private ApiSuStriation mStriation;

    public FinancialDetailPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 订单列表
     */
    public void onOrderList() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).getProductOrderFindList(map),
                new ApiCallBack<BaseResult<List<FinancialOrderBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<FinancialOrderBean>> model) {
                        if (model != null) {
                            List<FinancialOrderBean> beanList = model.data;
                            if (beanList != null) {
                                getView().onListSuccess(beanList);
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
     * 取款
     */
    public void onProductwithdrawald(String orderid) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        map.put("orderId", orderid);
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onProductwithdrawald(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        if (model != null) {
                            getView().onSuccess();
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
     * 查询汇总信息
     */
    public void onProductsum() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onProductsum(map),
                new ApiCallBack<BaseResult<FinancialSumBean>>() {
                    @Override
                    public void onSuccess(BaseResult<FinancialSumBean> model) {
                        if (model != null) {
                            FinancialSumBean bean = model.data;
                            if (bean != null) {
                                getView().onSuccessSum(bean);
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
