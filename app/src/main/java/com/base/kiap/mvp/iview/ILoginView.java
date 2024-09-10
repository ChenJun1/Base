package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.oldbean.UserBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 10:12 AM
 * @Description: java类作用描述
 */
public interface ILoginView extends IBaseView {
    void onLoginSuccess(UserBean bean);

}
