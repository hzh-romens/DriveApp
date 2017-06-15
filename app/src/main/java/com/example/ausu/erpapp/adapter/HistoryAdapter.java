package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ausu.erpapp.cell.ItemMoreCell;
import com.example.ausu.erpapp.cell.SearchCell;
import com.example.ausu.erpapp.cell.TextViewWithIcon;
import com.example.ausu.erpapp.model.SearchBean;

/**
 * Created by Lanxumit on 2016/7/26.
 */
public class HistoryAdapter extends BaseAdapter {
    private Context mContext;
    private SparseArray mResult;

    public HistoryAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(SparseArray result) {
        this.mResult = result;
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
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        if (position == (mResult.size() - 1)) {
            return 2;
        }
        //  if (mResult.get(position) instanceof SearchBean) {
        return 1;
        //}

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            if (convertView == null) {
                convertView = new TextViewWithIcon(mContext);
            }
            TextViewWithIcon cell = (TextViewWithIcon) convertView;
            cell.setValue(mResult.get(position).toString(), "", true, false, false);
        } else if (itemViewType == 1) {
            if (convertView == null) {
                convertView = new SearchCell(mContext);
            }
            SearchCell cell = (SearchCell) convertView;
            cell.setValue(((SearchBean) mResult.get(position)).getSchoolName());
        } else {
            if (convertView == null) {
                convertView = new ItemMoreCell(mContext);
            }
            ItemMoreCell cell = (ItemMoreCell) convertView;
            cell.setValue(mResult.get(position).toString(), false, -1);
        }
        return convertView;
    }
}
