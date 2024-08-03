package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.dao.MessageBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:13 PM
 * @Description: java类作用描述
 */
public interface IMessgListView extends IBaseView {
    void onSuccess(List<MessageBean> list);


}
