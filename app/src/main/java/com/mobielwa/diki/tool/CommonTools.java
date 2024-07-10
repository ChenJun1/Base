package com.mobielwa.diki.tool;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.mobielwa.diki.config.AppConfig;


/**
 * @Author: June
 * @CreateDate: 12/7/20 3:19 PM
 * @Description: java类作用描述
 */
public class CommonTools {
    /**
     * 获取渠道类型
     */
    public static String getChannel() {

        String value = "";
        try {

            ApplicationInfo appInfo = AppConfig.INSTANCE.getApplication().getPackageManager().getApplicationInfo(
                    AppConfig.INSTANCE.getApplication().getPackageName(),
                    PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
}
