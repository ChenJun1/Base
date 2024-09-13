package com.base.kiap.mvp.baseviwe;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.base.BaseUserInfoBean;
import com.base.kiap.bean.base.DepositUsdtInfoBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IBaseDepositUsdtView extends IBaseView {

    void onSuccess(DepositUsdtInfoBean bean);
}
