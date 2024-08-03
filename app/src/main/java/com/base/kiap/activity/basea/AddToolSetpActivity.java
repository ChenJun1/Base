package com.base.kiap.activity.basea;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.base.kiap.R;
import com.base.kiap.base.BaseMvpActivity;
import com.base.kiap.bean.dao.MessageBean;
import com.base.kiap.databinding.ActAddToolSetpBinding;
import com.base.kiap.fragment.BaseToolSetp1Fragment;
import com.base.kiap.fragment.BaseToolSetp2Fragment;
import com.base.kiap.fragment.BaseToolSetp3Fragment;
import com.base.kiap.mvp.iview.IMessgListView;
import com.base.kiap.mvp.presenter.MessgListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**

 */
public class AddToolSetpActivity extends BaseMvpActivity<IMessgListView, MessgListPresenter> implements IMessgListView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddToolSetpActivity.class);
        context.startActivity(starter);
    }


    private BaseToolSetp1Fragment step1Fragment = new BaseToolSetp1Fragment();
    private BaseToolSetp2Fragment step2Fragment = new BaseToolSetp2Fragment();
    private BaseToolSetp3Fragment step3Fragment = new BaseToolSetp3Fragment();

    private List<Fragment> fragmentList = new ArrayList<>();
    private ActAddToolSetpBinding binding;
    @Override
    protected int attachLayoutRes() {
        return R.layout.act_add_tool_setp;
    }

    @Override
    public View attachLayoutView() {
        binding = ActAddToolSetpBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        tvTitle.setText("Add Tool");
        initImmersionBar();
        fragmentList.add(step1Fragment);
        fragmentList.add(step2Fragment);
        fragmentList.add(step3Fragment);
//        showLoading();
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fr_content, step1Fragment)
                .commit();
    }

    public void replaceFragment(int index) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fr_content, fragmentList.get(index))
                .commit();
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
