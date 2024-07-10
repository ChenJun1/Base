package com.mobielwa.diki.utlis;

/**
 * @Author: June
 * @CreateDate: 12/31/20 4:04 PM
 * @Description: java类作用描述
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author : Ziwen Lan
 * Date : 2019/10/23
 * Time : 15:11
 * Introduction : 截屏工具类
 */
public class ScreenshotUtil {

    /**
     * 截取指定activity显示内容
     * 需要读写权限
     */
    public static void saveScreenshotFromActivity(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        saveImageToGallery(bitmap, activity);
        //回收资源
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
    }

    /**
     * 截取指定View显示内容
     * 需要读写权限
     */
    public static void saveScreenshotFromView(View view, Activity context) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        saveImageToGallery(bitmap, context);
        //回收资源
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
    }

    /**
     * 保存图片至相册
     * 需要读写权限
     */
    public static String saveImageToGallery(Bitmap bmp, Activity context) {
        File appDir = new File(getDCIM());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + getDCIM())));
        return file.getAbsolutePath();
    }

    /**
     * 获取相册路径
     */
    private static String getDCIM() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return "";
        }
        String path = Environment.getExternalStorageDirectory().getPath() + "/dcim/";
        if (new File(path).exists()) {
            return path;
        }
        path = Environment.getExternalStorageDirectory().getPath() + "/DCIM/";
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return "";
            }
        }
        return path;
    }

    public static Uri getImageContentUri(Context context, String path) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID }, MediaStore.Images.Media.DATA + "=? ",
                new String[] { path }, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            // 如果图片不在手机的共享图片数据库，就先把它插入。
            if (new File(path).exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, path);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
}
