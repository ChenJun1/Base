package com.mobielwa.diki.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobielwa.diki.R;
import com.mobielwa.diki.bean.OrderBean;
import com.mobielwa.diki.listen.onItemClickListen3;

/**
 * @Author: June
 * @CreateDate: 12/11/20 4:05 PM
 * @Description: 接单
 */
public class OrdersAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    private onItemClickListen3 listener;
    public OrdersAdapter(onItemClickListen3 mlistener) {
        super(R.layout.item_orders);
        listener = mlistener;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        LinearLayout llayout = helper.getView(R.id.line_orders);
        llayout.setOnClickListener(v -> listener.onItemClick(v,item));
        helper.setText(R.id.usdt_number, "" + item.getMoney()/10000D + " USDT");
        helper.setText(R.id.tv_balance, "" + item.getAmount()/100D);
        helper.setText(R.id.tv_lock1, mContext.getResources().getString(R.string.str_pay_usdt_rate)+": ₹ " + item.getRate()/100D);
        TextView tv_lock2 = helper.getView(R.id.tv_lock2);
        if(item.getStatus() == 1){
            tv_lock2.setText(item.getCreateTime());
            tv_lock2.setTextColor(mContext.getResources().getColor(R.color.color_font_black_21));
        }else if(item.getStatus() == 0){
            tv_lock2.setText(mContext.getResources().getText(R.string.str_pay_usdt_status1));
            tv_lock2.setTextColor(mContext.getResources().getColor(R.color.cool_green_shade));
        }else if(item.getStatus() == 2){
            tv_lock2.setText(mContext.getResources().getText(R.string.str_pay_usdt_status3));
            tv_lock2.setTextColor(mContext.getResources().getColor(R.color.colorf8));
        }

    }
}
