package com.mobielwa.diki;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.mobielwa.diki.activity.hkk.MainActivity;
import com.mobielwa.diki.bean.dao.DaoMaster;
import com.mobielwa.diki.bean.dao.DaoSession;
import com.mobielwa.diki.config.AppConfig;
import com.mobielwa.diki.utlis.languagelib.MultiLanguageUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.wanjian.cockroach.Cockroach;

import androidx.multidex.MultiDex;

/**
 * @Author: June
 * @CreateDate: 12/7/20 2:45 PM
 * @Description: java类作用描述
 */
public class APP extends Application {
    public static DaoSession mSession;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        AppConfig.INSTANCE.init(this);
        MultiLanguageUtil.init(this);
        CrashReport.initCrashReport(getApplicationContext(), "1a1ebc12e9", BuildConfig.DEBUG);

        initDb();

    }

    /**
     * 连接数据库并创建会话
     */
    public void initDb() {
        // 1、获取需要连接的数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "messg.db");
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        // 2、创建数据库连接
        DaoMaster daoMaster = new DaoMaster(db);
        // 3、创建数据库会话
        mSession = daoMaster.newSession();
    }

    // 供外接使用
    public  static DaoSession getDaoSession() {
        return mSession;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        if (!BuildConfig.DEBUG) {
            initCockroach();
        }
    }

    private void initCockroach() {
        final Thread.UncaughtExceptionHandler sysExcepHandler = Thread.getDefaultUncaughtExceptionHandler();
        Cockroach.install(new Cockroach.ExceptionHandler() {

            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException

            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                //开发时使用Cockroach可能不容易发现bug，所以建议开发阶段在handlerException中用Toast谈个提示框，
                //由于handlerException可能运行在非ui线程中，Toast又需要在主线程，所以new了一个new Handler(Looper.getMainLooper())，
                //所以千万不要在下面的run方法中执行耗时操作，因为run已经运行在了ui线程中。
                //new Handler(Looper.getMainLooper())只是为了能弹出个toast，并无其他用途
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Intent intent;
                            intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            getApplicationContext().startActivity(intent);
                            Process.killProcess(Process.myPid());
                            System.exit(0);
                            //建议使用下面方式在控制台打印异常，这样就可以在Error级别看到红色log
                            Log.e("AndroidRuntime","--->CockroachException:"+thread+"<---",throwable);
//                            Toast.makeText(getApplicationContext(), "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();
//                        throw new RuntimeException("..."+(i++));
                        } catch (Throwable e) {

                        }
                    }
                });
            }
        });
    }

}
