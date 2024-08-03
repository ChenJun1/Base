package com.base.kiap.utlis;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;

import com.base.kiap.config.AppConfig;
import com.base.kiap.config.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: June
 * @CreateDate: 12/24/20 2:35 PM
 * @Description: java类作用描述
 */
public class CommUtils {
    //复制
    public static void copy(String data) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) AppConfig.INSTANCE.getApplication().getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）,其他的还有
        // newHtmlText、
        // newIntent、
        // newUri、
        // newRawUri
        ClipData clipData = ClipData.newPlainText(null, data);

        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);

    }

    public static void ToWeb(String url, Context context) {
        if (url == null || url.equals("")) {
            return;
        }
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static int getRandomInt() {
        return (int) (Math.random() * 10 + 1);
    }


    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 获取当前app version code
     */
    public static long getAppVersionCode(Context context) {
        long appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode = packageInfo.getLongVersionCode();
            } else {
                appVersionCode = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
//            Log.e("", e.getMessage());
        }
        return appVersionCode;
    }


    public static int getSignature(String packageName){
        PackageManager pm=AppConfig.INSTANCE.getApplication().getPackageManager();
        PackageInfo packageInfo=null;
        int sig=0;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            android.content.pm.Signature[] signatures = packageInfo.signatures;
            sig=signatures[0].hashCode();
        } catch (PackageManager.NameNotFoundException e) {
            sig=0;
            e.printStackTrace();
        }
        return  sig;
    }

    public static int getSignature() {
        return getSignature(AppUtils.Companion.packageName());
    }

    public static Signature[] getRawSignature(String paramString) {
        if ((paramString == null) || (paramString.length() == 0)) {
            NLog.INSTANCE.e("获取签名失败，包名为 null");
            return null;
        }
        PackageManager localPackageManager = AppConfig.INSTANCE.getApplication().getPackageManager();
        PackageInfo localPackageInfo;
        try {
            localPackageInfo = localPackageManager.getPackageInfo(paramString, PackageManager.GET_SIGNATURES);
            if (localPackageInfo == null) {
                NLog.INSTANCE.e("信息为 null, 包名 = " + paramString);
                return null;
            }
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            NLog.INSTANCE.e("包名没有找到...");
            return null;
        }
        return localPackageInfo.signatures;
    }


    /**
     * 正则表达式 判断邮箱格式是否正确
     */

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 指定电话联系what app
     * @param mContext
     * @param mobileNum
     */
    public static void chatInWhatsApp(Context mContext, String mobileNum) {
        try {
            final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobileNum));
            intent.setPackage(Constants.WhatsAppPackageName);
            mContext.startActivity(intent);
        } catch (Exception e) { //  没有安装WhatsApp

            e.printStackTrace();
        }
    }


}
