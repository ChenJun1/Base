package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.ApplyChannBean;
import com.mobielwa.diki.bean.PayoutGetBean;
import com.mobielwa.diki.bean.WithdrawalFeeBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IExtractView extends IBaseView {
    void onSuccess();
    void finishSuccess(PayoutGetBean bean);
    void onfinish();
    void onFailure(String content);

}
