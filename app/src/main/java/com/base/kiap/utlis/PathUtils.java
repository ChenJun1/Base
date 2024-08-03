/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.base.kiap.utlis;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.os.StatFs;

import com.base.kiap.config.AppConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by
 */
public class PathUtils {
    private static final String TAG = "Tinker.Utils";

    /**
     * the error code define by myself
     * should after {@code ShareConstants.ERROR_PATCH_INSERVICE
     */
    public static final int ERROR_PATCH_GOOGLEPLAY_CHANNEL      = -20;
    public static final int ERROR_PATCH_ROM_SPACE               = -21;
    public static final int ERROR_PATCH_MEMORY_LIMIT            = -22;
    public static final int ERROR_PATCH_CRASH_LIMIT             = -23;
    public static final int ERROR_PATCH_CONDITION_NOT_SATISFIED = -24;

    public static final String PLATFORM = "platform";

    public static final int MIN_MEMORY_HEAP_SIZE = 45;

    private static boolean background = false;

    public static boolean isGooglePlay() {
        return false;
    }

    public static boolean isBackground() {
        return background;
    }

    public static void setBackground(boolean back) {
        background = back;
    }


    public static boolean isXposedExists(Throwable thr) {
        StackTraceElement[] stackTraces = thr.getStackTrace();
        for (StackTraceElement stackTrace : stackTraces) {
            final String clazzName = stackTrace.getClassName();
            if (clazzName != null && clazzName.contains("de.robv.android.xposed.XposedBridge")) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean checkRomSpaceEnough(long limitSize) {
        long allSize;
        long availableSize = 0;
        try {
            File data = Environment.getDataDirectory();
            StatFs sf = new StatFs(data.getPath());
            availableSize = (long) sf.getAvailableBlocks() * (long) sf.getBlockSize();
            allSize = (long) sf.getBlockCount() * (long) sf.getBlockSize();
        } catch (Exception e) {
            allSize = 0;
        }

        if (allSize != 0 && availableSize > limitSize) {
            return true;
        }
        return false;
    }
    /**
     * 获取外部缓存目录中的自定义目录
     *
     * @param context 上下文
     * @param path    自定义目录
     * @return File
     */
    public static File getCache(Context context, String path) {
        File file = null;
        try {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                file = new File(context.getExternalCacheDir().getPath() + "/"
                        + path);
            } else {
                file = new File(context.getCacheDir() + "/" + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file == null)
            return null;
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return null;
            }
        }
        return file;
    }

    public static String getRootPath(){
        String path ="";
        try {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                path = Environment.getExternalStorageDirectory() + "/download/";
            } else {
               path = AppConfig.INSTANCE.getApplication().getCacheDir() + "/download/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getExceptionCauseString(final Throwable ex) {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final PrintStream ps = new PrintStream(bos);

        try {
            // print directly
            Throwable t = ex;
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace(ps);
            return toVisualString(bos.toString());
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String toVisualString(String src) {
        boolean cutFlg = false;

        if (null == src) {
            return null;
        }

        char[] chr = src.toCharArray();
        if (null == chr) {
            return null;
        }

        int i = 0;
        for (; i < chr.length; i++) {
            if (chr[i] > 127) {
                chr[i] = 0;
                cutFlg = true;
                break;
            }
        }

        if (cutFlg) {
            return new String(chr, 0, i);
        } else {
            return src;
        }
    }

    /**
     * 判断是否是Debug
     *
     * @param context
     * @return
     */
    public static boolean isDebug(Context context) {
        return context.getApplicationInfo() != null &&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * 随机生成验证码
     * @return
     */
    public static String onRandomCode() {
        String strRand = "";
        for (int i = 0; i < 4; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        return strRand;
    }

    /**
     * 随机生成用户id
     * @return
     */
    public static StringBuilder onRandomUid() {
        Random r = new Random();
        StringBuilder strRand = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int ix= r.nextInt(10 - 7) + 7;
            strRand.append(ix);
        }
        strRand.append("*****");
        for (int i = 0; i < 3; i++) {
            int ix= r.nextInt(9 - 1) + 1;
            strRand.append(ix);
        }
        return strRand;
    }

    /**
     * 随机生成广播奖金
     * @return
     */
    public static StringBuilder onRandomBonus() {
        StringBuilder strRand=new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int temp = 1 + (int) (Math.random() * (9 + 1 - 1));
            strRand.append(String.valueOf(temp));
        }
        strRand.append("0");
        return strRand;
    }

    /**
     * 生成广播消息
     * @return
     */
    public static List<String> getTgList() {
        List<String> list = new ArrayList<>();
        StringBuilder stringBuilder;

        for (int i = 0; i < 50; i++) {
            stringBuilder=new StringBuilder();
            stringBuilder.append("NO. ");
            stringBuilder.append(onRandomUid());
            stringBuilder.append(" just cashed it out ");
            stringBuilder.append("Rs "+onRandomBonus() + ""+" ");
            list.add("Rs "+stringBuilder.toString() + "");
        }
        return list;
    }


}
