package com.sign.pushdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.os.Process;


import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by cys on 2018/7/16 0016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //其它机型
        if (SystemJudgeUtil.getSystem() == SystemJudgeUtil.SYS_OTHER) {
            Log.i("test", "**************************System OHTER********************************");
            //极光
            JPushInterface.setDebugMode(true);
            JPushInterface.init(this);
        } else if (SystemJudgeUtil.getSystem() == SystemJudgeUtil.SYS_MIUI) {
            Log.i("test", "**************************System MIUI********************************");
            //小米
            //初始化push推送服务
            /**
             * 经测试，Redmi 3S可以应用进程杀死后，仍可以收到推送
             *       MI 4LTE 收不到推送 TODO
             *       其它机型进程存活时可以收到，进程被杀死后收不到推送
             */
            if (shouldInit()) {
                Log.i("test", "**************************MIUI register********************************");
                MiPushClient.registerPush(this, "2882303761517839041", "5511783961041");
            }
            LoggerInterface newLogger = new LoggerInterface() {
                @Override
                public void setTag(String tag) {
                    // ignore
                }

                @Override
                public void log(String content, Throwable t) {
                    Log.d("test", content, t);
                }

                @Override
                public void log(String content) {
                    Log.d("test", content);
                }
            };
            Logger.setLogger(this, newLogger);
        } else if (MzSystemUtils.isBrandMeizu()) {
            //魅族推送只适用于Flyme系统,因此可以先行判断是否为魅族机型，再进行订阅，避免在其他机型上出现兼容性问题
            PushManager.register(this, "114724", "c88cf5b574c84565a44856169cad3568");
        }
    }


    /**
     * TODO
     *
     * @return
     */
    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

}
