package com.mobielwa.diki.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.ApplyBean;
import com.mobielwa.diki.bean.ApplyChannBean;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.bean.request.RechargeRequestBean;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.IRechargeView;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: 充值
 */
public class RechargePresenter extends BasePresenter<IRechargeView> {

    private ApiSuStriation mStriation;

    public RechargePresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 充值
     */
    public void onApplyrecharge(RechargeRequestBean bean) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("amount", bean.amount);
        map.put("userId", UserHelp.getUserId());
        map.put("vipId", bean.vipId);
        map.put("payId", bean.payId);
        map.put("realName", bean.realName+"");
        map.put("tradeSource", bean.tradeSource+"");
        map.put("userEmail", bean.userEmail+"");
        map.put("userMobile", bean.userMobile+"");
        map.put("whatsapp", bean.whatsapp+"");
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onApplyrecharge(map),
                new ApiCallBack<BaseResult<ApplyBean>>() {
                    @Override
                    public void onSuccess(BaseResult<ApplyBean> model) {
                        ApplyBean bean=model.data;
                        if (bean!=null) {
                            getView().onSuccess(bean);
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
     * 充值
     */
    public void onBuyMembers(RechargeRequestBean bean) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("amount", bean.amount);
        map.put("userId", UserHelp.getUserId());
        map.put("vipId", bean.vipId);
        map.put("payId", bean.payId);
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onBuyMembers(map),
                new ApiCallBack<BaseResult<ApplyBean>>() {
                    @Override
                    public void onSuccess(BaseResult<ApplyBean> model) {
                        ApplyBean bean=model.data;
                        if (bean!=null) {
                            getView().onSuccess(bean);
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
