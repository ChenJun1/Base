package com.mobielwa.diki.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobielwa.diki.R;
import com.mobielwa.diki.bean.CashRecordBean;
import com.mobielwa.diki.utlis.StringFormatUtils;

/**
 * @Author: June
 * @CreateDate: 12/25/20 2:47 PM
 * @Description: 收益明细
 */
public class CashRecordAdapter extends BaseQuickAdapter<CashRecordBean, BaseViewHolder> {
    public CashRecordAdapter() {
        super(R.layout.item_cash_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, CashRecordBean item) {

        TextView tagView = helper.getView(R.id.tv_tag);
        if (item.getTagName()==null||item.getTagName().isEmpty()) {
            tagView.setVisibility(View.GONE);
        }else{
            tagView.setVisibility(View.VISIBLE);
        }
        tagView.setText(item.getTagName());
//1、大装盘、2、开宝箱、3、任务、4、邀请分享、5、充值、6、提现、7、余额升级、8购买升级、9朋友升级奖励、10提现、11提现失败退款
        if (item.getTag() == 0) {
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label03));
        }else if(item.getTag() == 1){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label02));
        } else if (item.getTag() == 2) {
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label01));
        }else if (item.getTag() == 4) {
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label04));
        }else  if (item.getTag() == 5){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label05));
        }else if (item.getTag() == 6){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label02));
        }
        else if (item.getTag() == 7){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label01));
        }else if (item.getTag() == 8){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label04));
        }else if (item.getTag() == 9){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label05));
        }else if (item.getTag() == 10){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label02));
        }else if (item.getTag() == 11){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label03));
        }else if (item.getTag() == 12){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label04));
        }else if (item.getTag() == 13){
            tagView.setBackgroundColor(mContext.getResources().getColor(R.color.color_label01));
        }

        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_time, item.getCreateTime());
        if (item.getIsflag() == 1) {
            helper.setText(R.id.tv_money, "+Rs "+StringFormatUtils.moneyFormat(item.getMoney()) + "");
        }else if(item.getIsflag() == 2){
            helper.setText(R.id.tv_money, "-Rs "+StringFormatUtils.moneyFormat(item.getMoney()) + "");
        }
    }
}
