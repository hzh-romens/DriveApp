package com.example.ausu.erpapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.SparseArray;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.ausu.erpapp.adapter.ContentBodyAdapter;
import com.example.ausu.erpapp.model.CommentBodyBean;
import com.example.ausu.erpapp.model.CommentPostBean;
import com.example.ausu.erpapp.model.CommentTitleBean;
import com.example.ausu.erpapp.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Lanxumit on 2016/8/5.
 */
public class ContentBodyActivity extends BaseActivity {
    private ListView listView;
    private List<Object> result;
    private SparseArray types;
    private ContentBodyAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentbody);
        listView = (ListView) findViewById(R.id.listview);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);


        UIHelper.setupSwipeRefreshLayoutProgress(refreshLayout);
        UIHelper.updateSwipeRefreshProgressBarTop(this, refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(true);
                    initData();
                }
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
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        initData();
    }

    private void initData() {
        refreshLayout.setRefreshing(false);
        result = new ArrayList<Object>();
        types = new SparseArray();
        CommentTitleBean commentTitleBean = new CommentTitleBean();
        commentTitleBean.setTitle("张三驾校科目练习经历");
        commentTitleBean.setTitle("6月28日");

        result.add(commentTitleBean);
        CommentBodyBean commentBodyBean = new CommentBodyBean();
        commentBodyBean.setAvatar("");
        commentBodyBean.setBody("圣诞节开始接电话开始的框架是咖啡酒红色的开发和深刻的");
        commentBodyBean.setName("张三");
        types.put(0, 1);

        result.add(commentBodyBean);
        types.put(1, 2);

        result.add("empty");
        types.put(2, 3);

        result.add("评论详情");
        types.put(3, 4);

        CommentPostBean commentPostBean = new CommentPostBean();
        commentPostBean.setName("李四");
        commentPostBean.setTime(1232423534);
        List<String> commnets = new ArrayList<String>();
        commnets.add("是卡卡是");
        commentPostBean.setCommemts(commnets);
        types.put(4, 5);
        CommentPostBean commentPostBean2 = new CommentPostBean();
        commentPostBean2.setName("李四");
        commentPostBean.setTime(1232423534);
        List<String> commnets2 = new ArrayList<String>();
        commnets.add("是卡卡是");
        commentPostBean2.setCommemts(commnets2);
        types.put(5, 5);
        adapter = new ContentBodyAdapter(this);
        adapter.setData(result, types);
        listView.setAdapter(adapter);

    }
}
