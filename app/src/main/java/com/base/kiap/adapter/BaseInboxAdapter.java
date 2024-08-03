package com.base.kiap.adapter;

import com.base.kiap.R;
import com.base.kiap.bean.dao.MessageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


public class BaseInboxAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {
    public BaseInboxAdapter() {
        super(R.layout.item_msg);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

    }
}
