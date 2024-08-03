package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.FinancialOrderBean;
import com.base.kiap.bean.FinancialSumBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IFinanciaDetailView extends IBaseView {
    void onSuccess();
    void onSuccessSum(FinancialSumBean bean);
    void onListSuccess(List<FinancialOrderBean> FinancialBean);
}
