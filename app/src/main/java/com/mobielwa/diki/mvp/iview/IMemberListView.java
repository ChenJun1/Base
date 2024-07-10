package com.mobielwa.diki.mvp.iview;

import com.mobielwa.diki.base.IBaseView;
import com.mobielwa.diki.bean.MemberBean;
import com.mobielwa.diki.bean.MemberCardBean;
import com.mobielwa.diki.bean.MemberIndexBean;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 12/25/20 3:29 PM
 * @Description: java类作用描述
 */
public interface IMemberListView extends IBaseView {
    void onMemberList(List<MemberCardBean> beanList);
}
