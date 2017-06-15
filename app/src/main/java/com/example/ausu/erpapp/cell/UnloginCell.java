package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.LoginActivity;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.utils.UIUtils;

/**
 * Created by Lanxumit on 2016/10/8.
 */
public class UnloginCell extends FrameLayout {
    private TextView loginBtn;

    public UnloginCell(Context context) {
        super(context);
        initView(context);
    }

    public UnloginCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public UnloginCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        View view = View.inflate(context, R.layout.layout_unlogin, null);
        loginBtn = (TextView) view.findViewById(R.id.login);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        addView(view, layoutParams);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(UIUtils.dip2px(200), MeasureSpec.EXACTLY));
//    }
}
