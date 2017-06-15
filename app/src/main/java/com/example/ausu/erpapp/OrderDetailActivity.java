package com.example.ausu.erpapp;

import android.os.Bundle;
import android.widget.ListView;

import com.example.ausu.erpapp.adapter.OrderAdapter;
import com.example.ausu.erpapp.model.OrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class OrderDetailActivity extends BaseActivity {
    private ListView listView;
    private List<OrderBean> orderBeans;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        listView = (ListView) findViewById(R.id.listview);
        initData();
    }

    private void initData() {
        orderBeans = new ArrayList<OrderBean>();
        OrderBean orderBean = new OrderBean();
        orderBean.setCoach("某某");
        orderBean.setAdress("天津滨海大道");
        orderBean.setPayCount("$3000");
        orderBean.setGroup("夏季班");
        orderBean.setPayType("在线支付");
        orderBean.setSchool("天津滨海驾校");
        orderBeans.add(orderBean);
        orderAdapter = new OrderAdapter(this);
        orderAdapter.setData(orderBeans);
        listView.setAdapter(orderAdapter);
    }
}
