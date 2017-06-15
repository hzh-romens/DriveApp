package com.example.ausu.erpapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ausu.erpapp.adapter.AppointmentAdapter;
import com.example.ausu.erpapp.model.UserAppointmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class UserAppointmentActivity extends BaseActivity {
    private ListView listView;
    private TextView title;
    private ImageView btnBack;
    private AppointmentAdapter appointmentAdapter;
    private List<UserAppointmentBean> result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userappointment);
        listView = (ListView) findViewById(R.id.listview);
        initData();
    }

    private void initData() {
        result = new ArrayList<UserAppointmentBean>();
        UserAppointmentBean userAppointmentBean = new UserAppointmentBean();
        userAppointmentBean.setAppointmentTime("dds");
        userAppointmentBean.setItem("直角拐弯");
        userAppointmentBean.setType(1);
        result.add(userAppointmentBean);
        appointmentAdapter = new AppointmentAdapter(this);
        appointmentAdapter.setData(result);
        listView.setAdapter(appointmentAdapter);

    }
}
