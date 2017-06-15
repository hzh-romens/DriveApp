package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.utils.UIUtils;
import com.example.ausu.erpapp.view.CloudImageView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/4.
 */
public class ItemContentCell extends FrameLayout {
    private LinearLayout contentLayout;
    private TextView userName, mycontent, time, contentCount, recommondView;
    private ImageView head, delete;

    public ItemContentCell(Context context) {
        super(context);
        initView(context);
    }


    public ItemContentCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ItemContentCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_content, null);
        contentLayout = (LinearLayout) view.findViewById(R.id.contentLayout);
        contentCount = (TextView) view.findViewById(R.id.content);
        userName = (TextView) view.findViewById(R.id.userName);
        mycontent = (TextView) view.findViewById(R.id.mycontent);
        time = (TextView) view.findViewById(R.id.time);
        head = (ImageView) view.findViewById(R.id.head);
        delete = (ImageView) view.findViewById(R.id.delete);
        recommondView = (TextView) view.findViewById(R.id.recommond);
        //如果是我的页面显示delete,如果带推荐标志则显示推荐文本
        delete.setVisibility(GONE);
        recommondView.setVisibility(VISIBLE);
        addView(view);
    }

    public void addImageView(String url) {
        contentLayout.setOrientation(LinearLayout.HORIZONTAL);
        CloudImageView contentView = new CloudImageView(getContext());
        contentView.setImagePath(url);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, UIUtils.dip2px(96));
        params.weight = 1;
        contentLayout.addView(contentView, params);
    }

    public void addContentView(String content) {
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        TextView contentTextView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(32));
        layoutParams.setMargins(UIUtils.dip2px(15), 0, UIUtils.dip2px(15), 0);
        contentTextView.setGravity(Gravity.CENTER_VERTICAL);
        contentTextView.setPadding(UIUtils.dip2px(4), UIUtils.dip2px(6), UIUtils.dip2px(4), UIUtils.dip2px(6));
        contentTextView.setTextSize(14);
        contentTextView.setLayoutParams(layoutParams);
        contentTextView.setText("回复我的：测试评论");
        setShape(contentTextView);
        contentLayout.addView(contentTextView);
    }

    private void setShape(TextView contentTextView) {
        int roundRadius = 12; // 8dp 圆角半径
        int strokeColor = Color.parseColor("#ffffffff");//边框颜色
        int fillColor = Color.parseColor("#eeeeee");//内部填充颜色
        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(1, strokeColor);
        contentTextView.setBackgroundDrawable(gd);
    }


}
