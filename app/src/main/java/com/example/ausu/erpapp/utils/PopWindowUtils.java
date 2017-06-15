package com.example.ausu.erpapp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.date.WheelPicker;

import java.util.List;

/**
 * Created by Lanxumit on 2016/5/18.
 */
public class PopWindowUtils {
    private Context mContext;

    private popWindowItemListener mListener;

    public interface popWindowItemListener {
        void popWindowItemClick(int position);
    }

    public void setPopWindowItemListener(popWindowItemListener listener) {
        this.mListener = listener;
    }

    private PopupWindow popupWindow;

    public void showPopWindow(Activity context, final List<String> items, View targetView) {
        mContext = context;
        if (popupWindow != null) {
            popupWindow.dismiss();
            backgroundAlpha(1f);
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View popWindow = inflater.inflate(R.layout.user_layout_popwindow, null);
            popupWindow = new PopupWindow(popWindow, UIUtils.getScreenWidth() - 100, FrameLayout.LayoutParams.WRAP_CONTENT);
            final ListView listView = (ListView) popWindow.findViewById(R.id.listview);
            listView.setDividerHeight(0);
            listView.setDivider(null);
            ItemAdapter itemAdapter = new ItemAdapter(context);
            itemAdapter.bindItem(items);
            listView.setAdapter(itemAdapter);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            ColorDrawable drawable = new ColorDrawable(context.getResources().getColor(R.color.transparents));
            popupWindow.setBackgroundDrawable(drawable);
            popupWindow.setAnimationStyle(R.style.popwindowStyle);
            popupWindow.showAtLocation(targetView, Gravity.BOTTOM, 0, UIUtils.dip2px(48));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    popupWindow.dismiss();
                    if (mListener != null) {
                        mListener.popWindowItemClick(position);
                        popupWindow.dismiss();
                        backgroundAlpha(1f);
                    }
                }
            });
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
            backgroundAlpha(0.5f);
        }
    }

    private class ItemAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> mItems;

        public ItemAdapter(Context context) {
            this.mContext = context;
        }

        public void bindItem(List<String> items) {
            this.mItems = items;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mItems != null ? mItems.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_popwindow, null);
            TextView funcition = (TextView) convertView.findViewById(R.id.functionBtn);
            funcition.setText(mItems.get(position));
            return convertView;
        }
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    //弹出预约时间选择
    public void showAppointTime(Activity context, final List<String> weeks, final List<String> hours, View targetView) {
        mContext = context;
        if (popupWindow != null) {
            popupWindow.dismiss();
            backgroundAlpha(1f);
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View popWindow = inflater.inflate(R.layout.layout_appoint_time, null);
            popupWindow = new PopupWindow(popWindow, UIUtils.getScreenWidth(), FrameLayout.LayoutParams.WRAP_CONTENT);
            final WheelPicker weekPicker = (WheelPicker) popWindow.findViewById(R.id.week);
            final WheelPicker hourPicker = (WheelPicker) popWindow.findViewById(R.id.hour);
            TextView cancel = (TextView) popWindow.findViewById(R.id.cancel);
            TextView sure = (TextView) popWindow.findViewById(R.id.sure);
            weekPicker.setData(weeks);
            hourPicker.setData(hours);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            ColorDrawable drawable = new ColorDrawable(context.getResources().getColor(R.color.transparents));
            popupWindow.setBackgroundDrawable(drawable);
            popupWindow.setAnimationStyle(R.style.popwindowStyle);
            popupWindow.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            });
            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String appointTime = weeks.get(weekPicker.getCurrentItemPosition()) + hours.get(hourPicker.getCurrentItemPosition());
                    mTimeCallBackListener.timeCallBack(appointTime);
                    popupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            });

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
            backgroundAlpha(0.5f);
        }
    }

    public interface TimeCallBackListener {
        void timeCallBack(String time);
    }

    private TimeCallBackListener mTimeCallBackListener;

    public void setTimeCallBackListener(TimeCallBackListener listener) {
        this.mTimeCallBackListener = listener;
    }

}
