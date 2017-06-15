package com.example.ausu.erpapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;
import android.widget.ListView;

/**
 * Created by AUSU on 2016/7/6.
 */
public class MyExpandListView extends ExpandableListView {


    public MyExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyExpandListView(Context context) {
        super(context);
    }


    public MyExpandListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
