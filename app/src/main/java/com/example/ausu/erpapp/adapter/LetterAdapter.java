package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.model.LetterBean;

import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * Created by Lanxumit on 2016/7/28.
 */
public class LetterAdapter extends BaseAdapter {
    private Context mContext;
    private List<LetterBean> mResult;

    public LetterAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(List<LetterBean> result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    public void addData(List<LetterBean> result) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_letter, null);
            holder = new MyHolder();
            holder.letter = (TextView) convertView.findViewById(R.id.letter);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        holder.letter.setText(mResult.get(position).getLetter());
        //时间需要转换一下

        return convertView;
    }

    public static class MyHolder {
        private TextView time, letter;
    }
}
