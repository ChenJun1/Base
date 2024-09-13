package com.base.kiap.adapter;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.bean.base.DepositInrHistoryBean;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.utlis.CommUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


public class BaseDepositHistoryAdapter extends BaseQuickAdapter<DepositInrHistoryBean,BaseViewHolder> {
    public BaseDepositHistoryAdapter() {
        super(R.layout.item_deposit_history);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, DepositInrHistoryBean item) {
       TextView tv_inr_numb=  helper.getView(R.id.tv_inr_numb);
        TextView tv_status= helper.getView(R.id.tv_status);
        TextView amountReward=helper.getView(R.id.amountReward);
        TextView tv_orderId= helper.getView(R.id.tv_orderId);
        TextView tv_time= helper.getView(R.id.tv_time);

        tv_inr_numb.setText(CommUtils.coverMoney(item.amountOrder));
        tv_status.setText(item.payStatus + "");
        amountReward.setText("Reward: + " + CommUtils.coverMoney(item.amountReward));
        tv_orderId.setText(item.orderId);
        tv_time.setText(item.createTime);
    }
}
