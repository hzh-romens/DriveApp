package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ausu.erpapp.cell.CommentBodyCell;
import com.example.ausu.erpapp.cell.CommentHeadCell;
import com.example.ausu.erpapp.cell.CommentPostCell;
import com.example.ausu.erpapp.cell.ShadowSectionCell;
import com.example.ausu.erpapp.cell.TextViewWithIcon;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/6.
 */
public class ContentBodyAdapter extends BaseAdapter {
    private List<Object> mResult;
    private Context mContext;
    private SparseArray mTypes;

    public ContentBodyAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Object> result, SparseArray types) {
        this.mResult = result;
        this.mTypes = types;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTypes != null ? mTypes.size() : 0;
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
    public int getItemViewType(int position) {
        int i = Integer.parseInt(mTypes.get(position).toString());
        if (i == 1) {
            return 1;
        } else if (i == 2) {
            return 2;

        } else if (i == 3) {
            return 3;
        } else if (i == 4) {
            return 4;
        } else {
            return 5;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1) {
            if (convertView == null) {
                convertView = new CommentHeadCell(mContext);
            }
            CommentHeadCell cell = (CommentHeadCell) convertView;
        } else if (itemViewType == 2) {
            if (convertView == null) {
                convertView = new CommentBodyCell(mContext);
            }
            CommentBodyCell cell = (CommentBodyCell) convertView;
            // List
            //cell.setImageList();
        } else if (itemViewType == 3) {
            if (convertView == null) {
                convertView = new ShadowSectionCell(mContext);
            }
            ShadowSectionCell cell = (ShadowSectionCell) convertView;
        } else if (itemViewType == 4) {
            if (convertView == null) {
                convertView = new TextViewWithIcon(mContext);
            }
            TextViewWithIcon cell = (TextViewWithIcon) convertView;
            cell.setValue(mResult.get(position).toString(), "", false, false, false);
        } else {
            if (convertView == null) {
                convertView = new CommentPostCell(mContext);
            }
            CommentPostCell cell = (CommentPostCell) convertView;
        }
        return convertView;
    }
}
