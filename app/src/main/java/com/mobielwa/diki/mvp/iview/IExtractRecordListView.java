package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.ExtractRecordBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IExtractRecordListView extends IBaseView {
    void onSuccess(List<ExtractRecordBean> recordBeanList);
}
