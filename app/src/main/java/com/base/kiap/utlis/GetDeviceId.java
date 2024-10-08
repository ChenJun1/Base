package com.base.kiap.utlis;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author: June
 * @CreateDate: 2020-04-20 15:48
 * @Description: java类作用描述
 */
public class GetDeviceId {
    public static String getANDROID_ID(Context context) {
        String ANDROID_ID = "";
        try {
            ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
            if (TextUtils.isEmpty(ANDROID_ID)) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }

        return ANDROID_ID;
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public static final String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                String imei = telephonyManager.getDeviceId();
                //在次做个验证，也不是什么时候都能获取到的啊
                if (imei == null) {
                    imei = "";
                }
                return imei;
            }else {
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }



    public static String getSerialNumber(){
        String SerialNumber = "";

        try {
            SerialNumber = Build.SERIAL;

            if(TextUtils.isEmpty(SerialNumber)){
                return "";
            }
        }catch (Exception e){
            return "";
        }

        return  SerialNumber;
    }

    public static String getUniquePsuedoID(){
        try {
            String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);


            String serial = null;
            try
            {
                serial = Build.class.getField("SERIAL").get(null).toString();

                return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
            }
            catch (Exception e)
            {
                serial = "serial";
            }

            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        }catch (Exception e){
            return "";
        }
    }


    public static String getOnlyID(Context context){
        String onlyId = getANDROID_ID(context)+getSerialNumber()+getUniquePsuedoID()+getIMEI(context);

        return getMD5Str(onlyId);
    }

    private static String getMD5Str(String str)
    {
        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e)
        {
//            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++)
        {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }


    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }


    public static String getDeviceBrand(){
        String brand = Build.BRAND;
        return brand;
    }

    public static String getSystemModel(){
        String model = Build.MODEL;
        return model;
    }

    public static String getAppVersion(Context context){
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return packInfo.versionName;
    }

    public static String getDevice(Context context){
        return getOnlyID(context);
//        String dtr="app版本："+getAppVersion(context)+"n"+"手机品牌："+
//                getDeviceBrand()+"n"+"手机型号："+getSystemModel()+"n"+"Android系统版本号："+
//                getSystemVersion()+"n"+"onlyID："+getOnlyID(context)+"n"
//                +"ANDROID_ID："+getANDROID_ID(context)+"n"
//                +"SerialNumber："+getSerialNumber()+"n"
//                +"UniquePsuedoID："+getUniquePsuedoID()+"n";
//      String str=  getSystemModel() +
//                getSystemVersion() + getOnlyID(context);
//        String serial = null;
//        try
//        {
//            serial = Build.class.getField("SERIAL").get(null).toString();
//
//            return new UUID(str.hashCode(), serial.hashCode()).toString();
//        }
//        catch (Exception e)
//        {
//            serial = "serial";
//        }
//
//        return new UUID(str.hashCode(), serial.hashCode()).toString();
    }

    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (context == null) {
            return result;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Throwable e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

}
