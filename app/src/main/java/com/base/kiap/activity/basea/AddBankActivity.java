package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.kiap.R;
import com.base.kiap.activity.TeamDetailActivity;
import com.base.kiap.adapter.TeamAdapter;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.config.Constants;
import com.base.kiap.databinding.ActBaseAddBankBinding;
import com.base.kiap.databinding.ActBaseAddToolBinding;
import com.base.kiap.databinding.ActBasePayPassBinding;
import com.base.kiap.mvp.iview.IMessgListView;
import com.base.kiap.mvp.presenter.MessgListPresenter;
import com.base.kiap.utlis.RecyclerViewLoadUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 2/25/21 3:53 PM
 * @Description: DepositHistory
 */
public class AddBankActivity extends BaseMvpActivity<IMessgListView, MessgListPresenter> implements IMessgListView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddBankActivity.class);
        context.startActivity(starter);
    }

    private TeamAdapter adapter;
    private List<MessageBean> mList = new ArrayList<>();
    private ActBaseAddBankBinding binding;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_base_add_bank;
    }
    @Override
    public View attachLayoutView() {
        binding = ActBaseAddBankBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    protected void initData() {
        tvTitle.setText("Add Bank");
        initImmersionBar();
//        showLoading();

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
