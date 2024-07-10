package com.mobielwa.diki.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobielwa.diki.R;
import com.mobielwa.diki.bean.dao.MessageBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 2:47 PM
 * @Description: 消息列表
 */
public class FaqListAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {
    public FaqListAdapter() {
        super(R.layout.item_faq);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setText(R.id.tv_messg_title, item.getTitle());
        ImageView ivgo=helper.getView(R.id.iv_go);
        TextView title=helper.getView(R.id.tv_messg_title);

        if (item.getIsRead() == 1) {
            title.setTextColor(mContext.getResources().getColor(R.color.color_font_black_8a8a8a));
            ivgo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_goto_2));
        }else{
            title.setTextColor(mContext.getResources().getColor(R.color.color_font_black_00));
            ivgo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_goto_));
        }
    }
}
