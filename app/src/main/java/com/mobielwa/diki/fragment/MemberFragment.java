package com.mobielwa.diki.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mobielwa.diki.R;
import com.mobielwa.diki.activity.CashRecordActivity;
import com.mobielwa.diki.activity.MemeberListActivity;
import com.mobielwa.diki.activity.RechargeActivity;
import com.mobielwa.diki.adapter.MemberAdapter;
import com.mobielwa.diki.base.BaseFragment2;
import com.mobielwa.diki.bean.DialogBean;
import com.mobielwa.diki.bean.FinancialBean;
import com.mobielwa.diki.bean.MemberBean;
import com.mobielwa.diki.bean.MemberIndexBean;
import com.mobielwa.diki.bean.event.Main2OrderEvent;
import com.mobielwa.diki.bean.event.MemberDialogEvent;
import com.mobielwa.diki.bean.event.ReshUserEvent;
import com.mobielwa.diki.config.ConfigDate;
import com.mobielwa.diki.config.Constants;
import com.mobielwa.diki.config.SpCode;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.listen.onItemClickListen;
import com.mobielwa.diki.listen.onItemClickListen10;
import com.mobielwa.diki.listen.onItemClickListen4;
import com.mobielwa.diki.listen.onItemClickListen9;
import com.mobielwa.diki.mvp.iview.IMemberView;
import com.mobielwa.diki.mvp.presenter.MemberPresenter;
import com.mobielwa.diki.utlis.DateUtil;
import com.mobielwa.diki.utlis.DialogUtlis;
import com.mobielwa.diki.utlis.RecyclerViewLoadUtil;
import com.mobielwa.diki.utlis.SPUtils;
import com.mobielwa.diki.utlis.ShareUtils;
import com.mobielwa.diki.utlis.StringFormatUtils;
import com.mobielwa.diki.utlis.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * @Author: June
 * @CreateDate: 12/11/20 2:36 PM
 * @Description: 会员
 */
public class MemberFragment extends BaseFragment2<IMemberView, MemberPresenter> implements IMemberView, onItemClickListen4 {

    TextView today_profit;
    TextView total_profit;
    TextView tv_11;
    TextView tv_upgrade_content;
    Button tv_upgrade;
    Button tv_upgrade1;
    Button tv_add;
    TextView tv_memeber_number;

    MemberBean currentBean;

    public static MemberFragment newInstance() {

        Bundle args = new Bundle();
        MemberFragment fragment = new MemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.home_rv)
    RecyclerView homeRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private MemberAdapter adapter;
    private List<MemberBean> mBeanList = new ArrayList<>();

    @Override
    public int attachLayoutRes() {
        return R.layout.frm_member;
    }

    @Override
    public void initView(@NotNull View view) {

    }

    @Override
    protected void lazyLoad() {
        initRv();
        getPresenter().memberIndex();

    }

    private void initViewDate() {
        if (mBeanList == null || mBeanList.size() == 0) {
            getPresenter().memberList();
            return;
        }
        mBeanList.size();
        List<MemberBean> list = new ArrayList<>(mBeanList);
        mBeanList.clear();
        mBeanList.addAll(list);
        adapter.setNewData(mBeanList);
    }

    @Override
    public void onResume() {
        super.onResume();
        initViewDate();
    }

    @Override
    protected MemberPresenter createPresenter() {
        return new MemberPresenter();
    }

    private void initRv() {
        adapter = new MemberAdapter(this);
        addHeaderView();
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        homeRv.setLayoutManager(linearLayoutManager);
        RecyclerViewLoadUtil.rvLoad(homeRv);
        homeRv.setAdapter(adapter);
        adapter.setPreLoadNumber(1);
        adapter.setNewData(mBeanList);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            refreshLayout.finishRefresh(Constants.RefreshTime, true);
            showLoading();
            getPresenter().memberList();
        });
    }


    private void addHeaderView() {
        View headView = LayoutInflater.from(mActivity).inflate(R.layout.include_memeber1_title, (ViewGroup) homeRv.getParent(), false);
        today_profit = headView.findViewById(R.id.today_profit);
        total_profit = headView.findViewById(R.id.total_profit);
        tv_upgrade = headView.findViewById(R.id.tv_upgrade);
        tv_upgrade_content = headView.findViewById(R.id.tv_upgrade_content);
        adapter.addHeaderView(headView);

        View headView1 = LayoutInflater.from(mActivity).inflate(R.layout.include_memeber2_title, (ViewGroup) homeRv.getParent(), false);
        tv_11 = headView1.findViewById(R.id.tv_11);
        tv_memeber_number = headView1.findViewById(R.id.tv_memeber_number);
        tv_upgrade1 = headView1.findViewById(R.id.tv_upgrade1);
        tv_add = headView1.findViewById(R.id.tv_add);
        adapter.addHeaderView(headView1);

        tv_upgrade.setOnClickListener(v -> CashRecordActivity.start(mActivity));
        tv_upgrade1.setOnClickListener(v -> MemeberListActivity.start(mActivity));
        tv_add.setOnClickListener(v ->
                showBuyDialog(mActivity, (onItemClickListen10) (v1, memberId) -> {
                    if (mClickHelper.click()) {
                        return;
                    }
                    showLoading();
                    getPresenter().addMember(memberId);
                }));

    }

    @Override
    public void onMemberIndex(MemberIndexBean bean) {
        today_profit.setText("₹ "+bean.getTodayProfit());
        total_profit.setText("₹ "+bean.getTotalProfit());
        tv_11.setText(bean.getUsername()+"[ID:"+bean.getId()+"]");
        tv_upgrade_content.setText(String.format(getString(R.string.str_member_baozhengjin), bean.getDepositProfit()));
        tv_memeber_number.setText(bean.getNumber()+"");
    }

    @Override
    public void onMemberList(List<MemberBean> beanList) {
        refreshLayout.finishRefresh(true);
        mBeanList.clear();
        mBeanList.addAll(beanList);
        adapter.setNewData(mBeanList);
//        initViewDate();

    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onItemClick(View v, MemberBean bean) {
        MemeberListActivity.start(mActivity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 内容自适应高度
     */
    public static void showBuyDialog(Activity activity, onItemClickListen10 onClick) {
        AlertDialog mAlertDialog;
        //弹出Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.EtDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(true);
        mAlertDialog.show();
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialo_financia_input_141, null);
        Button yes = dialogView.findViewById(R.id.bt_yes);
        Button no = dialogView.findViewById(R.id.bt_no);
        EditText money = dialogView.findViewById(R.id.et_money);

        yes.setOnClickListener(v -> {
            String moneyLos = money.getText().toString();
            if (!moneyLos.isEmpty()) {
                int memberId = Integer.parseInt(moneyLos);
                onClick.onItemClick(v, memberId);
                mAlertDialog.dismiss();
            }

        });
        no.setOnClickListener(v -> {
            mAlertDialog.dismiss();
        });

        mAlertDialog.setContentView(dialogView);
        Window mDialogWindow = mAlertDialog.getWindow();
        mDialogWindow.setGravity(Gravity.CENTER);
        mDialogWindow.setBackgroundDrawableResource(R.color.color_transparent);
        mDialogWindow.setWindowAnimations(R.style.BottomAnimation);

        mDialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mDialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }

    @OnClick({R.id.circle_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.circle_button:
                if (ConfigDate.sConfigBean != null) {
                    ShareUtils.addGroup(mActivity, ConfigDate.sConfigBean.getTelegram());
                }
                break;
        }
    }
}
