package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;

/**
 * Created by AUSU on 2016/5/23.
 */
public class ListItemCell extends FrameLayout {
    private TextView itemview;

    public ListItemCell(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.listview_textview, null);
        itemview = (TextView) inflate.findViewById(R.id.itemView);
        addView(inflate);
    }

    public ListItemCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ListItemCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setValue(String value) {
        itemview.setText(value);
    }
}
