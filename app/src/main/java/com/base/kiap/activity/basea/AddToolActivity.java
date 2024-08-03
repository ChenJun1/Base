package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.adapter.TeamAdapter;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.databinding.ActBaseAddToolBinding;
import com.base.kiap.databinding.ActBasePayPassBinding;
import com.base.kiap.mvp.iview.IMessgListView;
import com.base.kiap.mvp.presenter.MessgListPresenter;
import com.base.kiap.utlis.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**

 */
public class AddToolActivity extends BaseMvpActivity<IMessgListView, MessgListPresenter> implements IMessgListView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddToolActivity.class);
        context.startActivity(starter);
    }

    private TeamAdapter adapter;
    private List<MessageBean> mList = new ArrayList<>();
    private ActBaseAddToolBinding binding;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_add_tool;
    }

    @Override
    public View attachLayoutView() {
        binding = ActBaseAddToolBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        tvTitle.setText("Add Tool");
        initImmersionBar();
//        showLoading();
        binding.rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToolSetpActivity.start(AddToolActivity.this);
            }
        });
    }



    @Override
    protected MessgListPresenter createPresenter() {
        return new MessgListPresenter();
    }

    @Override
    public void onSuccess(List<MessageBean> list) {

    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


}
