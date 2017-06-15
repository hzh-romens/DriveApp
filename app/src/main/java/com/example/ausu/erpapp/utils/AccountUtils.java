package com.example.ausu.erpapp.utils;

import android.content.SharedPreferences;

import com.example.ausu.erpapp.MyApplication;
import com.example.ausu.erpapp.model.UserBean;

/**
 * Created by Lanxumit on 2016/8/20.
 * 用户信息管理类
 */
public class AccountUtils {
    private final static String PREFERENCE_NAME = "user_cache";
    private final static String PREFERENCE_KEY_USER = "user";

    private static AccountUtils accountInsatnce = null;
    public static Object sync = new Object();


    public static synchronized AccountUtils instance() {
        if (accountInsatnce == null) {
            synchronized (AccountUtils.class) {
                if (accountInsatnce == null) {
                    accountInsatnce = new AccountUtils();
                }
            }
        }
        return accountInsatnce;
    }

    public boolean saveAccount(UserBean userData) {
        synchronized (sync) {
            SharedPreferences.Editor edit = user.edit();
            edit.putString("userName", userData.getUserName());
            edit.putInt("userId", userData.getUserId());
            edit.putString("mobile", userData.getMobile());
            edit.putString("suid", userData.getSuid());
            edit.putInt("userType", userData.getUserType());
            edit.putInt("gender", userData.getGender());
            return edit.commit();
        }

    }

    public void cleanAccount() {
        SharedPreferences.Editor edit = user.edit();
        edit.remove("userName");
        edit.remove("userId");
        edit.remove("suid");
        edit.remove("userType");
        edit.remove("gender");
        edit.commit();
    }

    SharedPreferences user = MyApplication.applicationContext.getSharedPreferences("user", MyApplication.applicationContext.MODE_PRIVATE);

    public void saveUuid(String key, String value) {
        SharedPreferences.Editor edit = user.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public String getUuid(String key) {
        String string = user.getString(key, "");
        return string;
    }

    public void saveStatus(String key, boolean value) {
        SharedPreferences.Editor edit = user.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public boolean getStatus(String key) {
        boolean aBoolean = user.getBoolean(key, false);
        return aBoolean;
    }
}
