package com.base.kiap.mvp.presenter;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.oldbean.CashRecordBean;
import com.base.kiap.config.UserHelp;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.iview.ICashRecordListView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class CashRecordPresenter extends BasePresenter<ICashRecordListView> {

    private ApiSuStriation mStriation;

    public CashRecordPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 收入记录
     */
    public void onIncomeRecord(int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("page", page);
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onIncomeRecord(map),
                new ApiCallBack<BaseResult<List<CashRecordBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<CashRecordBean>> model) {
                        if (model != null) {
                            List<CashRecordBean> beanList = model.data;
                            if (beanList != null) {
                                getView().onIncomeSuccess(beanList);
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
     * 支出记录
     */
    public void onPayRecord(int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("page", page);
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onPayRecord(map),
                new ApiCallBack<BaseResult<List<CashRecordBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<CashRecordBean>> model) {
                        if (model != null) {
                            List<CashRecordBean> beanList = model.data;
                            if (beanList != null) {
                                getView().onPaySuccess(beanList);
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
