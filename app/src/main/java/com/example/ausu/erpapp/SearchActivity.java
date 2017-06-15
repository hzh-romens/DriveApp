package com.example.ausu.erpapp;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ausu.erpapp.adapter.HistoryAdapter;
import com.example.ausu.erpapp.adapter.SearchAdapter;
import com.example.ausu.erpapp.model.SearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/26.
 */
public class SearchActivity extends BaseActivity {
    private ListView searchListview, historyListview;
    private SearchAdapter searchAdapter;
    private HistoryAdapter historyAdapter;
    private EditText searchView;
    private TextView cancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchListview = (ListView) findViewById(R.id.searchListview);
        historyListview = (ListView) findViewById(R.id.historyListview);
        searchView = (EditText) findViewById(R.id.edit_search);
        cancleBtn = (TextView) findViewById(R.id.cancle);
        initData();
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            searchView.getWindowToken(), 0);
                    searchData(searchView.getText().toString().trim());
                    historyListview.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setText("");
            }
        });
    }

    private List<SearchBean> searchBeanList;


    private void searchData(String trim) {
        //搜索驾校
        searchListview.setVisibility(View.VISIBLE);
        searchBeanList = new ArrayList<SearchBean>();
        searchAdapter = new SearchAdapter(this);
        //将搜索的数据添加到Adapter
        for (int i = 0; i < 10; i++) {
            SearchBean searchBean = new SearchBean();
            searchBeanList.add(searchBean);
        }
        searchAdapter.bindData(searchBeanList);
        searchListview.setAdapter(searchAdapter);


    }

    private SparseArray historyDataList;

    private void initData() {
        //先从数据库获取历史搜索记录的数据
        historyAdapter = new HistoryAdapter(this);
        searchListview.setVisibility(View.GONE);
        historyDataList = new SparseArray();
        historyDataList.put(0, "历史搜索");
        for (int i = 1; i <= 5; i++) {
            SearchBean searchBean = new SearchBean();
            historyDataList.put(i, searchBean);
        }
        historyDataList.put((5 + 1), "清空搜索记录");
        historyAdapter.bindData(historyDataList);
        historyListview.setAdapter(historyAdapter);

    }


}
