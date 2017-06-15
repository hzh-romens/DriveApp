package com.example.ausu.erpapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.widget.ListView;

import com.example.ausu.erpapp.adapter.PackageAdapter;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.fragment.DriverExamFragment;
import com.example.ausu.erpapp.fragment.ExamFragment;
import com.example.ausu.erpapp.fragment.WebFragment;
import com.example.ausu.erpapp.model.ClassDetailBean;
import com.example.ausu.erpapp.model.CoachInfoBean;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.NetUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/9.
 * 套餐详情页面
 */
public class PackagesActivity extends BaseActivity {
    private ListView listView;
    private PackageAdapter adapter;
    private List<Object> results;
    private String classId;
    private List<Integer> types;
    private CoachInfoBean coachInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        if (getIntent() != null) {
            classId = getIntent().getStringExtra("classId");
            if (getIntent().hasExtra("coach")) {

            }
        }
        listView = (ListView) findViewById(R.id.listview);
        adapter = new PackageAdapter(this);
        getData();
    }

    private void getData() {
        HashMap hashMap = new HashMap();
        hashMap.put("classId", "1");
        NetUtils netUtils = new NetUtils();
        netUtils.connectServer(hashMap, Config.BASE_URL + "/class/detail");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                try {
                    JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                    ClassDetailBean classDetailBean = ClassDetailBean.jsonToEntity(jsonNode);
                    results = new ArrayList<Object>();
                    types = new ArrayList<Integer>();
                    types.add(0);
                    types.add(1);
                    if (coachInfoBean != null) {
                        adapter.bindInfo(coachInfoBean);
                        types.add(2);
                        types.add(3);
                    }
                    types.add(4);
                    types.add(3);
                    types.add(5);
                    adapter.bindData(classDetailBean);
                    adapter.bindTypes(types);
                    listView.setAdapter(adapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
