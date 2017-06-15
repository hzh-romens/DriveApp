package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.model.ExamBean;

import java.util.List;

/**
 * Created by Lanxumit on 2016/7/28.
 */
public class ExamAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExamBean> mResult;

    public ExamAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<ExamBean> result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    public void addData(List<ExamBean> result) {
        if (result != null && result.size() != 0) {
            mResult.addAll(result);
            notifyDataSetChanged();
        }
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
        MyHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_exam, null);
            holder = new MyHolder();
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.singupBtn = (TextView) convertView.findViewById(R.id.singupBtn);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        //需要将时间转换
        holder.singupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击报名
            }
        });
        return convertView;
    }

    public static class MyHolder {
        private TextView time, singupBtn;
    }
}
