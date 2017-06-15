package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ausu.erpapp.R;

/**
 * Created by AUSU on 2016/7/11.
 */
public class ActivityCell extends FrameLayout {
    private TextView timer,driverName,discount,price,enrollNumber;
    private ImageView carIcon;
    public ActivityCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ActivityCell(Context context) {
        super(context);
        initView(context);
    }

    public ActivityCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, R.layout.list_item_ativity, null);
        timer= (TextView) inflate.findViewById(R.id.timer);
        driverName= (TextView) inflate.findViewById(R.id.driverName);
        discount= (TextView) inflate.findViewById(R.id.discount);
        price= (TextView) inflate.findViewById(R.id.price);
        enrollNumber= (TextView) inflate.findViewById(R.id.enroll);
        carIcon= (ImageView) inflate.findViewById(R.id.carIcon);
        addView(inflate);
    }
    //设置值
    public void setValue(){

    }

}
