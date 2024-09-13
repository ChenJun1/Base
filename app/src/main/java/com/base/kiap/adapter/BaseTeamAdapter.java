package com.base.kiap.adapter;

import android.view.View;
import android.widget.TextView;

import com.base.kiap.bean.base.BaseTeamDetailBean;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.utlis.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.base.kiap.R;

/**
 * @Author: June
 * @CreateDate: 12/25/20 2:47 PM
 * @Description: Team list
 */
public class BaseTeamAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {
    public BaseTeamAdapter() {
        super(R.layout.item_team);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
//        TextView phone = helper.getView(R.id.tv_phone);
//        TextView call =helper.getView(R.id.tv_call);
//        TextView lastOnlineTime =helper.getView(R.id.lastOnlineTime);
//        TextView yesterdayCommission =helper.getView(R.id.yesterdayCommission);
//        TextView totalCommission =helper.getView(R.id.totalCommission);
//        TextView yesterdayCommissionToMe =helper.getView(R.id.yesterdayCommissionToMe);
//        TextView totalCommissionToMe =helper.getView(R.id.totalCommissionToMe);
//        phone.setText(item.phone+"");
//        lastOnlineTime.setText(item.lastOnlineTime);
//        yesterdayCommission.setText("$"+item.yesterdayCommission/100);
//        totalCommission.setText("$"+item.totalCommission/100);
//        yesterdayCommissionToMe.setText("$"+item.yesterdayCommissionToMe/100);
//        totalCommissionToMe.setText("$"+100032/100);
//        call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtil.normal(phone+"");
//            }
//        });
    }
}
