package com.base.kiap.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.oldbean.MemberBean;
import com.base.kiap.bean.oldbean.MemberIndexBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.iview.IMemberView;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class MemberPresenter extends BasePresenter<IMemberView> {

    private ApiSuStriation mStriation;

    public MemberPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 获取会员等级
     */
    public void memberIndex() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).memberIndex(map),
                new ApiCallBack<BaseResult<MemberIndexBean>>() {
                    @Override
                    public void onSuccess(BaseResult<MemberIndexBean> model) {
                        if (model != null) {
                            MemberIndexBean memberIndex = model.data;
                            getView().onMemberIndex(memberIndex);
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

    public void memberList() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).memberList(map),
                new ApiCallBack<BaseResult<List<MemberBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<MemberBean>> model) {
                        if (model != null) {
                            List<MemberBean> memberList = model.data;
                            getView().onMemberList(memberList);
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

    public void addMember(int memberId) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("memberId", memberId);
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).addMember(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
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
