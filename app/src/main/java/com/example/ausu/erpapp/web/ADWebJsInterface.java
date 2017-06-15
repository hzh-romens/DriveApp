package com.example.ausu.erpapp.web;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;


import org.json.JSONException;
import org.json.JSONObject;

public class ADWebJsInterface extends JsBaseInterface {
    private Handler handler = new Handler();

    public ADWebJsInterface(Context context) {
        super(context);
    }

    @JavascriptInterface
    public void openGoods(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String id = jsonObject.getString("ID");
            if (!TextUtils.isEmpty(id)) {
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void login() {
    }


    @JavascriptInterface
    public String getToken() {
     return "";
    }

    @Override
    public void onJavascript() {
        handler.post(new Runnable() {
            public void run() {
                if (webView != null) {
                    String jsName = onCreateJsName();
                    //webView.loadUrl(String.format("javascript:insertJson(%s.query())", jsName));
                }
            }
        });
    }

    @Override
    protected String onCreateJsName() {
        return "Mobile";
    }
}
