package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.oldbean.FinancialBean;
import com.base.kiap.bean.oldbean.FinancialSumBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IFinancialView extends IBaseView {
    void onSuccessSum(FinancialSumBean bean);
    void onSuccess();
    void onListSuccess(List<FinancialBean> financialBeanList);
}
