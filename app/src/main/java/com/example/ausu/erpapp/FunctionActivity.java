package com.example.ausu.erpapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.fragment.AppointmentFragment;
import com.example.ausu.erpapp.fragment.ScheduleFragment;

/**
 * Created by Lanxumit on 2016/7/27.
 * //将Function功能模块用Fragment展示
 */
public class FunctionActivity extends BaseActivity {
    private TextView title;
    private String titleStr;
    private FrameLayout contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        title = (TextView) findViewById(R.id.title);
        contentLayout = (FrameLayout) findViewById(R.id.contentLayout);
        if (getIntent() != null) {
            if (getIntent().hasExtra("title")) {
                titleStr = getIntent().getStringExtra("title");
            }
        }
        title.setText(titleStr);
        if ("进程管理".equals(titleStr)) {
            changeFragment(new ScheduleFragment());
        } else if ("预约直考".equals(titleStr)) {
            changeFragment(new AppointmentFragment());
        }
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentLayout, fragment).commit();
    }
}
