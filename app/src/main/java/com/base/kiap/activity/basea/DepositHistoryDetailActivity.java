package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.base.BaseActivity;
import com.base.kiap.bean.base.DepositInrHistoryBean;
import com.base.kiap.databinding.ActBaseDepositListBinding;
import com.base.kiap.databinding.ActDepositHistoryDetailBinding;
import com.base.kiap.utlis.CommUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/21/20 3:39 PM
 * @Description: Order Detail
 */
public class DepositHistoryDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private static DepositInrHistoryBean depositBean;
    private static int depositType;
    private  ActDepositHistoryDetailBinding binding;
    public static void start(Context context, DepositInrHistoryBean bean,int type) {
        depositBean = bean;
        depositType = type;
        Intent starter = new Intent(context, DepositHistoryDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_deposit_history_detail;
    }

    @Override
    public View attachLayoutView() {
        binding = ActDepositHistoryDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    protected void initData() {
        tvTitle.setText("Detail");
        initImmersionBar();
        if (depositType == 1) {
            if (depositBean != null) {
                binding.orderId.setText(depositBean.orderId);
                binding.utr.setText(depositBean.utr);
                binding.amountOrder.setText(CommUtils.coverMoney(depositBean.amountOrder));
                binding.amountReward.setText(CommUtils.coverMoney(depositBean.amountReward));
                binding.amountActivity.setText(CommUtils.coverMoney(depositBean.amountActivity));
                binding.amountTotal.setText(CommUtils.coverMoney(depositBean.amountTotal));
                binding.receiveAccountNo.setText(depositBean.receiveAccountNo);
                binding.receiveAccountName.setText(depositBean.receiveAccountName);
                binding.receiveIfsc.setText(depositBean.receiveIfsc);
                binding.createTime.setText(depositBean.createTime);
                binding.notifyTime.setText(depositBean.notifyTime);
                binding.payStatus.setText(depositBean.payStatus+"");
            }
        }else{
            binding.orderId.setText(depositBean.orderId);
            binding.utr.setText(depositBean.utr);
            binding.amountOrder.setText(CommUtils.coverMoney(depositBean.amountOrder));
            binding.amountReward.setText(CommUtils.coverMoney(depositBean.amountReward));
            binding.amountActivity.setText(CommUtils.coverMoney(depositBean.amountActivity));
            binding.amountTotal.setText(CommUtils.coverMoney(depositBean.amountTotal));
            binding.receiveAccountNo.setText(depositBean.receiveAccountNo);
            binding.receiveAccountName.setText(depositBean.receiveAccountName);
            binding.receiveIfsc.setText(depositBean.receiveIfsc);
            binding.createTime.setText(depositBean.createTime);
            binding.notifyTime.setText(depositBean.notifyTime);
            binding.payStatus.setText(depositBean.payStatus+"");
        }

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
