package com.base.kiap.mvp.baseviwe;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.base.BaseUserInfoBean;
import com.base.kiap.bean.base.DepositInrHistoryBean;
import com.base.kiap.bean.base.DepositUstdHistoryBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IBaseDepositHistoryView extends IBaseView {

    void onSuccessInrList(List<DepositInrHistoryBean> bean);

}
