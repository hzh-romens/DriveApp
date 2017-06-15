package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.view.CloudImageView;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class UserSettingCell extends FrameLayout {
    private CloudImageView head;

    public UserSettingCell(Context context) {
        super(context);
        initView(context);
    }

    public UserSettingCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public UserSettingCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_user, null);
        head = (CloudImageView) view.findViewById(R.id.headIcon);
        head.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.headclickListener();
            }
        });
        addView(view);
    }

    public void setValue(Bitmap bitmap) {
        head.setImageBitmap(bitmap);
    }

    public interface headClick {
        void headclickListener();
    }

    private headClick mClick;

    public void setHeadClickListener(headClick click) {
        this.mClick = click;
    }

}
