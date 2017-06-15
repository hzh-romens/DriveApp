package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;

/**
 * Created by Lanxumit on 2016/9/5.
 */
public class PackageCoachCell extends FrameLayout {
    public PackageCoachCell(Context context) {
        super(context);
        initView(context);
    }

    public PackageCoachCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PackageCoachCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_layout_package_coach, null);
        addView(view);
    }

}
