package com.mobielwa.diki.mvp.presenter;

import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.ExtractRecordBean;
import com.mobielwa.diki.bean.dao.MessageBean;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.IExtractRecordListView;
import com.mobielwa.diki.mvp.iview.IMessgListView;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: 消息记录
 */
public class MessgListPresenter extends BasePresenter<IMessgListView> {

    private ApiSuStriation mStriation;

    public MessgListPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 消息记录
     */
    public void onMessList() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).getMsgList(map),
                new ApiCallBack<BaseResult<List<MessageBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<MessageBean>> model) {
                        if (model != null) {
                            List<MessageBean> beanList = model.data;
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
