package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.OrderBean;
import com.mobielwa.diki.bean.UsdtIndexBean;

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
