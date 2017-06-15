package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;

/**
 * Created by AUSU on 2016/7/17.
 */
public class ContentAdapter extends BaseAdapter {
    private Context mContext;

    public ContentAdapter(Context context) {
        this.mContext = context;
    }

    public void setData() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder itemHolder;
        if (convertView == null) {
            itemHolder = new ItemHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_content, null);
            itemHolder.content = (TextView) convertView.findViewById(R.id.content);
            itemHolder.contentLayout = (LinearLayout) convertView.findViewById(R.id.contentLayout);
            itemHolder.delete = (ImageView) convertView.findViewById(R.id.delete);
            itemHolder.head = (ImageView) convertView.findViewById(R.id.head);
            itemHolder.userName = (TextView) convertView.findViewById(R.id.userName);
            itemHolder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        //设置值
        return convertView;
    }

    public static class ItemHolder {
        private ImageView head, delete;
        private TextView userName, time, content;
        private LinearLayout contentLayout;
    }
}
