package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.model.UserAppointmentBean;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class AppointmentAdapter extends BaseAdapter {
    private List<UserAppointmentBean> mResults;
    private Context mContext;

    public AppointmentAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<UserAppointmentBean> results) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_userappointment, null);
            holder.appointStatus = (TextView) convertView.findViewById(R.id.appointStatus);
            holder.appointTime = (TextView) convertView.findViewById(R.id.appointmentTime);
            holder.appointItem = (TextView) convertView.findViewById(R.id.appointmentItem);
            holder.button1 = (TextView) convertView.findViewById(R.id.button1);
            holder.button2 = (TextView) convertView.findViewById(R.id.button2);
            convertView.setTag(convertView);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        //设置值
        return convertView;
    }

    public class MyHolder {
        private TextView appointStatus, appointTime, appointItem, button1, button2;
    }

}
