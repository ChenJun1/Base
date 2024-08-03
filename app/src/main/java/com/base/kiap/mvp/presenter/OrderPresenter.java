package com.base.kiap.mvp.presenter;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.R;
import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.DialogBean;
import com.base.kiap.bean.OrderBean;
import com.base.kiap.bean.UsdtIndexBean;
import com.base.kiap.bean.event.OrderSuccessEvent;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.listen.onItemClickListen;
import com.base.kiap.mvp.iview.IOrderView;
import com.base.kiap.utlis.DialogUtlis;
import com.base.kiap.utlis.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class OrderPresenter extends BasePresenter<IOrderView> {

    private ApiSuStriation mStriation;

    public OrderPresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 获取首页信息
     */
    public void onIndex() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onIndex(map),
                new ApiCallBack<BaseResult<UsdtIndexBean>>() {
                    @Override
                    public void onSuccess(BaseResult<UsdtIndexBean> model) {
                        if (model != null) {
                            UsdtIndexBean bean = model.data;
                            if (bean != null) {
                                getView().onIndex(bean);
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
     * 获取任务
     */
    public void onOrderList(int type, int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        map.put("stat", type);
        map.put("page", page);
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onOrderList(map),
                new ApiCallBack<BaseResult<List<OrderBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<OrderBean>> model) {
                        if (model != null) {
                            List<OrderBean> beanList = model.data;
                            if (beanList != null) {
                                getView().onGetOrderSuccess(beanList);
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
     * 接单
     */
    public void onPlaceOrder(OrderBean bean, Activity activity) {
//        if(UserHelp.getBalance() < UserHelp.getLevelBalance()){
//            getView().onHideDialog();
//            DialogBean diabean = new DialogBean();
//            diabean.title = activity.getString(R.string.str_information);
//            diabean.content = String.format(activity.getString(R.string.str_main_newuser), ""+new BigDecimal(UserHelp.getLevelBalance()).setScale(2, RoundingMode.HALF_UP), ""+new BigDecimal(UserHelp.getLevelBalance()).setScale(2, RoundingMode.HALF_UP));
//            diabean.btntext = activity.getString(R.string.str_recharge_now);
//            DialogUtlis.showTaskDialogNotiMsg(activity, diabean, new onItemClickListen() {
//                @Override
//                public void onItemClick(View v) {
//                    RechargeActivity.start(activity);
//                }
//                @Override
//                public void onClose() {}
//            });
//            return;
//        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        map.put("taskId", bean.getId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onPlaceOrder(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> model) {
                        ToastUtil.success(model.msg);
                        if (UserHelp.getSurplusTask()>0) {
                            UserHelp.setSurplusTask(UserHelp.getSurplusTask()-1);
                        }
                        EventBus.getDefault().post(new OrderSuccessEvent());
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.normal(msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        getView().onHideDialog();
                    }
                });
    }

    //等级不足 切换等级
    public void onChangeLv(Activity activity) {
        DialogBean diabean = new DialogBean();
        diabean.title = activity.getString(R.string.str_order_ts_1);
        diabean.content = activity.getString(R.string.str_order_ts_2);
        diabean.btntext = activity.getString(R.string.str_order_ts_3);
        DialogUtlis.showTaskDialogP(activity, diabean, new onItemClickListen() {
            @Override
            public void onItemClick(View v) {
                getView().onChangelv();
            }

            @Override
            public void onClose() {

            }
        });
    }

}
