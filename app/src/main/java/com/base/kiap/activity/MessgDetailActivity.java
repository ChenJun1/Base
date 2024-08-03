package com.base.kiap.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.dao.MessageBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 2/26/21 10:09 AM
 * @Description: java类作用描述
 */
public class MessgDetailActivity extends BaseMvpActivity {

    private static final String MSG = "msg";

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_msg_title)
    TextView tvMsgTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;
    
    public static void start(Context context, MessageBean bean) {
        Intent starter = new Intent(context, MessgDetailActivity.class);
        starter.putExtra(MSG, bean);
        context.startActivity(starter);
    }

    private MessageBean bean=null;

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_messgdetail;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.str_messg_title);
        bean=getIntent().getParcelableExtra(MSG);
        if (bean!=null) {
            tvMsgTitle.setText(bean.getTitle());
            tvContent.setText(bean.getContent());
            tvTime.setText(bean.getCreateTime());
        }

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
