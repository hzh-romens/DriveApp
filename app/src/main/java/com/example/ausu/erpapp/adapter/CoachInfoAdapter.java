package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.cell.ClassCell;
import com.example.ausu.erpapp.cell.CoachAddressCell;
import com.example.ausu.erpapp.cell.CoachCell;
import com.example.ausu.erpapp.cell.ContentCell;
import com.example.ausu.erpapp.cell.ShadowSectionCell;
import com.example.ausu.erpapp.cell.TextViewWithIcon;
import com.example.ausu.erpapp.model.CoachInfoBean;
import com.example.ausu.erpapp.model.CommentBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lanxumit on 2016/7/25.
 */
public class CoachInfoAdapter extends BaseAdapter {
    private Context mContext;
    private HashMap<Integer, Object> mResult;
    //班级数量的长度
    private int mClassSize;
    private List<Integer> mTypes;


    public void bindTypes(List<Integer> types) {
        this.mTypes = types;
        notifyDataSetChanged();
    }

    public CoachInfoAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(HashMap<Integer, Object> result, int classSize) {
        if (mResult != null && mResult.size() != 0) {
            mResult.clear();
        }
        this.mResult = result;
        this.mClassSize = classSize;
        notifyDataSetChanged();
    }

    public void addData(HashMap<Integer, Object> result, int classSize) {
        if (result != null && result.size() != 0) {
            mResult.putAll(result);
        }
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
        int i = mTypes.get(position).intValue();
        if (i == 0) {
            //教练信息
            return 0;
        } else if (i == 1 || i == 3 || i == 7) {
            return 1;
        } else if (i == 2) {
            return 2;
        } else if (i == 4 || i == 6 + mClassSize) {
            return 3;
        } else if (i > 6 + mClassSize) {
            return 5;
        } else {
            return 4;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            if (convertView == null) {
                convertView = new CoachCell(mContext);
            }
            CoachInfoBean coachInfo = (CoachInfoBean) mResult.get(position);
            CoachCell coachCell = (CoachCell) convertView;
            coachCell.setValue(coachInfo.getAvatar(), coachInfo.getUsername(), coachInfo.getTeachage(), coachInfo.getTeachage(), coachInfo.getPassratio(), coachInfo.getTeachage() + "", coachInfo.getAvgachieveperiod() + "", coachInfo.getGender());
        } else if (itemViewType == 1) {
            if (convertView == null) {
                convertView = new ShadowSectionCell(mContext);
            }
            ShadowSectionCell cell = (ShadowSectionCell) convertView;
            cell.setBackgroundColor(mContext.getResources().getColor(R.color.theme_bgc_grey));
        } else if (itemViewType == 2) {
            if (convertView == null) {
                convertView = new CoachAddressCell(mContext);
            }
            CoachAddressCell cell = (CoachAddressCell) convertView;
        } else if (itemViewType == 3) {
            Object o = mResult.get(position);
            if (convertView == null) {
                convertView = new TextViewWithIcon(mContext);
            }
            TextViewWithIcon cell = (TextViewWithIcon) convertView;
            if (position == 8) {
                String value = o.toString();
                cell.setValue(value.substring(0, 4), value.substring(4) + "条评价", true, false, false);
            } else {
                cell.setValue(o.toString(), "", true, false, false);
            }
            cell.setTextColor(R.color.theme_black, R.color.theme_light_black);
        } else if (itemViewType == 4) {
            if (convertView == null) {
                convertView = new ClassCell(mContext);
            }
            ClassCell cell = (ClassCell) convertView;
        } else if (itemViewType == 5) {
            CommentBean commentBean = (CommentBean) mResult.get(position);
            if (convertView == null) {
                convertView = new ContentCell(mContext);

            }
            ContentCell cell = (ContentCell) convertView;
            cell.setValue(commentBean.getAvatar(), commentBean.getStudentname(), commentBean.getTime(), commentBean.getEvaluateContent(), commentBean.getStars());
        }
        return convertView;
    }
}
