package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;

import java.util.List;

/**
 * Created by Lanxumit on 2016/9/5.
 */
public class ClassDetailCell extends FrameLayout {
    private TextView title;
    private LinearLayout itemView;

    public ClassDetailCell(Context context) {
        super(context);
        initView(context);
    }

    public ClassDetailCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ClassDetailCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_class_detail, null);
        title = (TextView) view.findViewById(R.id.title);
        itemView = (LinearLayout) view.findViewById(R.id.itemView);
        addView(view);
    }

    public void addItem(int index, View childView) {
        //给View设置Tag，复用View，防止重复添加
        if (childView != null) {
            if (itemView.findViewWithTag(index) == null) {
                childView.setTag(index);
                itemView.addView(childView);
            }
        }

    }

    public void setTitle(String value) {
        title.setText(value);
    }

}
