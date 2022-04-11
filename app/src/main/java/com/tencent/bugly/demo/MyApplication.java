package com.tencent.bugly.demo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by yue on 2022/4/1.
 * 使用流量测试，WIFI访问异常
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /* Bugly SDK初始化
         * 参数1：上下文对象
         * 参数2：APP ID，平台注册时得到,注意替换成你的appId
         * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
         */
        CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(this);
        CrashReport.initCrashReport(this, "ed8d153690", BuildConfig.DEBUG);
    }
}
