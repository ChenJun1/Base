package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.ApplyBean;
import com.mobielwa.diki.bean.ApplyChannBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IRechargeView extends IBaseView {
    void onSuccess(ApplyBean bean);
}
