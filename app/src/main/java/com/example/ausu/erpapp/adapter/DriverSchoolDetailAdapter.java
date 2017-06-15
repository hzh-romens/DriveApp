package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ScrollView;

import com.example.ausu.erpapp.PackagesActivity;
import com.example.ausu.erpapp.cell.ClassCell;
import com.example.ausu.erpapp.cell.HorizontalImageCell;
import com.example.ausu.erpapp.cell.SchoolDetailCell;
import com.example.ausu.erpapp.cell.ShadowSectionCell;
import com.example.ausu.erpapp.cell.TextViewWithIcon;
import com.example.ausu.erpapp.model.DriverClassBean;
import com.example.ausu.erpapp.model.DriverSchoolBean;
import com.example.ausu.erpapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/19.
 */
public class DriverSchoolDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<Integer> mTypes;
    private DriverSchoolBean mDriverSchoolBean;
    private List<DriverClassBean> mDriverClassList;
    private String[] mImageList = new String[4];
    private List<Object> mResult;

    public DriverSchoolDetailAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(DriverSchoolBean driverSchoolBean, List<DriverClassBean> driverClassList, String[] imageList, List<Integer> types, List<Object> result) {
        this.mDriverSchoolBean = driverSchoolBean;
        this.mDriverClassList = driverClassList;
        this.mTypes = types;
        this.mImageList = imageList;
        this.mResult = result;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTypes != null ? mTypes.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypes.get(position) == 0) {
            return 0;
        } else if (mTypes.get(position) == 1) {
            return 1;
        } else if (mTypes.get(position) == 2) {
            return 2;
        } else if (mTypes.get(position) == 3) {
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 5;
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
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            if (convertView == null) {
                convertView = new SchoolDetailCell(mContext);
            }
            SchoolDetailCell cell = (SchoolDetailCell) convertView;
        } else if (itemViewType == 1) {
            if (convertView == null) {
                convertView = new ShadowSectionCell(mContext);
            }

        } else if (itemViewType == 2) {
            if (convertView == null) {
                convertView = new TextViewWithIcon(mContext);
            }
            TextViewWithIcon cell = (TextViewWithIcon) convertView;
            cell.setValue(mResult.get(position).toString(), "", true, false, false);
        } else if (itemViewType == 3) {
            if (convertView == null) {
                convertView = new ClassCell(mContext);
            }
            ClassCell classCell = (ClassCell) convertView;
            classCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PackagesActivity.class);
                    intent.putExtra("classId", "20");
                    mContext.startActivity(intent);
                }
            });
        } else {
            ///展示图片的工具类
            if (convertView == null) {
                convertView = new HorizontalImageCell(mContext);
            }
            HorizontalImageCell cell = (HorizontalImageCell) convertView;
            cell.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, UIUtils.dip2px(60)));
            List<String> urls = new ArrayList<String>();
            String url = "http://b.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=72153cdbf536afc30e5937638329c7fc/4034970a304e251fe49095e3a586c9177f3e5341.jpg";
            urls.add(url);
            urls.add(url);
            urls.add(url);
            cell.setValue(urls);
        }

        return convertView;
    }
}
