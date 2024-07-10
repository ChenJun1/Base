package com.mobielwa.diki.mvp.presenter;

import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.CashRecordBean;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.ICashRecordListView;
import com.mobielwa.diki.utlis.ToastUtil;

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
