package com.base.kiap.adapter;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.base.kiap.R;
import com.base.kiap.bean.oldbean.OrderBean;
import com.base.kiap.listen.onItemClickListen3;

/**
 * @Author: June
 * @CreateDate: 12/11/20 4:05 PM
 * @Description: 首页任务
 */
public class HomeAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    private onItemClickListen3 listener;
    public HomeAdapter(onItemClickListen3 mlistener) {
        super(R.layout.item_home_orders);
        listener = mlistener;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
    }
}
