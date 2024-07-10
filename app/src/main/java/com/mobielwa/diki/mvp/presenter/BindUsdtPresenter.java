package com.mobielwa.diki.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.IBindUsdt;
import com.mobielwa.diki.mvp.iview.ISetPayPass;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class BindUsdtPresenter extends BasePresenter<IBindUsdt> {

    private ApiSuStriation mStriation;

    public BindUsdtPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 绑定USDT
     */
    public void onSetUsdtAddress(String usdt) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("usdt", usdt);
        map.put("id", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onSetUsdtAddress(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        ToastUtil.success(model.msg);
                        getView().onSuccess();
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
