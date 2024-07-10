package com.mobielwa.diki.utlis.imagu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * author : seven
 * e-mail : clx2216049367@163.com
 * date   : 2019/7/911:57 AM
 * desc   :压缩图片工具
 */
public class PhotoUtils {
    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */
    public static File getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了，只读取图片的大小，不分配内存
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是1920*1080分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为1920f
        float ww = 800f;// 这里设置宽度为1080f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww && w > hh) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        //检测并旋转图片
        Bitmap bitmap1 = changeImageLocate(srcPath, bitmap);
        return compressImage(bitmap1);// 压缩好比例大小后再进行质量压缩
    }


    public static File getimagedr(String srcPath) {
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, reduceBitmap(1080, 1920, srcPath));
        //检测并旋转图片
        Bitmap bitmap1 = changeImageLocate(srcPath, bitmap);
        return compressImage(bitmap1);// 压缩好比例大小后再进行质量压缩
    }

    /**
     * Compressed pictures
     *
     * @param newW Width
     * @param newH Height
     * @param path File path
     * @return
     */
    public static BitmapFactory.Options reduceBitmap(int newW, int newH, String path) {
        if (path == null || path.length() == 0) {
            return null;
        }
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, op);
        int oldW = op.outWidth;
        int oldH = op.outHeight;
        int size = 1;
        if (oldW > oldH) {// W > H(Horizontal plate camera)
            if (newW > newH) {
                if (oldH > newH) {
                    size = oldH / newH;
                }
            } else {
                if (oldH > newW) {
                    size = oldH / newW;
                }
            }
        } else {// H > W(Vertical camera)
            if (newH > newW) {
                if (oldW > newW) {
                    size = oldW / newW;
                }
            } else {
                if (oldW > newH) {
                    size = oldW / newH;
                }
            }
        }
        BitmapFactory.Options op1 = new BitmapFactory.Options();
        op1.inJustDecodeBounds = false;
        op1.inSampleSize = size;
        return op1;
    }

    //检测并旋转图片
    public static Bitmap changeImageLocate(String filepath, Bitmap bitmap) {
        //根据图片的filepath获取到一个ExifInterface的对象
        int degree = 0;
        try {
            ExifInterface exif = new ExifInterface(filepath);
            if (exif != null) {
                // 读取图片中相机方向信息
                int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                // 计算旋转角度
                switch (ori) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                    default:
                        degree = 0;
                        break;
                }
                if (degree != 0) {
                    // 旋转图片
                    Matrix m = new Matrix();
//                    m.setScale(0.5f,0.5f);
                    m.postRotate(degree);

                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);

                    return bitmap;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //质量压缩
    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 1024 && options>0) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        File dirFile = new File(Environment.getExternalStorageDirectory() + "/jumpimg");
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        File file = new File(dirFile.getPath(), System.currentTimeMillis() + ".jpeg");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        recycleBitmap(bitmap);
        return file;
    }

    //回收Bitmap
    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps == null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDir() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/jumpimg");
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

}
