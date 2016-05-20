package com.yoyo.googleplay.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.yoyo.googleplay.global.BaseApplication;

/**
 * Created by Administrator on 2016/5/14 0014.
 */
public class UIUtils {

    ///////////////////BaseApplication相关方法//////////////////////

    //获取context
    public static Context getContext() {
        return BaseApplication.getContext();
    }

    //获取handler
    public static Handler getHandler() {
        return BaseApplication.getHandler();
    }

    //获取主线程ID
    public static int getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    ///////////////////加载资源文件//////////////////////

    //加载字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    //加载字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    //加载图片
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    //加载颜色
    public static int geColor(int id) {
        return getContext().getResources().getColor(id);
    }

    //加载颜色状态选择器
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }
    //加载尺寸
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);//获取对应像素值
    }

    ///////////////////dip转px,px转dp//////////////////////
    //dp = px / 设备密度
    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;//设备密度
        int px = (int) (dip * density + 0.5f); //+0.5f可以进行四舍五入
        return px;
    }

    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;//设备密度
        return px / density;
    }

    ///////////////////通过布局文件加载布局//////////////////////
    public static View inflate(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    ///////////////////判断当前是否主线程运行//////////////////////
    public static boolean isRunOnUiThread(){
        //获取当前线程ID,如果当前线程id等于主线程ID，那就说明当前是在主线程
        return android.os.Process.myTid() == getMainThreadId();
    }

    //运行在主线程
    public static void runOnUiThread(Runnable r){
        //判断当前是否是主线程，如果是，就直接运行
        if(isRunOnUiThread()){
            //当前就是主线程
            r.run();
        }else{
            //不是主线程，需要运行在主线程
            getHandler().post(r);
        }
    }


}
