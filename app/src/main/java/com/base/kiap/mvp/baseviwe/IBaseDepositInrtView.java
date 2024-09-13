package com.base.kiap.mvp.baseviwe;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.base.BaseWithdrawBean;
import com.base.kiap.bean.base.DepositInrInfoBean;
import com.base.kiap.bean.base.DepositUsdtInfoBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IBaseDepositInrtView extends IBaseView {

    void onSuccess(List<DepositInrInfoBean> list);
    void onBuySuccess();
}
