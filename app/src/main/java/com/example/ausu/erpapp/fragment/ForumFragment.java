package com.example.ausu.erpapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ScaleXSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ausu.erpapp.ContentBodyActivity;
import com.example.ausu.erpapp.PostCommentActivity;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.ForumAdapter;
import com.example.ausu.erpapp.utils.DrawableUtils;
import com.example.ausu.erpapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by pc on 2016/4/25.
 * 论坛
 */
public class ForumFragment extends Fragment {
    private ListView listView;
    private ForumAdapter forumAdapter;
    private ImageView post;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_distance, null);
        listView = (ListView) view.findViewById(R.id.listview);
        post = (ImageView) view.findViewById(R.id.post);
        addHeadView();
        List<Object> object = new ArrayList<Object>();
        object.add(1);
        object.add(2);
        forumAdapter = new ForumAdapter(getActivity());
        forumAdapter.setData(object);
        listView.setAdapter(forumAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().startActivity(new Intent(getActivity(), ContentBodyActivity.class));
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), PostCommentActivity.class));
            }
        });
        return view;
    }

    private void addHeadView() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackground(getActivity().getResources().getDrawable(R.mipmap.ic_launcher));
        imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(200)));
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_header, null);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_head_content, null);
        View contentView2 = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_head_content, null);
        TextView comtentTitle = (TextView) contentView.findViewById(R.id.content_title);
        addSpanableView(comtentTitle);
        listView.addHeaderView(imageView, null, false);
        listView.addHeaderView(contentView2, null, false);
        listView.addHeaderView(contentView, null, false);
        listView.addHeaderView(view1, null, false);
    }


    //在结尾添加置顶标签
    private void addSpanableView(TextView comtentTitle) {
        String testStr = "Spannable标题测试------";
        //加一个判断，是否置顶
        testStr = testStr + "  置顶";
        SpannableString spannableString = new SpannableString(testStr);
        Drawable drawable = getActivity().getResources().getDrawable(R.drawable.shape_stick);
        DrawableUtils.MyImageSpan imageSpan = new DrawableUtils.MyImageSpan(drawable);
        spannableString.setSpan(imageSpan, testStr.length() - 2, testStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        comtentTitle.setText(spannableString);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
