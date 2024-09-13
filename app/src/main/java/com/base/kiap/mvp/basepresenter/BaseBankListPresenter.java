package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseBankInfoBean;
import com.base.kiap.bean.base.request.AddBankRequest;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.mvp.baseviwe.IBaseAddBankView;
import com.base.kiap.mvp.baseviwe.IBaseBankListView;
import com.base.kiap.utlis.ToastUtil;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: 消息记录
 */
public class BaseBankListPresenter extends BasePresenter<IBaseBankListView> {

    private ApiSuStriation mStriation;

    public BaseBankListPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    public void onBankList() {

        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).bankList(),
                new ApiCallBack<BaseResult<List<BaseBankInfoBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<BaseBankInfoBean>> model) {
                        if (model != null) {
                            List<BaseBankInfoBean> beanList = model.data;
                            if (beanList != null) {
                                getView().onSuccess(beanList);
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

    public void onDeleteBank(BaseBankInfoBean bankInfoBean) {

        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).deleteBank(bankInfoBean),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        if (model != null) {
                            Object beanList = model.data;
                            if (beanList != null) {
                                getView().onDeleteSuccess();
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
