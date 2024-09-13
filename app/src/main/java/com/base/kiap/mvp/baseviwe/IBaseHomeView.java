package com.base.kiap.mvp.baseviwe;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.base.BaseBannerBean;
import com.base.kiap.bean.base.BaseIndexBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:13 PM
 * @Description: java类作用描述
 */
public interface IBaseHomeView extends IBaseView {

    void onIndexSuccess(BaseIndexBean bean);
    void onBannerSuccess(List<BaseBannerBean> bean);

    void onWithdrawSwitchSuccess();

}
