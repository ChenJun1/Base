package com.mobielwa.diki.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobielwa.diki.R;
import com.mobielwa.diki.bean.RechargeRecordBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 2:47 PM
 * @Description: 提现记录
 */
public class RechargeRecordAdapter extends BaseQuickAdapter<RechargeRecordBean, BaseViewHolder> {
    public RechargeRecordAdapter() {
        super(R.layout.item_recharge_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, RechargeRecordBean item) {
        helper.setText(R.id.tv_bank, item.getTitle());
        helper.setText(R.id.tv_money, item.getMoneyOk()+"");
        helper.setText(R.id.tv_time, item.getCreateTime());
        if(item.getStatus() == 0){
            helper.setText(R.id.tv_status, mContext.getString(R.string.str_pay_state_wait));
            helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.cool_green_normal));
        }else if(item.getStatus() == 1){
            helper.setText(R.id.tv_status, mContext.getString(R.string.str_extr_1));
            helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.cool_green_shade));
        }else if(item.getStatus() == 2){
            helper.setText(R.id.tv_status, mContext.getString(R.string.str_extr_2));
            helper.setTextColor(R.id.tv_status, mContext.getResources().getColor(R.color.color_0606));
        }

    }
}
