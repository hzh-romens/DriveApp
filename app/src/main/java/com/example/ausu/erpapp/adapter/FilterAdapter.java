package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ausu.erpapp.model.FilterBean;

import java.util.List;

/**
 * Created by Lanxumit on 2016/7/19.
 */
public class FilterAdapter extends BaseAdapter {
    private Context mContext;
    private List<FilterBean> mResult;

    public FilterAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(List<FilterBean> result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mResult != null ? mResult.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mResult.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
