package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.model.OrderBean;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class OrderAdapter extends BaseAdapter {
    private List<OrderBean> mResults;
    private Context mContext;

    public OrderAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<OrderBean> results) {
        this.mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mResults != null ? mResults.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder = null;
        if (convertView == null) {
            holder = new MyHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_order, null);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        return convertView;
    }

    public class MyHolder {
        private TextView group, school, coach, payCount, payType, address;
    }
}
