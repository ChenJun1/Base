package com.mobielwa.diki.mvp.presenter;

import android.app.Activity;
import android.view.View;

import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BasePresenter;
import com.mobielwa.diki.bean.BannerBean;
import com.mobielwa.diki.bean.ConfigBean;
import com.mobielwa.diki.bean.DialogBean;
import com.mobielwa.diki.bean.UserBean;
import com.mobielwa.diki.bean.event.ConfigEvent;
import com.mobielwa.diki.config.ConfigDate;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.bean.request.BaseResult;
import com.mobielwa.diki.https.retrofit.ApiCallBack;
import com.mobielwa.diki.https.retrofit.ApiFactory;
import com.mobielwa.diki.https.retrofit.ApiSuStriation;
import com.mobielwa.diki.https.retrofit.PostApi;
import com.mobielwa.diki.listen.onItemClickListen;
import com.mobielwa.diki.mvp.iview.IMineView;
import com.mobielwa.diki.utlis.DialogUtlis;
import com.mobielwa.diki.utlis.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashMap;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:15 PM
 * @Description: java类作用描述
 */
public class MinePresenter extends BasePresenter<IMineView> {

    private ApiSuStriation mStriation;

    public MinePresenter() {
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
                        getView().onHideDialog();
                    }
                });
    }

    /**
     * banner
     */
    public void bottom() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).indexBotmom(map),
                new ApiCallBack<BaseResult<List<BannerBean>>>() {
                    @Override
                    public void onSuccess(BaseResult<List<BannerBean>> model) {
                        if (model != null) {
                            List<BannerBean> beanList = model.data;
                            if (beanList != null) {
                                getView().onGetBottomSuccess(beanList);
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

    public void acceptVip() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).acceptVip(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> obj) {
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

    public void rejectVip() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).rejectVip(map),
                new ApiCallBack<BaseResult<Object>>() {
                    @Override
                    public void onSuccess(BaseResult<Object> obj) {
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
     * 获取系统配置
     */
    public void onSettingFind() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).onSettingFind(map),
                new ApiCallBack<BaseResult<ConfigBean>>() {
                    @Override
                    public void onSuccess(BaseResult<ConfigBean> model) {
                        if (model != null) {
                            ConfigBean bean = model.data;
                            if (bean != null) {
                                ConfigDate.sConfigBean = bean;
                                UserHelp.updateConfig(bean);
                                EventBus.getDefault().post(new ConfigEvent());
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtil.normal(msg);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * 获取用户消息数量
     */
    public void findMsgCount() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", UserHelp.getUserId());
        mStriation.addSubStriation(ApiFactory.retrofit().create(PostApi.class).getMsgCount(map),
                new ApiCallBack<BaseResult<Integer>>() {
                    @Override
                    public void onSuccess(BaseResult<Integer> model) {
                        if (model != null) {
                            int count = model.data;
                           getView().onMsgCount(count);
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
     * 首次进入我的页面提示弹窗
     */
    public void showDialogMine(Activity activity) {
        DialogBean diabean = new DialogBean();
        diabean.title = activity.getString(R.string.str_mine_dialog_001);
        diabean.content = activity.getString(R.string.str_mine_dialog_002);
        diabean.btntext = activity.getString(R.string.str_mine_dialog_003);
        DialogUtlis.showTaskDialogTis12(activity, diabean, new onItemClickListen() {
            @Override
            public void onItemClick(View v) {
            }

            @Override
            public void onClose() {
            }
        });
    }

}
