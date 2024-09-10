package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.oldbean.BannerBean;
import com.base.kiap.bean.oldbean.UserBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IMineView extends IBaseView {
    void onMineInfoSuccess(UserBean bean);

    void onGetBottomSuccess(List<BannerBean> beanList);

    void onMsgCount(int msgCount);
}
