package com.base.kiap.mvp.baseviwe;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.base.BaseTeamIndexInfoBean;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.bean.oldbean.UsdtIndexBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IBaseTeamView extends IBaseView {
    void onIndexSuccess(BaseTeamIndexInfoBean bean);
}
