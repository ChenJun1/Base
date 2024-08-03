package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.IndexBean;
import com.base.kiap.bean.OrderBean;
import com.base.kiap.bean.UserBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:13 PM
 * @Description: java类作用描述
 */
public interface IHomeView extends IBaseView {

    void onMineInfoSuccess(UserBean bean);

    void onIndexSuccess(IndexBean bean);

    void onGetOrderSuccess(List<OrderBean> orderBeanList);
}
