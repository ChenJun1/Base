package com.mobielwa.diki.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.RechargeRecordBean;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.IRechargeUsdtRecordListView;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class RechargeRecordPresenter extends BasePresenter<IRechargeUsdtRecordListView> {

    private ApiSuStriation mStriation;

    public RechargeRecordPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 充值记录
     */
    public void rechargeDetail(int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        map.put("page", page);
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).rechargeDetail(map),
                new ApiCallBack<BaseResult<List<RechargeRecordBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<RechargeRecordBean>> model) {
                        getView().onSuccess(model.data);
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
