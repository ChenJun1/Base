package com.base.kiap.mvp.baseviwe;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.base.BaseTeamDetailBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IBaseTeamDetailView extends IBaseView {
    void onDetailListSuccess(List<BaseTeamDetailBean> bean);
}
