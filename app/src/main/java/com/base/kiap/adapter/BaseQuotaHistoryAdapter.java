package com.base.kiap.adapter;

import com.base.kiap.R;
import com.base.kiap.bean.dao.MessageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


public class BaseQuotaHistoryAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {
    public BaseQuotaHistoryAdapter() {
        super(R.layout.item_quota_history);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

    }
}
