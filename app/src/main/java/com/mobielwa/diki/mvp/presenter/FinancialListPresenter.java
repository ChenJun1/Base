package com.mobielwa.diki.mvp.presenter;

import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.DistriFirendBean;
import com.mobielwa.diki.bean.DistriSum;
import com.mobielwa.diki.bean.FinancialBean;
import com.mobielwa.diki.bean.FinancialSumBean;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.mvp.iview.IFinancialView;
import com.mobielwa.diki.mvp.iview.IMyteamView;
import com.mobielwa.diki.utlis.ToastUtil;

import java.util.LinkedHashMap;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: 理财列表
 */
public class FinancialListPresenter extends BasePresenter<IFinancialView> {

    private ApiSuStriation mStriation;

    public FinancialListPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     *理财列表
     */
    public void getProductFindList() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).getProductFindList(map),
                new ApiCallBack<BaseResult<List<FinancialBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<FinancialBean>> model) {
                        if (model != null) {
                            List<FinancialBean> beanList = model.data;
                            if (beanList != null) {
                                getView().onListSuccess(beanList);
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

    /**
     * 购买理财
     */
    public void onProductBuy(int money,int id) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        map.put("money",money);
        map.put("productId", id);
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).ProductBuy(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        if (model != null) {
                               ToastUtil.success(model.msg);
                               getView().onSuccess();
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
     * 查询汇总信息
     */
    public void onProductsum() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onProductsum(map),
                new ApiCallBack<BaseResult<FinancialSumBean>>() {
                    @Override
                    public void onSuccess(BaseResult<FinancialSumBean> model) {
                        if (model != null) {
                            FinancialSumBean bean = model.data;
                            if (bean != null) {
                                getView().onSuccessSum(bean);
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
