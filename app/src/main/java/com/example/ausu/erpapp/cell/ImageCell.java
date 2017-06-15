package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;

/**
 * Created by Lanxumit on 2016/7/30.
 */
public class ImageCell extends FrameLayout {
    public ImageCell(Context context) {
        super(context);
        initView(context);
    }

    public ImageCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ImageCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_image_appointment, null);
        addView(view);
    }


}
