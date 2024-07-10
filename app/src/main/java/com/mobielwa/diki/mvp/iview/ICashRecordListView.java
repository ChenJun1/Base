package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.CashRecordBean;
import com.mobielwa.diki.bean.ExtractRecordBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface ICashRecordListView extends IBaseView {
    void onIncomeSuccess(List<CashRecordBean> cashRecordBeanList);
    void onPaySuccess(List<CashRecordBean> cashRecordBeanList);
}
