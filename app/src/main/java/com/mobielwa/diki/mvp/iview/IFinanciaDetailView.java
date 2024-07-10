package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.DistriFirendBean;
import com.mobielwa.diki.bean.DistriSum;
import com.mobielwa.diki.bean.FinancialOrderBean;
import com.mobielwa.diki.bean.FinancialSumBean;

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
