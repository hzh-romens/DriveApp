package com.example.ausu.erpapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ausu.erpapp.adapter.DriverSchoolDetailAdapter;
import com.example.ausu.erpapp.model.DriverClassBean;
import com.example.ausu.erpapp.model.DriverSchoolBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Lanxumit on 2016/7/19.
 */
public class DriverSchoolDetailActivity extends BaseActivity {
    private ListView listView;
    private ImageView backBtn;
    private List<Object> result;
    private DriverSchoolBean driverSchoolBean;
    private List<DriverClassBean> classList;
    private DriverSchoolDetailAdapter adapter;
    private String[] imageList = new String[4];
    private List<Integer> types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("driverSchool")) {
                driverSchoolBean = (DriverSchoolBean) getIntent().getExtras().getSerializable("driverSchool");
            }
        }

        listView = (ListView) findViewById(R.id.listview);
        backBtn = (ImageView) findViewById(R.id.ic_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        //获取数据，并解析
        String json = "";
        types = new ArrayList<Integer>();
        classList = new ArrayList<DriverClassBean>();
        //   types.add(0);
        //   types.add(1);
        //    types.add(2);
        //添加套餐信息
        for (int i = 0; i < classList.size(); i++) {
            classList.add(classList.get(i));
            //    types.add(3);
        }
        //     types.add(1);
        //    types.add(2);
        //添加四张图片
        for (int i = 0; i < 4; i++) {
            imageList[i] = "www.baidu.com";
        }

        driverSchoolBean = new DriverSchoolBean();
        driverSchoolBean.setDriverSchoolName("天津滨海驾校");
        driverSchoolBean.setAddress("天津市大港区古林街");
        driverSchoolBean.setDriverSchoolId("1");
        driverSchoolBean.setDistance("20km");


        result = new ArrayList<Object>();
        result.add(driverSchoolBean);
        types.add(0);
        result.add("shadow");
        types.add(1);
        result.add("所有套餐");
        types.add(2);
        DriverClassBean driverClassBean = new DriverClassBean();
        driverClassBean.setProductname("暑假特惠普通班");
        driverClassBean.setProductsummary("班车接送");
        driverClassBean.setProductprice(2000);
        driverClassBean.setEnrollment(20);
        result.add(driverClassBean);
        types.add(3);
        DriverClassBean driverClassBean1 = new DriverClassBean();
        driverClassBean1.setProductname("暑假特惠普通班2");
        driverClassBean1.setProductsummary("班车接送,15天学完");
        driverClassBean1.setProductprice(4000);
        driverClassBean1.setEnrollment(10);
        result.add(driverClassBean1);
        types.add(3);
        result.add("shadow");
        types.add(1);
        result.add("场地照片");
        types.add(2);

        //暂时先写假的
        result.add("图片集合");
        types.add(4);

        adapter = new DriverSchoolDetailAdapter(this);
        adapter.bindData(driverSchoolBean, classList, imageList, types, result);
        listView.setAdapter(adapter);
    }
}
