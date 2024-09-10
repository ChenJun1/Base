package com.base.kiap.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.base.BasePresenter;
import com.base.kiap.bean.oldbean.OrderBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 2/26/21 10:09 AM
 * @Description: java类作用描述
 */
public class ExchangeDetailActivity extends BaseMvpActivity {

    private static final String MSG = "order_detail";

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_orderId)
    TextView tv_orderId;
    @BindView(R.id.tv_usdt)
    TextView tv_usdt;
    @BindView(R.id.tv_amount)
    TextView tv_amount;
    @BindView(R.id.tv_rate)
    TextView tv_rate;
    @BindView(R.id.tv_accountNo)
    TextView tv_accountNo;
    @BindView(R.id.tv_accountHolder)
    TextView tv_accountHolder;
    @BindView(R.id.tv_ifsc)
    TextView tv_ifsc;
    @BindView(R.id.tv_createTime)
    TextView tv_createTime;
    @BindView(R.id.tv_transactionTime)
    TextView tv_transactionTime;
    @BindView(R.id.tv_transactionStatus)
    TextView tv_transactionStatus;

    public static void start(Context context, OrderBean bean) {
        Intent starter = new Intent(context, ExchangeDetailActivity.class);
        starter.putExtra(MSG, bean);
        context.startActivity(starter);
    }

    private OrderBean bean=null;

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_exchange_detail;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.str_messg_title);
        bean=getIntent().getParcelableExtra(MSG);
        if (bean!=null) {
            tv_orderId.setText(bean.getTxMode());
            tv_usdt.setText(bean.getMoney()/10000D+"");
            tv_amount.setText(bean.getAmount()/100D+"");
            tv_rate.setText(bean.getRate()/100D+"");
            tv_accountNo.setText(bean.getAccountNo());
            tv_accountHolder.setText(bean.getAccountHolder());
            tv_ifsc.setText(bean.getIfsc());
            tv_createTime.setText(bean.getCreateTime());
            tv_transactionTime.setText(bean.getTransactionTime());
            if(bean.getStatus() == 1){
                tv_transactionStatus.setText(getResources().getText(R.string.str_pay_usdt_status2));
                tv_transactionStatus.setTextColor(getResources().getColor(R.color.cool_green_shade));
            }else if(bean.getStatus() == 0){
                tv_transactionStatus.setText(getResources().getText(R.string.str_pay_usdt_status1));
            }else if(bean.getStatus() == 2){
                tv_transactionStatus.setText(getResources().getText(R.string.str_pay_usdt_status3));
                tv_transactionStatus.setTextColor(getResources().getColor(R.color.colorf8));
            }
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
