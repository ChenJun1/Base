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
import com.mobielwa.diki.mvp.iview.ITurntable;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class TurntablePresenter extends BasePresenter<ITurntable> {

    private final ApiSuStriation mStriation;

    public TurntablePresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 查询抽奖次数
     */
    public void findCount() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).findTurntableCount(map),
                new ApiCallBack<BaseResult<Integer>>() {
                    @Override
                    public void onSuccess(BaseResult<Integer> model) {
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

    /**
     * 抽奖
     */
    public void draw() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).turntableDraw(map),
                new ApiCallBack<BaseResult<Integer>>() {
                    @Override
                    public void onSuccess(BaseResult<Integer> model) {
                        getView().drawSuccess(model.data);
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
