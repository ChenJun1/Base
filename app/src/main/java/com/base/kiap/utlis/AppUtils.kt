package com.base.kiap.utlis;
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import androidx.core.content.FileProvider
import com.base.kiap.R
import com.base.kiap.config.AppConfig
import java.io.File
import java.util.*

/**
 * Created by chenxz on 2018/4/21.
 */
class AppUtils private constructor() {
    init {
        throw Error("Do not need instantiate!")
    }

    companion object {

        private val DEBUG = true
        private val TAG = "AppUtils"


        /**
         * 得到软件版本号
         *
         * @param context 上下文
         * @return 当前版本Code
         */
        fun getVerCode(context: Context): Int {
            var verCode = -1
            try {
                val packageName = context.packageName
                verCode = context.packageManager
                    .getPackageInfo(packageName, 0).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verCode
        }


        /**
         * 获取应用运行的最大内存
         *
         * @return 最大内存
         */
        val maxMemory: Long
            get() = Runtime.getRuntime().maxMemory() / 1024

        /**
         * 得到软件显示版本信息
         *
         * @param context 上下文
         * @return 当前版本信息
         */
        fun getVerName(context: Context): String {
            var verName = ""
            try {
                val packageName = context.packageName
                verName = context.packageManager
                    .getPackageInfo(packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return verName
        }

        /**
         * 获取包名
         */
        fun packageName(): String? {
            val manager = AppConfig.getApplication().packageManager
            var name: String? = ""
            try {
                val info = manager.getPackageInfo(AppConfig.getApplication().packageName, 0)
                name = info.packageName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return name
        }


        /**
         * 获取手机系统SDK版本
         *
         * @return 如API 17 则返回 17
         */
        val sdkVersion: Int
            get() = android.os.Build.VERSION.SDK_INT


        /**
         * 获取渠道类型
         */
        fun getChannel(): String? {
            var value = ""
            try {
                val appInfo: ApplicationInfo = AppConfig.getApplication().getPackageManager().getApplicationInfo(AppConfig.getApplication().getPackageName(),
                    PackageManager.GET_META_DATA)
                value = appInfo.metaData.getString("UMENG_CHANNEL")
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return value
        }

        /**
         * 获取appName
         */
        fun getAppName(): String? {
            return AppConfig.getApplication().getString(R.string.app_name)
        }


        /*
         * 得到全局唯一UUID
         */
        fun getUUID(): String? {
            val uuid: String
            val savedUUID = SPUtils.get("UUID_SAVE", "") as String
            if (TextUtils.isEmpty(savedUUID)) {
                val id = UUID.randomUUID()
                val idd = id.toString().split("-".toRegex()).toTypedArray()
                uuid = idd[0] + idd[1] + idd[4]
                SPUtils.put("UUID_SAVE", uuid)
            } else {
                uuid = savedUUID
            }
            return uuid
        }


        /**
         * 安装Apk
         */
        fun installApk(file: File?) {
            if (file == null) {
                return
            }

            val intent = Intent(Intent.ACTION_VIEW)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val contentUri = FileProvider.getUriForFile(AppConfig.getApplication(), packageName() + ".fileprovider", file)
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            } else {
                intent.setDataAndType(Uri.parse("file://$file"), "application/vnd.android.package-archive")
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            AppConfig.getApplication().startActivity(intent)
        }

        fun MD5Sign(str: String?): String? {
            return try {
                MD5.md5(str)
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }
    }



}