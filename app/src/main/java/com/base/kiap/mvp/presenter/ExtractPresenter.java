package com.base.kiap.mvp.presenter;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.R;
import com.base.kiap.activity.ExtractRecordActivity;
import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.DialogBean;
import com.base.kiap.bean.PayoutGetBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.listen.onItemClickListen;
import com.base.kiap.mvp.iview.IExtractView;
import com.base.kiap.utlis.DialogUtlis;
import com.base.kiap.utlis.ToastUtil;

import java.util.LinkedHashMap;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class ExtractPresenter extends BasePresenter<IExtractView> {

    private ApiSuStriation mStriation;

    public ExtractPresenter() {
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
    public void getWithdrawalInfo() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());

        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).getWithdrawalInfo(map),
                new ApiCallBack<BaseResult<PayoutGetBean>>() {
                    @Override
                    public void onSuccess(BaseResult<PayoutGetBean> model) {
                        PayoutGetBean bean=model.data;
                        if (bean!=null) {
                            getView().finishSuccess(bean);
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
     * 申请提现
     */
    public void onApplyWithdrawal(String money, String pass, String accountNo, String accountHolder, String ifsc) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("amount", money);
        map.put("password", pass);
        map.put("accountNo", accountNo);
        map.put("accountHolder", accountHolder);
        map.put("ifsc", ifsc);
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onApplyWithdrawal(map),
                new ApiCallBack<BaseResult<String>>() {
                    @Override
                    public void onSuccess(BaseResult<String> model) {
                        if (model.code==0) {
                            String str = model.data;
                            if (str!=null&&str.length()>10) {
                                getView().onFailure(model.data);
                            }else{
                                long balance = UserHelp.getBalance();
                                UserHelp.setBalance(balance-(int)(Double.parseDouble(money)*10000));
                                getView().onSuccess();
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
     * 申请提现
     */
    public void onApplyWithdrawalUsdt(int money, String pass, String usdtAddr) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("amount", money);
        map.put("password", pass);
        map.put("usdtAddr", usdtAddr);
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onApplyWithdrawalUsdt(map),
                new ApiCallBack<BaseResult<String>>() {
                    @Override
                    public void onSuccess(BaseResult<String> model) {
                        if (model.code==0) {
                            String str = model.data;
                            if (str!=null&&str.length()>10) {
                                getView().onFailure(model.data);
                            }else{
                                long balance = UserHelp.getBalance();
                                UserHelp.setBalance(balance-money);
                                getView().onSuccess();
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

    public void onWithdrawalSuccessDialog(Activity activity) {
        DialogBean diabean = new DialogBean();
        diabean.title = activity.getString(R.string.str_extr_success_1);
        diabean.content = activity.getString(R.string.str_extr_success_2);
        diabean.btntext = activity.getString(R.string.str_extr_success_3);
        DialogUtlis.showTaskDialogPExtract(activity, diabean, new onItemClickListen() {
            @Override
            public void onItemClick(View v) {
                ExtractRecordActivity.start(activity);
                getView().onfinish();
            }

            @Override
            public void onClose() {

            }
        });
    }

    public void onWithdrawalSuccessUsdtDialog(Activity activity) {
        DialogBean diabean = new DialogBean();
        diabean.title = activity.getString(R.string.str_extr_success_1);
        diabean.content = activity.getString(R.string.str_extr_success_2);
        diabean.btntext = activity.getString(R.string.str_extr_success_3);
        DialogUtlis.showTaskDialogPExtract(activity, diabean, new onItemClickListen() {
            @Override
            public void onItemClick(View v) {
                ExtractRecordActivity.start(activity);
                getView().onfinish();
            }

            @Override
            public void onClose() {

            }
        });
    }

}
