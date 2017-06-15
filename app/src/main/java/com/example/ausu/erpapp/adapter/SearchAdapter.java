package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ausu.erpapp.cell.ListItemCell;
import com.example.ausu.erpapp.cell.SearchCell;
import com.example.ausu.erpapp.model.SearchBean;

import java.util.Collection;
import java.util.List;

/**
 * Created by AUSU on 2016/5/23.
 */
public class SearchAdapter extends BaseAdapter {
    private Context mContext;
    private List<SearchBean> mResult;

    public SearchAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(List<SearchBean> result) {
        this.mResult = result;
        notifyDataSetChanged();
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new SearchCell(mContext);
        }
        SearchCell cell = (SearchCell) convertView;
        cell.setValue(mResult.get(position).getSchoolName());
        return convertView;
    }
}
