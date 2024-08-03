package com.base.kiap.activity.hkk;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.base.kiap.R;
import com.base.kiap.base.BaseActivity;
import com.base.kiap.config.UserHelp;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: June
 * @CreateDate: 12/21/20 3:39 PM
 * @Description: 下载地址
 */
public class DownLoadActivity extends BaseActivity {

  @BindView(R.id.tv_phone)
  TextView tvPhone;
  @BindView(R.id.tv_download)
  TextView tvDownload;
  @BindView(R.id.tv_call)
  TextView tvCall;


  public static void start(Context context) {
    Intent starter = new Intent(context, DownLoadActivity.class);
    context.startActivity(starter);
  }

  @Override
  protected int attachLayoutRes() {
    return R.layout.act_meinfo;
  }

  @Override
  protected void initData() {
    tvPhone.setText(UserHelp.getPhone() + "");
  }


  @OnClick({R.id.tv_download, R.id.tv_call})
  public void onViewClicked(View view) {
      if (mClickHelper.click()) {
          return;
      }
    switch (view.getId()) {
      case R.id.tv_download:
        break;
      case R.id.tv_call:
        break;
    }
  }
}
