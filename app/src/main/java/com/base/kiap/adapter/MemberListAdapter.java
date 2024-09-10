package com.base.kiap.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.base.kiap.R;
import com.base.kiap.bean.oldbean.MemberCardBean;

/**
 * @Author: June
 * @CreateDate: 12/11/20 4:05 PM
 * @Description: 会员
 */
public class MemberListAdapter extends BaseQuickAdapter<MemberCardBean, BaseViewHolder> {

    public MemberListAdapter() {
        super(R.layout.item_vip);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberCardBean item) {
        helper.setText(R.id.tv_task_sum, item.getAccountNumber());//账户号码
        helper.setText(R.id.vip_title, item.getUsername()+"[ID:"+item.getUserId()+"]");//姓名+ID

        helper.setText(R.id.tv_commission, item.getBankName());//银行名称
        TextView tvStatus = helper.getView(R.id.tv_income);
        if(item.getStat() == 0){//账户状态（0待审核，1启用，2无效，3停止）
            tvStatus.setText(mContext.getResources().getText(R.string.str_pay_member_status1));
            tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryGreen));
        }else if(item.getStat() == 1){//账户状态（0待审核，1启用，2无效，3停止）
            tvStatus.setText(mContext.getResources().getText(R.string.str_pay_member_status2));
            tvStatus.setTextColor(mContext.getResources().getColor(R.color.cool_green_shade));
        }else if(item.getStat() == 2){//账户状态（0待审核，1启用，2无效，3停止）
            tvStatus.setText(mContext.getResources().getText(R.string.str_pay_member_status3));
            tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorf8));
        }else if(item.getStat() == 3){//账户状态（0待审核，1启用，2无效，3停止）
            tvStatus.setText(mContext.getResources().getText(R.string.str_pay_member_status4));
            tvStatus.setTextColor(mContext.getResources().getColor(R.color.colorf8));
        }

        tvStatus.setTextColor(mContext.getResources().getColor(R.color.cool_green_normal));
        helper.setText(R.id.tv_balance_baozheng, "₹ "+item.getTotalAmount());//今日交易额
        helper.setText(R.id.price0, item.getRate());//费率0.3%
        helper.setText(R.id.tv_profit, "₹ "+item.getProfit());//今日收益
        helper.setText(R.id.tv_total_profit, "₹ "+item.getTotalProfit());//总收益
    }
}
