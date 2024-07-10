package com.mobielwa.diki.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.mobielwa.diki.R;
import com.mobielwa.diki.base.BaseMvpActivity;
import com.mobielwa.diki.bean.UsdtBean;
import com.mobielwa.diki.bean.UsdtOrderBean;
import com.mobielwa.diki.bean.request.RechargeRequestBean;
import com.mobielwa.diki.config.ConfigDate;
import com.mobielwa.diki.config.UserHelp;
import com.mobielwa.diki.mvp.iview.IRechargeUsdtView;
import com.mobielwa.diki.mvp.presenter.UsdtRechargePresenter;
import com.mobielwa.diki.utlis.CommUtils;
import com.mobielwa.diki.utlis.ToastUtil;
import com.mobielwa.diki.utlis.imagu.PhotoUtils;
import com.mobielwa.diki.utlis.permission.OnRequestPermissionListener;
import com.mobielwa.diki.utlis.permission.PermissionRequestHelper;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @Author: June
 * @CreateDate: 1/26/21 10:46 AM
 * @Description: java类作用描述
 */
public class RechargeUsdtActivity extends BaseMvpActivity<IRechargeUsdtView, UsdtRechargePresenter> implements IRechargeUsdtView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.tv_usdt_address)
    TextView tvUsdtAddress;
    @BindView(R.id.usdt_copy_address)
    TextView usdtCopyAddress;
    @BindView(R.id.tv_usdt_money)
    TextView tvUsdtMoney;
    @BindView(R.id.recharge_times)
    TextView rechargeTimes;

    private CountDownTimer timer;

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_recharge_usdt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RechargeUsdtActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.str_usdt_title);
        initImmersionBar();
        getPresenter().findUsdtAddress();
    }

    @Override
    protected UsdtRechargePresenter createPresenter() {
        return new UsdtRechargePresenter();
    }

    @OnClick({R.id.iv_back, R.id.usdt_copy_address,R.id.usdt_copy_address1, R.id.bt_save_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.usdt_copy_address:
                CommUtils.copy(tvUsdtAddress.getText().toString());
                ToastUtil.success(getString(R.string.str_copy_success));
                break;
            case R.id.usdt_copy_address1:
                CommUtils.copy(tvUsdtMoney.getText().toString());
                ToastUtil.success(getString(R.string.str_copy_success));
                break;
            case R.id.bt_save_pic:
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == Activity.RESULT_OK) {
//                if (requestCode == ALBUM) {
//                    Glide.with(this).load(data.getData()).into(rlPicBg);
//                    onUploadImg(data.getData());
//                }
            }
        }
    }

    @Override
    public void onHideDialog() {
        hideLoading();
    }

    @Override
    public void onSuccess(UsdtBean bean) {
        tvUsdtAddress.setText(bean.getUsdtAddress());
        MultiTransformation<Bitmap> mt= new MultiTransformation<>(new RoundedCornersTransformation(0, 0));
        RequestOptions rq = new RequestOptions().bitmapTransform(mt);
        Glide.with(this).load(bean.getQrcode()).apply(rq).into(ivQrcode);

        timer = new CountDownTimer(bean.getTimes()*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                rechargeTimes.setText(second2Time(millisUntilFinished/1000));
            }
            public void onFinish() {
                rechargeTimes.setText("00:00");
            }

            public String second2Time(Long second) {
                if (second == null || second < 0) {
                    return "00:00";
                }

                long m = (second % 3600) / 60;
                long s = second % 60;
                String str = "";
                str += (m < 10 ? ("0" + m) : m) + ":";
                str += (s < 10 ? ("0" + s) : s);
                return str;

            }
        };
        timer.start();
    }

    @Override
    public void onFail() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
        }
    }
}
