package com.base.kiap.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.oldbean.UsdtBean;
import com.base.kiap.bean.oldbean.UsdtOrderBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.iview.IRechargeUsdtView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: 充值
 */
public class UsdtRechargePresenter extends BasePresenter<IRechargeUsdtView> {

    private ApiSuStriation mStriation;

    public UsdtRechargePresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 查询USDT地址
     */
    public void findUsdtAddress() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());

        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).findUsdtAddress(map),
                new ApiCallBack<BaseResult<UsdtBean>>() {
                    @Override
                    public void onSuccess(BaseResult<UsdtBean> model) {
                        UsdtBean bean=model.data;
                        if (bean!=null) {
                            getView().onSuccess(bean);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.error(msg);
                        getView().onFail();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        getView().onHideDialog();
                    }
                });
    }

    /**
     * 获取osskey
     */

    /**
     * 创建USDT订单
     */
    public void createUsdtOrder(UsdtOrderBean bean) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("money", bean.getUsdtMoney());
        map.put("userId", UserHelp.getUserId());
        map.put("vipId", bean.getVipId());
        map.put("usdtMoney", bean.getUsdtMoney());
        map.put("imgUrl", bean.getImgUrl());
        map.put("whatsapp", bean.getWhatsapp());
        map.put("usdtNote", bean.getUsdtNote());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).createUsdtOrder(map),
                new ApiCallBack<BaseResult<String>>() {
                    @Override
                    public void onSuccess(BaseResult<String> model) {
                        ToastUtil.success(""+model.data);
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
