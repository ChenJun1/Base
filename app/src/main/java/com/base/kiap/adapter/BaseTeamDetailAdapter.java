package com.base.kiap.adapter;

import android.view.View;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.bean.base.BaseTeamDetailBean;
import com.base.kiap.utlis.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @Author: June
 * @CreateDate: 12/25/20 2:47 PM
 * @Description: Team list
 */
public class BaseTeamDetailAdapter extends BaseQuickAdapter<BaseTeamDetailBean,BaseViewHolder> {
    public BaseTeamDetailAdapter() {
        super(R.layout.item_team_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseTeamDetailBean item) {
        TextView phone = helper.getView(R.id.tv_phone);
        TextView call =helper.getView(R.id.tv_call);
        TextView lastOnlineTime =helper.getView(R.id.lastOnlineTime);
        TextView yesterdayCommission =helper.getView(R.id.yesterdayCommission);
        TextView totalCommission =helper.getView(R.id.totalCommission);
        TextView yesterdayCommissionToMe =helper.getView(R.id.yesterdayCommissionToMe);
        TextView totalCommissionToMe =helper.getView(R.id.totalCommissionToMe);
        phone.setText(item.phone+"");
        lastOnlineTime.setText(item.lastOnlineTime);
        yesterdayCommission.setText("$"+item.yesterdayCommission/100);
        totalCommission.setText("$"+item.totalCommission/100);
        yesterdayCommissionToMe.setText("$"+item.yesterdayCommissionToMe/100);
        totalCommissionToMe.setText("$"+100032/100);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.normal(phone+"");
            }
        });
    }
}
