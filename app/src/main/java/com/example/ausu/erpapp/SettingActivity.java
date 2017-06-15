package com.example.ausu.erpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.utils.AccountUtils;

import org.w3c.dom.Text;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout resetPassword, feedback, about, cleanCache, checkVersion;
    private TextView exitLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);
        resetPassword = (LinearLayout) findViewById(R.id.layout_password);
        feedback = (LinearLayout) findViewById(R.id.layout_feedback);
        about = (LinearLayout) findViewById(R.id.layout_about);
        cleanCache = (LinearLayout) findViewById(R.id.layout_clean_cache);
        checkVersion = (LinearLayout) findViewById(R.id.layout_check_version);
        exitLogin = (TextView) findViewById(R.id.exit_login);
        resetPassword.setOnClickListener(this);
        feedback.setOnClickListener(this);
        about.setOnClickListener(this);
        cleanCache.setOnClickListener(this);
        checkVersion.setOnClickListener(this);
        exitLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SettingActivity.this, SettingFragmentActivity.class);
        switch (v.getId()) {
            case R.id.layout_password:
                intent.putExtra("name", "密码重置");
                startActivity(intent);
                break;
            case R.id.layout_feedback:
                intent.putExtra("name", "意见反馈");
                startActivity(intent);
                break;
            case R.id.layout_about:
                intent.putExtra("name", "关于");
                startActivity(intent);
                break;
            case R.id.layout_clean_cache:
                //清除缓存
                break;
            case R.id.layout_check_version:
                //版本检测
                break;
            case R.id.exit_login:
                //清除本地存储的用户数据
                AccountUtils.instance().cleanAccount();
                finish();
                //intent.putExtra("name", "退出");
                //startActivity(intent);
                break;
        }
    }
}
