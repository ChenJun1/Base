package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.BannerBean;
import com.mobielwa.diki.bean.OrderBean;
import com.mobielwa.diki.bean.dao.MessageBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:13 PM
 * @Description: java类作用描述
 */
public interface IMessgListView extends IBaseView {
    void onSuccess(List<MessageBean> list);


}
