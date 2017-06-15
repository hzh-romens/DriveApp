package com.example.ausu.erpapp.web;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ausu.erpapp.BaseActivity;
import com.example.ausu.erpapp.R;


/**
 * Created by siery on 15/9/9.
 */
public class ADWebActivity extends WebActivity {
    public static final String ARGUMENTS_KEY_TITLE = "title";
    public static final String ARGUMENTS_KEY_TARGET_URL = "target_url";
    public static final String ARGUMENTS_KEY_HTML = "html";
    public static final String ARGUMENTS_KEY_ICON_URL = "thumbURL";

    private JsBaseInterface adWebJsInterface;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
        WebView webView = getWebView();
        adWebJsInterface = new ADWebJsInterface(this)
                .withWebView(webView);
        webView.addJavascriptInterface(adWebJsInterface, adWebJsInterface.toString());

        //区分本地网页和网络上的网页
        if (bundle.containsKey(ARGUMENTS_KEY_TARGET_URL)) {
            String url = bundle.getString(ARGUMENTS_KEY_TARGET_URL);
            webView.loadUrl(url);
        } else {
            String html = bundle.getString(ARGUMENTS_KEY_HTML, "");
            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }

    }

    @Override
    protected void onWebPageCompleted() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
