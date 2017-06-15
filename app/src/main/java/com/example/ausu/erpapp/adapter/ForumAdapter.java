package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ausu.erpapp.cell.ItemContentCell;

import java.util.List;

/**
 * Created by AUSU on 2016/7/6.
 */
public class ForumAdapter extends BaseAdapter {
    private List<Object> mResult;
    private Context mContext;

    public void setData(List<Object> result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    public ForumAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mResult != null ? mResult.size() : 0;
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
        if (convertView == null) {
            convertView = new ItemContentCell(mContext);
        }
        ItemContentCell cell = (ItemContentCell) convertView;
        if (position == 0) {
            ImageView imageView = new ImageView(mContext);
            cell.addContentView("dd");
        }
        return convertView;
    }
}
