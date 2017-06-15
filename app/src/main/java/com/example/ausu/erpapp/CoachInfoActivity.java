package com.example.ausu.erpapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.SparseArray;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.ausu.erpapp.adapter.CoachInfoAdapter;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.CoachInfoBean;
import com.example.ausu.erpapp.model.CommentBean;
import com.example.ausu.erpapp.model.DriverClassBean;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.NetUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Lanxumit on 2016/7/23.
 */
public class CoachInfoActivity extends BaseActivity {
    private ListView listView;
    private CoachInfoAdapter adapter;
    private HashMap<Integer, Object> datas;
    private CoachInfoBean coachInfoBean;
    private List<CommentBean> commentDatas;
    private SwipeRefreshLayout refreshLayout;
    private List<Integer> types;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachinfo);
        listView = (ListView) findViewById(R.id.listview);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        initData();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!refreshLayout.isRefreshing()) {
                    initData();
                }
                refreshLayout.setRefreshing(false);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            //滚动到底部，添加下一页的数据
                            pageNum++;
                            getContentData();
                            //initData();
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    private void initData() {
        adapter = new CoachInfoAdapter(this);
        datas = new HashMap<Integer, Object>();
        types = new ArrayList<Integer>();
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            coachInfoBean = (CoachInfoBean) extras.getSerializable("coach");
        }
        datas.put(0, coachInfoBean);
        types.add(0);

        datas.put(1, "shadow");
        types.add(1);

        datas.put(2, "address");
        types.add(2);

        datas.put(3, "shadow");
        types.add(3);


        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("teacherId", coachInfoBean.getId() + "");
        netUtils.connectServer(hashMap, Config.BASE_URL + "/teacher/detail");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                Log.i("教练详情------", result);
                //获取教练带的班次
            }
        });
        //如果没有班级就将培训班级和下面的阴影模块去掉
        datas.put(4, "培训班次");
        types.add(4);
        DriverClassBean driverClassBean = new DriverClassBean();
        driverClassBean.setProductname("暑假特惠普通班");
        driverClassBean.setProductsummary("班车接送");
        driverClassBean.setProductprice(2000);
        driverClassBean.setEnrollment(20);
        datas.put(5, driverClassBean);
        types.add(5);
        DriverClassBean driverClassBean1 = new DriverClassBean();
        driverClassBean1.setProductname("暑假特惠普通班2");
        driverClassBean1.setProductsummary("班车接送,15天学完");
        driverClassBean1.setProductprice(4000);
        driverClassBean1.setEnrollment(10);
        datas.put(6, driverClassBean1);
        types.add(6);
        datas.put(7, "shadow");
        types.add(7);
        getContentData();
    }

    private int pageSize = 10;
    private int pageNum = 1;

    //获取评论
    private void getContentData() {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("pageSize", pageSize + "");
        hashMap.put("pageNum", pageNum + "");
        hashMap.put("coaChId", coachInfoBean.getId() + "");
        netUtils.connectServer(hashMap, Config.BASE_URL + "/teacher/comments");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                try {
                    JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                    JsonNode pageList = jsonNode.path("pageList");
                    if (pageList != null && pageList.size() != 0 && pageNum == 1) {
                        datas.put(8, "用户评价" + pageList.size());
                        types.add(8);
                    }
                    if (pageList != null && pageList.size() != 0) {
                        int size = datas.size();
                        for (int i = 0; i < pageList.size(); i++) {
                            CommentBean commentBean = CommentBean.jsonToEntity(pageList.get(i));
                            datas.put(i + size, commentBean);
                            types.add(i + size);
                        }
                        if (pageNum == 1) {
                            //绑定数据
                            adapter.bindData(datas, pageList.size());
                            adapter.bindTypes(types);
                            listView.setAdapter(adapter);
                        } else {
                            //添加数据
                            adapter.addData(datas, pageList.size());
                            adapter.bindTypes(types);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }


}
