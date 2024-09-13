package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.base.BaseActivity;
import com.base.kiap.config.UserHelp;
import com.base.kiap.databinding.ActBaseTeamDetailListBinding;
import com.base.kiap.databinding.ActBaseUserInfoBinding;
import com.base.kiap.utlis.CommUtils;
import com.base.kiap.utlis.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class BaseUserInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ActBaseUserInfoBinding binding;
    public static void start(Context context) {
        Intent starter = new Intent(context, BaseUserInfoActivity.class);
        context.startActivity(starter);
    }
    public View attachLayoutView() {
        binding = ActBaseUserInfoBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_user_info;
    }

    @Override
    protected void initData() {
        tvTitle.setText("User Info");
        binding.tvPhone.setText(UserHelp.getPhone());
        binding.tvPhone2.setText(UserHelp.getPhone());
        binding.tvUserId.setText(UserHelp.getUserId());
        binding.tvUserid2.setText(UserHelp.getUserId());
        binding.tvEmail.setText(UserHelp.getMail());
        binding.tvTelegram.setText(UserHelp.getTelegram());
        binding.tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommUtils.copy(binding.tvPhone.getText().toString());
                ToastUtil.success("Copy Success");
            }
        });
        binding.tvUserId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommUtils.copy(binding.tvPhone.getText().toString());
                ToastUtil.success("Copy Success");
            }
        });
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        if (mClickHelper.click()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
