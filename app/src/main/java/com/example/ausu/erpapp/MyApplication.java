package com.example.ausu.erpapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by AUSU on 2016/5/16.
 */
public class MyApplication extends Application {
    public static volatile Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this.getApplicationContext();
    }
}
