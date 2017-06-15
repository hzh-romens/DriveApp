package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.view.MyListView;


/**
 * Created by Lanxumit on 2016/7/19.
 */
public class FilterCell extends FrameLayout {
    private RadioGroup filterGroup;
    private MyListView myListView;

    public FilterCell(Context context) {
        super(context);
        initView(context);
    }


    public FilterCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FilterCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View filter = View.inflate(context, R.layout.list_item_filter, null);
        filterGroup = (RadioGroup) filter.findViewById(R.id.tabContainer);
        myListView = (MyListView) filter.findViewById(R.id.myListView);
        filterGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.flag_all:
                        break;
                    case R.id.flag_account:
                        break;
                    case R.id.flag_content:
                        break;
                    case R.id.flag_distance:
                        break;
                    case R.id.flag_filter:
                        //点击事件，listview向上移动一段距离再打开一个选择窗口
                        break;

                }
            }
        });
        addView(filter);
    }

}
