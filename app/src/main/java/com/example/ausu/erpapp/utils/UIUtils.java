package com.example.ausu.erpapp.utils;


import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;


import com.example.ausu.erpapp.MyApplication;

import java.math.BigDecimal;

/**
 * create 2015.8.13
 * hzh
 * 工具类,
 */

public class UIUtils {
    public static Context getContext() {
        return MyApplication.applicationContext.getApplicationContext();
    }


    /**
     * dip转换px
     */
    public static int dip2px(int dip) {

        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }


    public static void runInMainThread(Runnable runnable) {
        //  AndroidUtilities.runOnUIThread(runnable);
    }

    public static boolean isExitsSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    public static double getDouvleValue(String value) {
        double doubleValue = Double.parseDouble(value);
        BigDecimal b = new BigDecimal(doubleValue);
        double modifiedValue = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return modifiedValue;
    }

    public static int getScreenHeight() {
        WindowManager systemService = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        return systemService.getDefaultDisplay().getHeight();
    }

    public static int getScreenWidth() {
        WindowManager systemService = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        return systemService.getDefaultDisplay().getWidth();
    }





}

