package com.example.ausu.erpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.ExamAdapter;
import com.example.ausu.erpapp.model.ExamBean;
import com.example.ausu.erpapp.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/28.
 * 理论考试界面
 */
public class ExamFragment extends Fragment {
    private ListView listView;
    private ExamAdapter examAdapter;
    private SwipeRefreshLayout refreshLayout;
    private List<ExamBean> result;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_exam, null);
        listView = (ListView) view.findViewById(R.id.listview);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        UIHelper.setupSwipeRefreshLayoutProgress(refreshLayout);
        UIHelper.updateSwipeRefreshProgressBarTop(getActivity(), refreshLayout);
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
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        result = new ArrayList<ExamBean>();
        ExamBean examBean = new ExamBean();
        examBean.setExamID("1");
        examBean.setName("倒车入库考试");
        examBean.setIsSingup(false);
        result.add(examBean);
        //传入页码，也可进行翻页加载
        refreshLayout.setRefreshing(true);
        //从服务器获取数据。获取到数据的时候将 refreshLayout.setRefreshing(false);
        examAdapter = new ExamAdapter(getActivity());
        examAdapter.setData(result);
        listView.setAdapter(examAdapter);
        refreshLayout.setRefreshing(false);
    }
}
