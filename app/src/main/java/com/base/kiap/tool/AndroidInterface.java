package com.base.kiap.tool;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;

import com.base.kiap.config.UserHelp;

/**
 * @Author: June
 * @CreateDate: 3/2/21 3:16 PM
 * @Description: java类作用描述
 */
public class AndroidInterface {
    private Context mActivity;
    public AndroidInterface(Activity activity) {
        this.mActivity = activity;
    }

    @JavascriptInterface//获取用户id
    public String getUserId() {
        return UserHelp.getUserId()+"";
    }

}
