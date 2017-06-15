package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.HorizontalAdapter;
import com.example.ausu.erpapp.utils.UIUtils;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/13.
 */
public class HorizontalImageCell extends FrameLayout {
    private RecyclerView horizontalView;
    private HorizontalAdapter horizontalAdapter;

    public HorizontalImageCell(Context context) {
        super(context);
        initView(context);
    }

    public HorizontalImageCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HorizontalImageCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_horizontal, null);
        horizontalView = (RecyclerView) view.findViewById(R.id.horizontalView);
        //创建默认的线性LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalView.setLayoutManager(linearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        horizontalView.setHasFixedSize(true);
        addView(view, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(60)));
    }

    public void setValue(List<String> urls) {
        horizontalAdapter = new HorizontalAdapter();
        horizontalAdapter.bindData(urls);
        horizontalView.setAdapter(horizontalAdapter);
    }
}
