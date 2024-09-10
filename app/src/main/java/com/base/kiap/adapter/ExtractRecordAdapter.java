package com.base.kiap.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.base.kiap.R;
import com.base.kiap.bean.oldbean.ExtractRecordBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 2:47 PM
 * @Description: 提现记录
 */
public class ExtractRecordAdapter extends BaseQuickAdapter<ExtractRecordBean, BaseViewHolder> {
    public ExtractRecordAdapter() {
        super(R.layout.item_extract_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExtractRecordBean item) {
        TextView tagView = helper.getView(R.id.tv_tag);
        //0提现中，1提现成功，2提现失败
        if (item.getStatus() == 0) {
            tagView.setText(R.string.str_extr_0);
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.cool_green_normal));
        }else if(item.getStatus() == 1){
            tagView.setText(R.string.str_extr_1);
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.cool_green_shade));
        } else if (item.getStatus() == 2) {
            tagView.setText(R.string.str_extr_2);
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.colorf8));
        }
        helper.setText(R.id.tv_bank, item.getTxMode());
        helper.setText(R.id.tv_money, "₹ "+item.getMoney() + "");
        helper.setText(R.id.tv_time, item.getCreateTime());
    }
}
