package com.base.kiap.utlis;


import com.bumptech.glide.Glide;
import com.base.kiap.config.AppConfig;

import androidx.recyclerview.widget.RecyclerView;

/**
 * author : seven
 * e-mail : clx2216049367@163.com
 * date   : 2020/4/1710:37 AM
 * desc   :
 */
public class RecyclerViewLoadUtil {

    public static void rvLoad(RecyclerView recyclerView){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(AppConfig.INSTANCE.getApplication()).resumeRequests();//恢复Glide加载图片
                } else {
                    Glide.with(AppConfig.INSTANCE.getApplication()).pauseRequests();//禁止Glide加载图片
                }
            }
        });
    }

}
