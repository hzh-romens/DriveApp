package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.cell.TextViewWithIcon;
import com.example.ausu.erpapp.model.StudyBean;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by Lanxumit on 2016/7/28.
 */
public class StudyScheduleAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private Map<Integer, StudyBean> mResult;

    public StudyScheduleAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(Map<Integer, StudyBean> result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mResult != null ? mResult.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        StudyBean studyBean = mResult.get(groupPosition);
        return studyBean.getItems() != null ? studyBean.getItems().size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mResult.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextViewWithIcon(mContext);
        }
        TextViewWithIcon cell = (TextViewWithIcon) convertView;
        cell.setValue(mResult.get(groupPosition).getTypeName(), "", false, false, false);
        cell.setBackGroundColor(R.color.theme_bgc_grey);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        MyHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_study, null);
            holder = new MyHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.completeBtn = (TextView) convertView.findViewById(R.id.completeBtn);
            holder.completeIcon = (ImageView) convertView.findViewById(R.id.completeIcon);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        StudyBean.ItemBean itemBean = mResult.get(groupPosition).getItems().get(childPosition);
        if (itemBean.isComplete()) {
            holder.completeBtn.setVisibility(View.GONE);
            holder.completeIcon.setVisibility(View.VISIBLE);
        } else {
            holder.completeBtn.setVisibility(View.VISIBLE);
            holder.completeIcon.setVisibility(View.GONE);
        }
        holder.completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击完成
            }
        });

        return convertView;
    }

    public static class MyHolder {
        private TextView name, completeBtn;
        private ImageView completeIcon;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
