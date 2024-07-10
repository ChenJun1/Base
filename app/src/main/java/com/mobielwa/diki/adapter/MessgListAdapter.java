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
public class MessgListAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {
    public MessgListAdapter() {
        super(R.layout.item_messg);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setText(R.id.tv_messg_title, item.getTitle());
        helper.setText(R.id.tv_time, item.getCreateTime());
        ImageView ivgo=helper.getView(R.id.iv_go);
        TextView title=helper.getView(R.id.tv_messg_title);
        TextView time=helper.getView(R.id.tv_time);

        if (item.getIsRead() == 1) {
            title.setTextColor(mContext.getResources().getColor(R.color.color_font_black_8a8a8a));
            time.setTextColor(mContext.getResources().getColor(R.color.color_font_black_8a8a8a));
            ivgo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_goto_2));
        }else{
            title.setTextColor(mContext.getResources().getColor(R.color.color_font_black_00));
            time.setTextColor(mContext.getResources().getColor(R.color.color_font_black_00));
            ivgo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_goto_));
        }
    }
}
