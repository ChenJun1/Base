package com.mobielwa.diki.config;

import android.app.Application
import com.mobielwa.diki.BuildConfig
import com.mobielwa.diki.utlis.SPUtils

/**
 * @author
 * @date
 * @desc 用来初始化项目所需要的配置
 */
object AppConfig {

    const val TAG = "Kotlin"
    var debug = BuildConfig.DEBUG

    private var application: Application? = null

    /**
     * Init, it must be call before used .
     */
    fun init(application: Application) {
        this.application = application
        SPUtils.init(application)
    }

    fun getApplication(): Application {
        if (application == null) {
            throw RuntimeException("Please init in Application#onCreate first.")
        }
        return application!!
    }

    fun openDebug() {
        debug = true
    }

}