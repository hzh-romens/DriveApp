package com.example.ausu.erpapp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.FlowLayoutCallback;
import com.example.ausu.erpapp.utils.DrawableUtils;
import com.example.ausu.erpapp.utils.UIUtils;

import java.util.List;

/**
 * Created by Lanxumit on 2016/5/18.
 * 筛选界面，需要微调
 */
public class AreaPopWindow implements View.OnClickListener {

    private PopupWindow popupWindow;
    private FlowLayout type, area;
    private TextView reset, sure;
    private Context mContext;
    private SparseBooleanArray mTypeStatus;
    private SparseBooleanArray mAreaStatus;


    public void showPopWindow(final Activity context, final View targetView, final List<String> types, final List<String> areas
            , final SparseBooleanArray typeStatus, final SparseBooleanArray areaStatus) {
        this.mContext = context;
        this.mTypeStatus = typeStatus;
        this.mAreaStatus = areaStatus;
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View popWindow = inflater.inflate(R.layout.layout_popwindow, null);
            type = (FlowLayout) popWindow.findViewById(R.id.flowLayout);
            area = (FlowLayout) popWindow.findViewById(R.id.flowLayout2);
            reset = (TextView) popWindow.findViewById(R.id.reset);
            sure = (TextView) popWindow.findViewById(R.id.sure);
            reset.setOnClickListener(this);
            sure.setOnClickListener(this);
            type.setCount(3);
            area.setCount(3);
            type.setAdapter(new FlowLayoutCallback() {
                @Override
                public void setData(SparseBooleanArray typeStatus, SparseBooleanArray areaStatus) {

                }

                @Override
                public int getCount() {
                    return types.size();
                }

                @Override
                public View getView(final int position, ViewGroup container) {
                    final TextView textView = new TextView(context);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                    textView.setText(types.get(position));
                    textView.setPadding(4, 8, 4, 8);
                    textView.setTextColor(context.getResources().getColor(R.color.theme_black));
                    textView.setGravity(Gravity.CENTER);
                    if (typeStatus.get(position)) {
                        //选中
                        textView.setTextColor(context.getResources().getColor(R.color.themeColor));
                        GradientDrawable drawable = DrawableUtils.createCheckedDrawable(Color.WHITE, R.color.themeColor, 6);
                        textView.setBackgroundDrawable(drawable);
                    } else {
                        //未选中
                        textView.setTextColor(context.getResources().getColor(R.color.theme_black));
                        GradientDrawable drawable = DrawableUtils.createCheckedDrawable(Color.WHITE, R.color.line_color, 6);
                        textView.setBackgroundDrawable(drawable);
                    }

                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.callbackType(position);
                            for (int i = 0; i < mTypeStatus.size(); i++) {
                                if (i == position) {
                                    boolean b = mTypeStatus.get(position);
                                    mTypeStatus.append(position, !b);
                                } else {
                                    mTypeStatus.append(i, false);
                                }
                            }

                            type.updateLayout();
                        }
                    });
                    return textView;
                }
            });
            type.updateLayout();
            area.setAdapter(new FlowLayoutCallback() {
                @Override
                public void setData(SparseBooleanArray typeStatus, SparseBooleanArray areaStatus) {

                }

                @Override
                public int getCount() {
                    return areas.size();
                }

                @Override
                public View getView(final int position, ViewGroup container) {
                    final TextView textView = new TextView(context);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                    textView.setPadding(4, 8, 4, 8);
                    textView.setText(areas.get(position));
                    textView.setTextColor(context.getResources().getColor(R.color.theme_black));
                    textView.setGravity(Gravity.CENTER);
                    if (mAreaStatus.get(position)) {
                        //选中
                        textView.setTextColor(context.getResources().getColor(R.color.themeColor));
                        GradientDrawable drawable = DrawableUtils.createCheckedDrawable(Color.WHITE, R.color.themeColor, 6);
                        textView.setBackgroundDrawable(drawable);
                    } else {
                        //未选中
                        textView.setTextColor(context.getResources().getColor(R.color.theme_black));
                        GradientDrawable drawable = DrawableUtils.createCheckedDrawable(Color.WHITE, R.color.line_color, 6);
                        textView.setBackgroundDrawable(drawable);
                    }


                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.callbackArea(position);
                            for (int i = 0; i < mAreaStatus.size(); i++) {
                                if (i == position) {
                                    boolean b = mAreaStatus.get(position);
                                    mAreaStatus.append(position, !b);
                                } else {
                                    mAreaStatus.append(i, false);
                                }
                            }
                            area.updateLayout();
                        }
                    });
                    return textView;
                }
            });
            int[] location = new int[2];
            targetView.getLocationOnScreen(location);
            area.updateLayout();
            popupWindow = new PopupWindow(popWindow, FrameLayout.LayoutParams.MATCH_PARENT, UIUtils.getScreenHeight() - location[1] - targetView.getHeight());
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            ColorDrawable drawable = new ColorDrawable(context.getResources().getColor(R.color.md_black_1000));
            popupWindow.setBackgroundDrawable(drawable);
            popupWindow.getBackground().setAlpha(170);
            popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            popupWindow.showAsDropDown(targetView);
        }
    }


    //刷新item的状态
    public void notifyStatus(SparseBooleanArray typeStatus, SparseBooleanArray areaStatus) {
        this.mTypeStatus = typeStatus;
        this.mAreaStatus = areaStatus;
        area.updateLayout();
        type.updateLayout();
    }


    public interface CallBackListener {
        void callbackType(int pos);

        void callbackArea(int pos);
    }

    private CallBackListener mListener;

    public void setCallBackListener(CallBackListener listener) {
        this.mListener = listener;
    }


    public interface SureCallBackListener {
        void sureCallBack();
    }

    public SureCallBackListener mSureCallBackListener;

    public void setSureCallBackListener(SureCallBackListener listener) {
        this.mSureCallBackListener = listener;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                mListener.callbackArea(-1);
                mListener.callbackType(-1);
                break;
            case R.id.sure:
                //
                int typePosition = -1;
                int areaPosition = -1;
                for (int i = 0; i < mTypeStatus.size(); i++) {
                    if (mTypeStatus.get(i)) {
                        typePosition = i;
                    }
                }

                for (int i = 0; i < mAreaStatus.size(); i++) {
                    if (mAreaStatus.get(i)) {
                        areaPosition = i;
                    }
                }
                mListener.callbackArea(areaPosition);
                mListener.callbackType(typePosition);
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                mSureCallBackListener.sureCallBack();
                break;
        }
    }
}
