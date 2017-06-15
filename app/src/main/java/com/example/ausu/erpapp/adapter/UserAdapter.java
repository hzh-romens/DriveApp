package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ausu.erpapp.OrderDetailActivity;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.UserAppointmentActivity;
import com.example.ausu.erpapp.cell.FunctionCell;
import com.example.ausu.erpapp.cell.ShadowSectionCell;
import com.example.ausu.erpapp.cell.TextViewWithIcon;
import com.example.ausu.erpapp.cell.UserAppointmentCell;
import com.example.ausu.erpapp.cell.UserSettingCell;
import com.example.ausu.erpapp.model.FunctionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class UserAdapter extends BaseAdapter {
    private Context mContext;
    private SparseArray mResults;
    private SparseArray mTypes;

    public UserAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(SparseArray results, SparseArray types) {
        this.mResults = results;
        this.mTypes = types;

    }

    public void notifyData(SparseArray results) {
        this.mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTypes != null ? mTypes.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        int i = Integer.parseInt(mTypes.get(position).toString());
        if (i== 1) {
            return 1;
        } else if (i == 2) {
            return 2;
        } else if (i == 3 || i == 5) {
            return 3;
        } else if (i == 4) {
            return 4;
        } else {
            return 5;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1) {
            if (convertView == null) {
                convertView = new UserSettingCell(mContext);
            }
            UserSettingCell cell = (UserSettingCell) convertView;
            cell.setValue((Bitmap) mResults.get(1));
            cell.setHeadClickListener(new UserSettingCell.headClick() {
                @Override
                public void headclickListener() {
                    mListener.headClick();
                }
            });
        } else if (itemViewType == 2) {
            if (convertView == null) {
                convertView = new FunctionCell(mContext);
            }
            FunctionCell cell = (FunctionCell) convertView;
            List<FunctionBean> result = new ArrayList<FunctionBean>();
            FunctionBean functionBean = new FunctionBean();
            functionBean.setName("待评价");
            functionBean.setDresId(R.mipmap.ic_user_comment);
            result.add(functionBean);
            FunctionBean functionBean1 = new FunctionBean();
            functionBean1.setName("全部预约");
            functionBean1.setDresId(R.mipmap.ic_user_appointment);
            result.add(functionBean1);
            FunctionBean functionBean2 = new FunctionBean();
            functionBean2.setName("订单信息");
            functionBean2.setDresId(R.mipmap.ic_user_order);
            result.add(functionBean2);
            cell.initData(result);
            cell.setOnItemClickListener(new FunctionCell.OnItemClickListener() {
                @Override
                public void OnItemClick(String name) {
                    if ("全部预约".equals(name)) {
                        mContext.startActivity(new Intent(mContext, UserAppointmentActivity.class));
                    } else if ("订单信息".equals(name)) {
                        mContext.startActivity(new Intent(mContext, OrderDetailActivity.class));
                    }
                }
            });
        } else if (itemViewType == 3) {
            if (convertView == null) {
                convertView = new ShadowSectionCell(mContext);
            }
            ShadowSectionCell cell = (ShadowSectionCell) convertView;
        } else if (itemViewType == 4) {
            if (convertView == null) {
                convertView = new UserAppointmentCell(mContext);
            }
            UserAppointmentCell cell = (UserAppointmentCell) convertView;
        } else {
            if (convertView == null) {
                convertView = new TextViewWithIcon(mContext);
            }
            TextViewWithIcon cell = (TextViewWithIcon) convertView;
            if (position == 5) {
                cell.setValue("联系客服", "", true, true, false);
                cell.setTextNameDrawableLeft(true, R.mipmap.ic_user_contact);
            } else if (position == 6) {
                cell.setValue("分享APP", "", true, true, false);
                cell.setTextNameDrawableLeft(true, R.mipmap.ic_user_share);
            }
        }
        return convertView;
    }

    public interface HeadClickListener {
        void headClick();
    }

    private HeadClickListener mListener;

    public void setHeadClickListener(HeadClickListener listener) {
        this.mListener = listener;
    }
}
