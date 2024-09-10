package com.base.kiap.mvp.iview;

import com.base.kiap.base.IBaseView;
import com.base.kiap.bean.oldbean.MemberBean;
import com.base.kiap.bean.oldbean.MemberIndexBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IMemberView extends IBaseView {
    void onMemberIndex(MemberIndexBean beanList);
    void onMemberList(List<MemberBean> beanList);
}
