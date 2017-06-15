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
import com.example.ausu.erpapp.adapter.LetterAdapter;
import com.example.ausu.erpapp.model.LetterBean;
import com.example.ausu.erpapp.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/28.
 * 教练寄语界面
 */
public class LetterFragment extends Fragment {
    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private LetterAdapter letterAdapter;
    private List<LetterBean> result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_letter, null);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        listView = (ListView) view.findViewById(R.id.listview);
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

    public void initData() {
        result = new ArrayList<LetterBean>();
        LetterBean letterBean = new LetterBean();
        letterBean.setTime(System.currentTimeMillis());
        letterBean.setLetter("dfhjkdshfjkdshfkjdshkfhdsj");
        result.add(letterBean);
        LetterBean letterBean1 = new LetterBean();
        letterBean1.setTime(1235466878);
        letterBean1.setLetter("dfhjkdshfjkdshffsdgffgghdsj");
        result.add(letterBean1);
        //传入页码，也可进行翻页加载
        refreshLayout.setRefreshing(true);
        //从服务器获取数据。获取到数据的时候将 refreshLayout.setRefreshing(false);
        letterAdapter = new LetterAdapter(getActivity());
        letterAdapter.bindData(result);
        listView.setAdapter(letterAdapter);
        refreshLayout.setRefreshing(false);
    }
}
