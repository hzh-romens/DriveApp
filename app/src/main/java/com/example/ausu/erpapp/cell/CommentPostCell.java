package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/6.
 * 回复的帖子
 */
public class CommentPostCell extends FrameLayout {
    private LinearLayout commnetLayout;

    public CommentPostCell(Context context) {
        super(context);
        initView(context);
    }

    public CommentPostCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CommentPostCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_content_comment, null);
        commnetLayout = (LinearLayout) view.findViewById(R.id.contentLayout);
        addView(view);
    }

    public void addComment(String commnet) {
        TextView commentView = new TextView(getContext());
        commentView.setText(commnet);
        commentView.setTextSize(16);
        commnetLayout.addView(commentView);
    }

}
