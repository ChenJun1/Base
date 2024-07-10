package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.DistriFirendBean;
import com.mobielwa.diki.bean.DistriSum;
import com.mobielwa.diki.bean.FinancialBean;
import com.mobielwa.diki.bean.FinancialSumBean;

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
