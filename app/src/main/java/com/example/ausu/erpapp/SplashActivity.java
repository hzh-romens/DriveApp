package com.example.ausu.erpapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.ausu.erpapp.utils.AccountUtils;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.OkHttpClientManager;
import com.fasterxml.jackson.databind.JsonNode;


import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;

public class SplashActivity extends Activity {

    private static final int LANUCH = 1;
    private static final int MAIN = 3;
    private Handler mMainHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case LANUCH:
                    nav();
                    break;
                case 2:
                    //调用另外一个登陆接口
                    SkipLogin();
                    break;
                case 3:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    break;

                default:
                    break;
            }
        }

        ;
    };
    private String access_token;
    private String loing_name;

    private void SkipLogin() {
        String url = "http://yz001.huangsi.wang:8084/api/pp/passport/mobilelogin";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("loginName", loing_name);
        params.put("accesstoken", access_token);
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                android.util.Log.i("errorMsg~~~~~~", e.toString());
            }

            @Override
            public void onResponse(Object response) {
                if (response != null && !"".equals(response)) {
                    try {
                        JsonNode jsonNode = JacksonMapper.getInstance().readTree(response.toString());
                        if (jsonNode.has("errorMsg")) {
                            //意味着登陆失败，需要清空登陆时记录的信息
                            ClearInfo();
                            Toast.makeText(SplashActivity.this, jsonNode.path("errorMsg").asText(), Toast.LENGTH_SHORT).show();
                        } else {
                            //登陆成功，接下来验证用户信息
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, params);
    }


    private void ValidateUser() {
        OkHttpClientManager.getAsyn("", new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                android.util.Log.i("errorMsg---", e.toString());
            }

            @Override
            public void onResponse(Object response) {
                // android.util.Log.i("用户验证信息---", response.toString());
                try {
                    JsonNode jsonNode = JacksonMapper.getInstance().readTree(response.toString());
                    if (jsonNode.has("errorMsg")) {
                        //验证用户信息失败，即用户登录失败
                        ClearInfo();
                        Toast.makeText(SplashActivity.this, jsonNode.path("errorMsg").asText(), Toast.LENGTH_SHORT).show();
                    } else {
                        //验证成功，保存用户登录信息，并进入主页面
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        if (AccountUtils.instance().getStatus("loaded")) {
            mMainHandler.sendEmptyMessage(3);
        } else {
            mMainHandler.sendEmptyMessage(LANUCH);
        }

    }


    private void nav() {
        AccountUtils.instance().saveStatus("loaded", true);
        startActivity(new Intent(this, NavActivity.class));
        finish();
        // startActivity(new Intent(this,MainActivity.class));
        //finish();
        //用户登陆过 必定已经导航过

        //未登陆过
        //已经导航过 则进入登陆页

        //未登录 也未导航过


    }

    /**
     * 登录失败的，清除记录的信息
     */
    private void ClearInfo() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
