package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.CodeBean;
import com.mobielwa.diki.bean.UserBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 10:12 AM
 * @Description: java类作用描述
 */
public interface ILoginView extends IBaseView {
    void onLoginSuccess(UserBean bean);

}
