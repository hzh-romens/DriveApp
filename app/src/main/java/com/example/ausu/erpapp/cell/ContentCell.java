package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.utils.DateUtils;
import com.example.ausu.erpapp.view.CloudImageView;
import com.hedgehog.ratingbar.RatingBar;


/**
 * Created by Lanxumit on 2016/7/25.
 */
public class ContentCell extends FrameLayout {
    private CloudImageView head;
    private TextView userName, time, content;
    private RatingBar ratingBar;

    public ContentCell(Context context) {
        super(context);
        initView(context);
    }

    public ContentCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ContentCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_coach_content, null);
        head = (CloudImageView) view.findViewById(R.id.userHead);
        userName = (TextView) view.findViewById(R.id.userName);
        time = (TextView) view.findViewById(R.id.time);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingbar);
        content = (TextView) view.findViewById(R.id.content);
        ratingBar.setmClickable(true);
        ratingBar.setClickable(true);
        addView(view);
    }

    public void setValue(String avatarUrl, String name, Long times, String contents, int level) {
        head.setImagePath(avatarUrl);
        userName.setText(name);
        time.setText(DateUtils.getTime(times));
        content.setText(contents);
        ratingBar.setStar(level);
    }

}
