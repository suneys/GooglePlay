package com.yoyo.googleplay.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 自定义Application
 * Created by Administrator on 2016/5/14 0014.
 */
public class BaseApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        //1、获取context
        context = getApplicationContext();

        //2、创建Handle
        handler = new Handler();

        //3、获取主线程ID
        mainThreadId = android.os.Process.myTid();

    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }


}
