package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.model.PracticeItemBean;

import java.util.List;

/**
 * Created by Lanxumit on 2016/7/27.
 */
public class PracticeAdapter extends BaseAdapter {
    private List<PracticeItemBean> mResult;
    private Context mContext;
    private SparseBooleanArray mAnswers;

    public PracticeAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<PracticeItemBean> result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    public void setAnswers(SparseBooleanArray answers) {
        this.mAnswers = answers;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mResult != null ? mResult.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_pratice, null);
            holder = new MyHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        if (mAnswers.size() >= 2) {
            holder.checkBox.setClickable(false);
            Toast.makeText(mContext, "最多只能选取两个项目", Toast.LENGTH_SHORT).show();
        } else {
            holder.checkBox.setClickable(true);
        }
        if (mAnswers.get(position)) {
            holder.checkBox.setClickable(true);
        }
        //else {
        //  holder.checkBox.setClickable(false);
        // }
        holder.checkBox.setText(mResult.get(position).getCoursename());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListener.onCheckChange(position, isChecked);
            }
        });
        return convertView;
    }

    public static class MyHolder {
        private CheckBox checkBox;
    }

    public interface onCheckListener {
        void onCheckChange(int position, boolean isChecked);
    }

    private onCheckListener mListener;

    public void setOnCheckListener(onCheckListener listener) {
        this.mListener = listener;
    }
}
