package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.UsdtBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IRechargeUsdtView extends IBaseView {
    void onSuccess(UsdtBean bean);
    void onFail();
}
