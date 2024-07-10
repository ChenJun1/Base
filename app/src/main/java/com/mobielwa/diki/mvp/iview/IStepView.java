package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.ApplyBean;
import com.mobielwa.diki.bean.BindBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IStepView extends IBaseView {
    void onSuccess(String str);
    void bindObj(BindBean obj);
}
