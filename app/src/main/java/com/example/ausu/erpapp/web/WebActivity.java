package com.example.ausu.erpapp.web;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.BaseActivity;
import com.example.ausu.erpapp.R;

/**
 * Created by siery on 15/5/5.
 */
public abstract class WebActivity extends BaseActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webvew);

        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAppCacheEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    view.loadUrl(url);
                }
                return true;
            }
        });


        // 如果不设置这个，JS代码中的按钮会显示，但是按下去却不弹出对话框
        // Sets the chrome handler. This is an implementation of WebChromeClient
        // for use in handling JavaScript dialogs, favicons, titles, and the
        // progress. This will replace the current handler.
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //网页加载完毕，也就是进度条达到100，这个时候调用onWebPageCompleted方法
                super.onProgressChanged(view, newProgress);
            }

        });
    }

    @Override
    public void onDestroy() {
        WebView webView = getWebView();
        if (webView != null) {
            webView.clearCache(true);
        }
        super.onDestroy();
    }

    protected abstract void onWebPageCompleted();

    protected WebView getWebView() {
        return this.webView;
    }


    ;
}
