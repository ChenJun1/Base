package com.base.kiap.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.base.kiap.R;
import com.base.kiap.bean.dao.MessageBean;

/**
 * @Author: June
 * @CreateDate: 12/25/20 2:47 PM
 * @Description: Team list
 */
public class TeamAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {
    public TeamAdapter() {
        super(R.layout.item_team);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

    }
}
