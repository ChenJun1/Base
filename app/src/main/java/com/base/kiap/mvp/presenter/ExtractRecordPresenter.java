package com.base.kiap.mvp.presenter;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.ExtractRecordBean;
import com.base.kiap.config.UserHelp;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.iview.IExtractRecordListView;
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
public class ExtractRecordPresenter extends BasePresenter<IExtractRecordListView> {

    private ApiSuStriation mStriation;

    public ExtractRecordPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 提现记录
     */
    public void onWithdrawal(int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("page", page);
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onWithdrawal(map),
                new ApiCallBack<BaseResult<List<ExtractRecordBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<ExtractRecordBean>> model) {
                        if (model != null) {
                            List<ExtractRecordBean> beanList = model.data;
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

}
