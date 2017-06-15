package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.SingUpActivity;
import com.example.ausu.erpapp.model.DriverClassBean;
import com.example.ausu.erpapp.model.DriverSchoolBean;
import com.example.ausu.erpapp.model.DriverSchoolListBean;
import com.example.ausu.erpapp.view.CloudImageView;

import java.util.List;
import java.util.Map;

/**
 * Created by Lanxumit on 2016/7/21.
 */
public class MainAdapter extends BaseExpandableListAdapter {
    private List<DriverSchoolListBean> mGroupData;
    private Map<String, List<DriverClassBean>> mChildData;
    private Context mContext;

    public MainAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(List<DriverSchoolListBean> groupData, Map<String, List<DriverClassBean>> childData) {
        if (mGroupData != null && mGroupData.size() != 0) {
            mGroupData.clear();
        }
        if (mChildData != null && mChildData.size() != 0) {
            mChildData.clear();
        }
        this.mGroupData = groupData;
        this.mChildData = childData;
        notifyDataSetChanged();
    }

    public void addData(List<DriverSchoolListBean> groupData, Map<String, List<DriverClassBean>> childData) {
        mGroupData.addAll(groupData);
        mChildData.putAll(childData);
        notifyDataSetChanged();
    }

    @Override

    public int getGroupCount() {
        return mGroupData != null ? mGroupData.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int driverSchoolId = mGroupData.get(groupPosition).getId();
        return mChildData.get(driverSchoolId + "") != null ? mChildData.get(driverSchoolId + "").size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        DriverSchoolListBean driverSchoolListBean = mGroupData.get(groupPosition);
        return mChildData.get(driverSchoolListBean.getId() + "").get(childPosition);
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
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_group, null);
            groupHolder = new GroupHolder();
            groupHolder.cloudImageView = (CloudImageView) convertView.findViewById(R.id.carIcon);
            groupHolder.schoolName = (TextView) convertView.findViewById(R.id.schoolName);
            groupHolder.adress = (TextView) convertView.findViewById(R.id.adress);
            groupHolder.distance = (TextView) convertView.findViewById(R.id.distance);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        DriverSchoolListBean driverSchoolBean = mGroupData.get(groupPosition);
        groupHolder.cloudImageView.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
        groupHolder.cloudImageView.setImagePath(driverSchoolBean.getAvatar());
        groupHolder.distance.setText(driverSchoolBean.getDistance() + "km");
        groupHolder.schoolName.setText(driverSchoolBean.getSchoolname());
        groupHolder.adress.setText(driverSchoolBean.getBizadress());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_child, null);
            childHolder = new ChildHolder();
            childHolder.className = (TextView) convertView.findViewById(R.id.className);
            childHolder.detail = (TextView) convertView.findViewById(R.id.detail);
            childHolder.enroll = (TextView) convertView.findViewById(R.id.enroll);
            childHolder.enrollNumber = (TextView) convertView.findViewById(R.id.enroll_number);
            childHolder.price = (TextView) convertView.findViewById(R.id.price);
            childHolder.getMore = (TextView) convertView.findViewById(R.id.getMore);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        DriverSchoolListBean driverSchoolListBean = mGroupData.get(groupPosition);
        final DriverClassBean driverClassBean = mChildData.get(driverSchoolListBean.getId() + "").get(childPosition);

        SpannableString spannableString = new SpannableString("￥" + driverClassBean.getDiscountprice() + "   市场价 ￥" + driverClassBean.getProductprice());
        ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.themeColor));
        //将这个Span应用于指定范围的字体
        spannableString.setSpan(span, 0, String.valueOf(driverClassBean.getDiscountprice()).length() + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ForegroundColorSpan span1 = new ForegroundColorSpan(mContext.getResources().getColor(R.color.light_black));
        spannableString.setSpan(span1, String.valueOf(driverClassBean.getDiscountprice()).length() + 1, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(10, true);
        spannableString.setSpan(absoluteSizeSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        AbsoluteSizeSpan absoluteSizeSpan1 = new AbsoluteSizeSpan(16, true);
        spannableString.setSpan(absoluteSizeSpan1, 1, String.valueOf(driverClassBean.getDiscountprice()).length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //  discount.setText(driverClassBean.getProductprice() + "");
        childHolder.price.setText(spannableString);
        // childHolder.price.setText(driverClassBean.getProductprice() + "");
        childHolder.enrollNumber.setText("已报名" + driverClassBean.getEnrollment() + "人");
        childHolder.detail.setText(driverClassBean.getProductsummary());
        childHolder.className.setText(driverClassBean.getProductname());
        childHolder.enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingUpActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DriverClass", driverClassBean);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        if (isLastChild) {
            childHolder.getMore.setVisibility(View.VISIBLE);
        } else {
            childHolder.getMore.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupHolder {
        private CloudImageView cloudImageView;
        private TextView schoolName, adress, distance;

    }

    class ChildHolder {
        private TextView className, detail, price, enroll, getMore, enrollNumber;
    }
}
