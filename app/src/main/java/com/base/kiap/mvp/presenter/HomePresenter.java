package com.base.kiap.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Process;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.base.kiap.R;
import com.base.kiap.activity.basea.MainActivity;
import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.IndexBean;
import com.base.kiap.bean.OrderBean;
import com.base.kiap.bean.UserBean;
import com.base.kiap.bean.request.BaseResult;
import com.base.kiap.config.UserHelp;
import com.base.kiap.https.retrofit.ApiCallBack;
import com.base.kiap.https.retrofit.ApiFactory;
import com.base.kiap.https.retrofit.ApiSuStriation;
import com.base.kiap.https.retrofit.PostApi;
import com.base.kiap.mvp.iview.IHomeView;
import com.base.kiap.utlis.DialogFactory;
import com.base.kiap.utlis.ToastUtil;
import com.base.kiap.utlis.languagelib.LanguageType;
import com.base.kiap.utlis.languagelib.MultiLanguageUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class HomePresenter extends BasePresenter<IHomeView> {

    private ApiSuStriation mStriation;

    public HomePresenter() {
        mStriation = new ApiSuStriation();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onUnregister() {
        if (mStriation != null) {
            mStriation.onUnSubscribe();
        }
    }

    /**
     * 获取用户信息
     */
    public void findUser() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).findUser(map),
                new ApiCallBack<BaseResult<UserBean>>() {
                    @Override
                    public void onSuccess(BaseResult<UserBean> model) {
                        if (model != null) {
                            UserBean bean = model.data;
                            if (bean != null) {
                                UserHelp.updateUser(bean);
                                getView().onMineInfoSuccess(bean);
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
                    }
                });
    }

    public void findIndex() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).findIndex(map),
                new ApiCallBack<BaseResult<IndexBean>>() {
                    @Override
                    public void onSuccess(BaseResult<IndexBean> model) {
                        if (model != null) {
                            IndexBean bean = model.data;
                            if (bean != null) {
                                getView().onIndexSuccess(bean);
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
     * 语言切换弹窗
     */
    public void onChangedLanguage(Activity mActivity) {
        String[] language_arrays = mActivity.getResources().getStringArray(R.array.language_arrays);
        DialogFactory.createAlertDialog(mActivity,
                language_arrays, (dialog, which) -> {
                    Intent intent;
                    intent = new Intent(mActivity, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    switch (which) {
                        case 0:
                            MultiLanguageUtil.getInstance().updateLanguage(LanguageType.LANGUAGE_EN);
                            break;
                        case 1:
                            MultiLanguageUtil.getInstance().updateLanguage(LanguageType.LANGUAGE_INR);
                            break;
                        default:
                            MultiLanguageUtil.getInstance().updateLanguage(LanguageType.LANGUAGE_EN);
                            break;
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mActivity.startActivity(intent);
                            Process.killProcess(Process.myPid());
                            System.exit(0);
                        }
                    }, 500);
                });
    }

}
