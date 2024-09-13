package com.base.kiap.mvp.basepresenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.base.BaseTeamDetailBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.bean.base.request.TeamDetailRequest;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.BasePostApi;
import com.base.kiap.mvp.baseviwe.IBaseTeamDetailView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class BaseTeamDetailPresenter extends BasePresenter<IBaseTeamDetailView> {

    private ApiSuStriation mStriation;

    public BaseTeamDetailPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }



    public void getDetailList(int type,int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("type", type);
        map.put("page", page);

        mStriation.addSubStriation(ApiFactory.retrofit().create(BasePostApi.class).getTeamDetailList(new TeamDetailRequest(type,page)),
                new ApiCallBack<BaseResult<List<BaseTeamDetailBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<BaseTeamDetailBean>> model) {
                        if (model != null) {
                            List<BaseTeamDetailBean> beanList = model.data;
                            if (beanList != null) {
                                getView().onDetailListSuccess(beanList);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.error(msg);
                        getView().onHideDialog();
                    }

                    @Override
                    public void onFinish() {
                        getView().onHideDialog();
                        super.onFinish();
                    }
                });
    }



}
