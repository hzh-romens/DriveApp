package com.example.ausu.erpapp;

import android.os.Bundle;
import android.widget.ListView;

import com.example.ausu.erpapp.adapter.DriverSchoolAdapter;

/**
 * Created by AUSU on 2016/7/12.
 */
public class DriverSchoolActivity extends BaseActivity{
    private ListView listView;
    private DriverSchoolAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverschool);
        listView= (ListView) findViewById(R.id.listview);
    }
}
