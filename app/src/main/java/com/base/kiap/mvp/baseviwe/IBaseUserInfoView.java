package com.base.kiap.mvp.baseviwe;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.base.BaseUserInfoBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IBaseUserInfoView extends IBaseView {

    void onUserInfo(BaseUserInfoBean bean);
    void onOutSuccess();
}
