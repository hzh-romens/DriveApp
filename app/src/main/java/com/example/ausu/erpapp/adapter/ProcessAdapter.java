package com.example.ausu.erpapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.example.ausu.erpapp.FunctionActivity;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.SelfActivity;
import com.example.ausu.erpapp.cell.AppointmentCell;
import com.example.ausu.erpapp.cell.FunctionCell;
import com.example.ausu.erpapp.cell.ImageCell;
import com.example.ausu.erpapp.cell.ShadowSectionCell;
import com.example.ausu.erpapp.cell.TitleCell;
import com.example.ausu.erpapp.cell.UnloginCell;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.AppointmentBean;
import com.example.ausu.erpapp.model.FunctionBean;
import com.example.ausu.erpapp.model.ProcessBean;
import com.example.ausu.erpapp.utils.DateUtils;
import com.example.ausu.erpapp.utils.NetUtils;
import com.example.ausu.erpapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/26.
 */
public class ProcessAdapter extends BaseAdapter {
    private SparseIntArray mTypes;
    private Context mContext;
    private ProcessBean mProcessBean;
    private AppointmentBean mAppointmentBean;

    public ProcessAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(ProcessBean processBean, SparseIntArray types) {
        this.mTypes = types;
        this.mProcessBean = processBean;
        notifyDataSetChanged();
    }

    public void notifyData(AppointmentBean appointmentBean) {
        this.mAppointmentBean = appointmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTypes != null ? mTypes.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypes.get(position) == -1) {
            return -1;
        } else if (mTypes.get(position) == 0) {
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
        return 6;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == -1) {
            if (convertView == null) {
                convertView = new UnloginCell(mContext);
                Rect frame = new Rect();
                ((Activity) mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;
                convertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.getScreenHeight() - statusBarHeight - UIUtils.dip2px(48) * 2));
            }
            UnloginCell cell = (UnloginCell) convertView;
        } else if (itemViewType == 0) {
            if (convertView == null) {
                convertView = new TitleCell(mContext);
                convertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(40)));
            }
            TitleCell cell = (TitleCell) convertView;
        } else if (itemViewType == 1) {
            //中间的图片点击部分
            if (convertView == null) {
                convertView = new ImageCell(mContext);
            }
            ImageCell cell = (ImageCell) convertView;
        } else if (itemViewType == 2) {
            //中间的预约时间选择界面
            if (convertView == null) {
                convertView = new AppointmentCell(mContext);
            }
            final AppointmentCell cell = (AppointmentCell) convertView;
            cell.setValue(mProcessBean);
            if (mAppointmentBean != null) {
                cell.setAppointValue(mAppointmentBean);
            }
            cell.setUpdateListener(new AppointmentCell.UpdateListener() {
                @Override
                public void Navigation() {

                }

                @Override
                public void AppointmentTimeCallback(String day, String time) {
                    mAppointClickListener.TimeClickCallBack(day);
                }

                @Override
                public void AppointmentItemCallback(String item) {
                    mAppointClickListener.ItemClickCallBack();
                }

                @Override
                public void AppointmentBtnCallback() {
                    //提交预约
                    String appointmentTime = mAppointmentBean.getAppointmentTime();
                    DateUtils.getAppointTime(appointmentTime);
                    NetUtils netUtils = new NetUtils();
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("periodbegein", "");
                    hashMap.put("periodend", "");
                    hashMap.put("studyitems", "");
                    hashMap.put("teacherid", "");
                    netUtils.connectServer(hashMap, Config.BASE_URL + "/study/subscribe");
                    netUtils.setCallBackListener(new NetUtils.CallBackListener() {
                        @Override
                        public void CallBackResult(String result) {

                        }
                    });

                }
            });
        } else if (itemViewType == 3) {
            //阴影
            if (convertView == null) {
                convertView = new ShadowSectionCell(mContext);
            }
            ShadowSectionCell cell = (ShadowSectionCell) convertView;
        } else {
            //三个模块部分
            if (convertView == null) {
                convertView = new FunctionCell(mContext);
                convertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(96)));
            }
            FunctionCell cell = (FunctionCell) convertView;
            List<FunctionBean> result = new ArrayList<FunctionBean>();
            FunctionBean functionBean = new FunctionBean();
            functionBean.setName("理论自学");
            functionBean.setDresId(R.mipmap.ic_self);
            result.add(functionBean);
            FunctionBean functionBean1 = new FunctionBean();
            functionBean1.setName("进程管理");
            functionBean1.setDresId(R.mipmap.ic_schedule);
            result.add(functionBean1);
            FunctionBean functionBean2 = new FunctionBean();
            functionBean2.setName("预约直考");
            functionBean2.setDresId(R.mipmap.ic_appointment);
            result.add(functionBean2);
            cell.initData(result);
            cell.setOnItemClickListener(new FunctionCell.OnItemClickListener() {
                @Override
                public void OnItemClick(String name) {
                    if ("理论自学".equals(name)) {
                        mContext.startActivity(new Intent(mContext, SelfActivity.class));
                    } else if ("进程管理".equals(name)) {
                        Intent intent = new Intent(mContext, FunctionActivity.class);
                        intent.putExtra("title", "进程管理");
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, FunctionActivity.class);
                        intent.putExtra("title", "预约直考");
                        mContext.startActivity(intent);
                    }
                }
            });
        }
        return convertView;
    }


    private AppointClickListener mAppointClickListener;

    public interface AppointClickListener {
        void ItemClickCallBack();

        void TimeClickCallBack(String time);
    }

    public void setAppointClickListener(AppointClickListener appointClickListener) {
        this.mAppointClickListener = appointClickListener;
    }
}
