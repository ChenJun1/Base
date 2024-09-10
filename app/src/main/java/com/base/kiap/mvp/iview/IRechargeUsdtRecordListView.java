package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.oldbean.RechargeRecordBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IRechargeUsdtRecordListView extends IBaseView {
    void onSuccess(List<RechargeRecordBean> recordBeanList);
}
