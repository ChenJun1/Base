package com.base.kiap.adapter;

import com.base.kiap.R;
import com.base.kiap.bean.dao.MessageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


public class BaseDeposit01Adapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {
    public BaseDeposit01Adapter() {
        super(R.layout.item_deposit_01);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

    }
}
