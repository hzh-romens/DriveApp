package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;

/**
 * Created by Lanxumit on 2016/7/26.
 */
public class SearchCell extends FrameLayout {
    private TextView schoolName;

    public SearchCell(Context context) {
        super(context);
        initView(context);
    }

    public SearchCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SearchCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_search, null);
        schoolName= (TextView) view.findViewById(R.id.name);
        addView(view);
    }
    public void setValue(String name){
        schoolName.setText(name);
    }

}
