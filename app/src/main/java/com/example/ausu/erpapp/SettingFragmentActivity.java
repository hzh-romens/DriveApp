package com.example.ausu.erpapp;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ausu.erpapp.fragment.AboutFragment;
import com.example.ausu.erpapp.fragment.ChangePasswordFragment;
import com.example.ausu.erpapp.fragment.FeedBackFragment;

/**
 * Created by Lanxumit on 2016/10/8.
 */
public class SettingFragmentActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backView;
    private TextView titleView, submit;
    private FrameLayout container;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_setting);
        if (getIntent() != null) {
            title = getIntent().getStringExtra("name");
        }
        backView = (ImageView) findViewById(R.id.ic_back);
        titleView = (TextView) findViewById(R.id.title);
        container = (FrameLayout) findViewById(R.id.container);
        submit = (TextView) findViewById(R.id.submit);
        titleView.setText(title);
        backView.setOnClickListener(this);
        changeFragment(title);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    public void changeFragment(String value) {
        if ("密码重置".equals(value)) {
            submit.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChangePasswordFragment()).commit();
        } else if ("意见反馈".equals(value)) {
            submit.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FeedBackFragment()).commit();
        } else if ("关于".equals(value)) {
            submit.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AboutFragment()).commit();
        }
    }
}
