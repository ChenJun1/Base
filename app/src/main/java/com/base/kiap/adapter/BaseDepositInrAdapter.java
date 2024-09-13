package com.base.kiap.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.bean.base.DepositInrInfoBean;
import com.base.kiap.listen.onItemClickListen11;
import com.base.kiap.utlis.CommUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


public class BaseDepositInrAdapter extends BaseQuickAdapter<DepositInrInfoBean,BaseViewHolder> {
    private onItemClickListen11 listen;
    public BaseDepositInrAdapter() {
        super(R.layout.item_deposit_01);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, DepositInrInfoBean item) {
        TextView inr= helper.getView(R.id.tv_inr_numb);
        TextView incom =helper.getView(R.id.tv_income);
        TextView income_ra = helper.getView(R.id.tv_income_ra);
        TextView quota  =helper.getView(R.id.tv_quota);
        TextView bankName  =helper.getView(R.id.bank_name);

        inr.setText(CommUtils.coverMoney(item.amountOrder) + " INR");
        incom.setText("₹ " + CommUtils.coverMoney(item.amountReward));
        quota.setText("+ " + CommUtils.coverMoney(item.amountQuota));
        income_ra.setText("(" + item.percentage+ "%)+"+CommUtils.coverMoney(item.amountActivity)+"(Activity)");
        bankName.setText(item.bankName);
        if (listen != null) {
            helper.getView(R.id.tv_buy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listen.onItemClick(v,item);
                }
            });
        }


    }
    public void setListen(onItemClickListen11 listen) {
        this.listen = listen;
    }
}
