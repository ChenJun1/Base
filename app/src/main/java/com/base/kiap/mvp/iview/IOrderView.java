package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.OrderBean;
import com.base.kiap.bean.UsdtIndexBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IOrderView extends IBaseView {
    void onIndex(UsdtIndexBean bean);
    void onGetOrderSuccess(List<OrderBean> orderBeanList);
    void onChangelv();
}
