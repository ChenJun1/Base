package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.BannerBean;
import com.mobielwa.diki.bean.UserBean;

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
