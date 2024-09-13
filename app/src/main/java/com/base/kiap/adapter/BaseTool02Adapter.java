package com.base.kiap.adapter;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.bean.base.BaseBankInfoBean;
import com.base.kiap.bean.dao.MessageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.w3c.dom.Text;


public class BaseTool02Adapter extends BaseQuickAdapter<BaseBankInfoBean, BaseViewHolder> {
    public BaseTool02Adapter() {
        super(R.layout.item_tool_02);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, BaseBankInfoBean item) {
        TextView bankName = helper.getView(R.id.bankName);
        TextView bankAccount = helper.getView(R.id.bankAccount);
        TextView ifsc = helper.getView(R.id.ifsc);
        TextView status = helper.getView(R.id.status);
        bankName.setText(item.bankName);
        bankAccount.setText(item.bankAccount);
        ifsc.setText(item.ifsc);
        status.setText(item.status+"");
    }
}
