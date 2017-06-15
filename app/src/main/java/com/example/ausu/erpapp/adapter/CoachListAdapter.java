package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.model.CoachInfoBean;
import com.example.ausu.erpapp.view.CloudImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/31.
 */
public class CoachListAdapter extends BaseAdapter {
    private List<CoachInfoBean> mResults = new ArrayList<CoachInfoBean>();
    private Context mContext;

    public void bindData(List<CoachInfoBean> results) {
        if (mResults != null && mResults.size() != 0) {
            mResults.clear();
        }
        this.mResults = results;
        notifyDataSetChanged();
    }

    public void addData(List<CoachInfoBean> results) {
        if (results != null) {
            mResults.addAll(results);
        }
        notifyDataSetChanged();
    }

    public CoachListAdapter(Context context) {
        this.mContext = context;
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
        MyHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_item_coach, null);
            holder = new MyHolder();
            holder.avatar = (CloudImageView) convertView.findViewById(R.id.avatar_coach);
            holder.coach_name = (TextView) convertView.findViewById(R.id.coach_name);
            holder.coach_year = (TextView) convertView.findViewById(R.id.coach_year);
            holder.day = (TextView) convertView.findViewById(R.id.day);
            holder.rate = (TextView) convertView.findViewById(R.id.rate);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        holder.avatar.setImagePath(mResults.get(position).getAvatar());
        holder.coach_name.setText(mResults.get(position).getUsername());
        holder.coach_year.setText(mResults.get(position).getTeachage() + "");
        holder.day.setText(mResults.get(position).getPassratio());
        holder.rate.setText(mResults.get(position).getAvgachieveperiod() + "");
        return convertView;
    }

    public class MyHolder {
        private CloudImageView avatar;
        private TextView coach_name, coach_year, day, rate;
    }
}
