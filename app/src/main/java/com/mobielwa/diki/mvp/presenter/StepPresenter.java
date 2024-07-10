package com.mobielwa.diki.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.ApplyBean;
import com.mobielwa.diki.bean.BindBean;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.bean.request.RechargeRequestBean;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.IRechargeView;
import com.mobielwa.diki.mvp.iview.IStepView;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: 充值
 */
public class StepPresenter extends BasePresenter<IStepView> {

    private ApiSuStriation mStriation;

    public StepPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    public void stepOk(int step) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("step", step);
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).stepOk(map),
                new ApiCallBack<BaseResult<String>>() {
                    @Override
                    public void onSuccess(BaseResult<String> model) {
                        String str = model.data;
                        if (str != null) {
                            ToastUtil.success(str);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.error(msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    public void findBind() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).findBind(map),
                new ApiCallBack<BaseResult<BindBean>>() {
                    @Override
                    public void onSuccess(BaseResult<BindBean> model) {
                        BindBean bean = model.data;
                        if (bean != null) {
                            getView().bindObj(bean);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.error(msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

}
