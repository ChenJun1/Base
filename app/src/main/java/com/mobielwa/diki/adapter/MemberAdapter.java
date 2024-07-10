package com.mobielwa.diki.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobielwa.diki.R;
import com.mobielwa.diki.bean.MemberBean;
import com.mobielwa.diki.listen.onItemClickListen4;

/**
 * @Author: June
 * @CreateDate: 12/11/20 4:05 PM
 * @Description: 会员
 */
public class MemberAdapter extends BaseQuickAdapter<MemberBean, BaseViewHolder> {
    private onItemClickListen4 listener;

    public MemberAdapter(onItemClickListen4 mlistener) {
        super(R.layout.item_member);
        listener = mlistener;
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberBean item) {
        helper.setText(R.id.tv_task_sum, item.getAccountCount()+"");//账户数量
        helper.setText(R.id.vip_id, item.getId()+"");//账户ID
        helper.setText(R.id.vip_title, item.getUsername()+"");//姓名

        helper.getView(R.id.ll_memeber_detail).setOnClickListener(v -> listener.onItemClick(v,item));
        helper.getView(R.id.ll_memeber_detail0).setOnClickListener(v -> listener.onItemClick(v,item));
    }
}
