package com.mobielwa.diki.utlis;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import com.mobielwa.diki.config.AppConfig;

import java.util.List;

/**
 * @Author: June
 * @CreateDate: 1/31/21 11:42 AM
 * @Description: java类作用描述
 */
public class ApkCommUtils {

    public boolean checkApkExist(Context context, Intent intent) {
        List list = context.getPackageManager()
                .queryIntentActivities(intent, 0);

        if (list.size() > 0) {
            return true;

        }

        return false;

    }

    public static boolean checkApkExist(String packageName) {
        if (packageName == null || "".equals(packageName)) return false;

        try {
            ApplicationInfo info =  AppConfig.INSTANCE.getApplication().getPackageManager() .getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES); return true;

        } catch (PackageManager.NameNotFoundException e) { return false; }

    }

    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    public static boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = AppConfig.INSTANCE.getApplication().getPackageManager().getPackageInfo(packname, 0);
        } catch (Exception e) {
        }
        return packageInfo != null;
    }

    /**
     * 判断应用是否在后台运行并直接打开
     *
     * @param context
     * @param packageName
     * @return
     */
    public static Intent getAppOpenIntentByPackageName(Context context, String packageName) {
        //Activity完整名
        String mainAct = null;
        //根据包名寻找
        PackageManager pkgMag = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);

        @SuppressLint("WrongConstant") List<ResolveInfo> list = pkgMag.queryIntentActivities(intent,
                PackageManager.GET_ACTIVITIES);
        for (int i = 0; i < list.size(); i++) {
            ResolveInfo info = list.get(i);
            if (info.activityInfo.packageName.equals(packageName)) {
                mainAct = info.activityInfo.name;
                break;
            }
        }
        if (TextUtils.isEmpty(mainAct)) {
            return null;
        }
        intent.setComponent(new ComponentName(packageName, mainAct));
        return intent;
    }

    public static Context getPackageContext(Context context, String packageName) {
        Context pkgContext = null;
        if (context.getPackageName().equals(packageName)) {
            pkgContext = context;
        } else {
            // 创建第三方应用的上下文环境
            try {
                pkgContext = context.createPackageContext(packageName,
                        Context.CONTEXT_IGNORE_SECURITY
                                | Context.CONTEXT_INCLUDE_CODE);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return pkgContext;
    }

    public static boolean openPackage(Context context, String packageName) {
        Context pkgContext = getPackageContext(context, packageName);
        Intent intent = getAppOpenIntentByPackageName(context, packageName);
        if (pkgContext != null && intent != null) {
            pkgContext.startActivity(intent);
            return true;
        }
        return false;
    }
}
