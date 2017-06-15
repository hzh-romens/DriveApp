package com.example.ausu.erpapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ausu.erpapp.adapter.CoachListAdapter;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.CoachInfoBean;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.NetUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/31.
 */
public class CoachListActivity extends BaseActivity {
    private ImageView backBtn, filter;
    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private List<CoachInfoBean> result;
    private CoachListAdapter coachListAdapter;
    public static final int CURRENT = 1;
    public static final int NEXT = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CURRENT:
                    //将传递过来的数据转化一下
                    JsonNode jsonNode = (JsonNode) msg.obj;
                    if (jsonNode != null && jsonNode.size() != 0) {
                        result = new ArrayList<CoachInfoBean>();
                        for (int i = 0; i < jsonNode.size(); i++) {
                            CoachInfoBean coachInfoBean = CoachInfoBean.jsonToEntity(jsonNode.get(i));
                            result.add(coachInfoBean);
                        }
                    }

                    coachListAdapter.bindData(result);
                    break;
                case NEXT:
                    JsonNode jsonNode2 = (JsonNode) msg.obj;
                    if (jsonNode2 != null && jsonNode2.size() != 0) {
                        result = new ArrayList<CoachInfoBean>();
                        for (int i = 0; i < jsonNode2.size(); i++) {
                            CoachInfoBean coachInfoBean = CoachInfoBean.jsonToEntity(jsonNode2.get(i));
                            result.add(coachInfoBean);
                        }
                    }
                    coachListAdapter.addData(result);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachlist);
        setView();
        setListener();
        getData();
        coachListAdapter = new CoachListAdapter(this);
        listView.setAdapter(coachListAdapter);
    }

    private void setListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!refreshLayout.isRefreshing()) {
                    getData();
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
                            getData();
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void setView() {
        backBtn = (ImageView) findViewById(R.id.btn_back);
        filter = (ImageView) findViewById(R.id.flag_filter);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listview);
    }

    private int pageSize = 10;
    private int pageNum = 1;

    private void getData() {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("pageNum", pageNum + "");
        hashMap.put("pageSize", pageSize + "");
        hashMap.put("cityId", "1");
        netUtils.connectServer(hashMap, Config.BASE_URL + "/teacher/list");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                Log.i("教练列表数据-----", result);
                try {
                    JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                    JsonNode pageList = jsonNode.path("pageList");
                    if (pageNum == 1) {
                        //第一页数据或者下拉刷新数据
                        handler.sendMessage(handler.obtainMessage(CURRENT, pageList));
                    } else {
                        //滑动到底部加载数据
                        handler.sendMessage(handler.obtainMessage(NEXT, pageList));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
