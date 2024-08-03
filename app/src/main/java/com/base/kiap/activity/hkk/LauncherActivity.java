package com.base.kiap.activity.hkk;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.base.kiap.activity.basea.MainActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.base.kiap.R;

/**
 * @Author: June
 * @CreateDate: 12/7/20 2:22 PM
 * @Description: 启动页
 */
public class LauncherActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    private Handler handler;
    private Runnable runnable;
    private RelativeLayout rlSpan;
    private boolean isShowAd = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setTheme(R.style.Appwelcome);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_launcher);
        rlSpan=findViewById(R.id.rl_splan);
        init();
    }

    private void init() {
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                if (!isShowAd) {
                    Bundle bundle = new Bundle();
                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "启动");
                    bundle.putString(FirebaseAnalytics.Param.CONTENT, "启动APP");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                    MainActivity.start(LauncherActivity.this);
                    finish();
                }
            }
        }, 1500);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

}
