package com.example.ausu.erpapp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.utils.UIUtils;
import com.mikepenz.iconics.utils.Utils;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/23.
 */
public class ListPopWindow {
    private popWindowItemListener mListener;

    public interface popWindowItemListener {
        void popWindowItemClick(int position);
    }

    public void setPopWindowItemListener(popWindowItemListener listener) {
        this.mListener = listener;
    }

    private PopupWindow popupWindow;

    public void showPopWindow(Activity context, final List<String> items, View targetView) {
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View popWindow = inflater.inflate(R.layout.layout_popwindow_list, null);
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
         //   popupWindow.set
            popupWindow.setAnimationStyle(R.style.popwindowStyle);
            popupWindow.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    popupWindow.dismiss();
                    if (mListener != null) {
                        mListener.popWindowItemClick(position);
                    }
                }
            });
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
}
