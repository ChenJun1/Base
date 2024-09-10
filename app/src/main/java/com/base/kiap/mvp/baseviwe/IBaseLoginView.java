package com.base.kiap.mvp.baseviwe;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.base.BaseUserBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 10:12 AM
 * @Description: java类作用描述
 */
public interface IBaseLoginView extends IBaseView {
    void onLoginSuccess(BaseUserBean bean);

}
