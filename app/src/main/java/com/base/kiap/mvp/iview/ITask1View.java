package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.TaskOrderBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface ITask1View extends IBaseView {
    void onGetTaskSuccess(List<TaskOrderBean> orderBeans);

    void onCommitSuccess();
}
