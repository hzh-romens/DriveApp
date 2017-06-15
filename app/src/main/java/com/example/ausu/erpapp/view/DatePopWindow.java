package com.example.ausu.erpapp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.date.WheelDayPicker;
import com.example.ausu.erpapp.date.WheelMonthPicker;
import com.example.ausu.erpapp.date.WheelYearPicker;

/**
 * Created by Lanxumit on 2016/8/23.
 * 日期选择器，界面需要微调
 */
public class DatePopWindow implements View.OnClickListener {
    private PopupWindow popupWindow;
    private Context mContext;
    private WheelMonthPicker wheelMonth;
    private WheelYearPicker wheelYear;
    private WheelDayPicker wheelDay;
    private TextView cancel, sure;

    public void showDatePopWindow(final Activity context, final View targetView) {
        if (popupWindow != null) {
            popupWindow.dismiss();
            backgroundAlpha(1f);
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            this.mContext = context;
            View popWindow = inflater.inflate(R.layout.layout_popwindow_date, null);
            setUpView(popWindow);
            popupWindow = new PopupWindow(popWindow, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            ColorDrawable drawable = new ColorDrawable(Color.WHITE);
            popupWindow.setBackgroundDrawable(drawable);
            popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            popupWindow.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
            backgroundAlpha(0.5f);
        }

    }

    private void setUpView(View popWindow) {
        cancel = (TextView) popWindow.findViewById(R.id.cancel);
        sure = (TextView) popWindow.findViewById(R.id.sure);
        wheelYear = (WheelYearPicker) popWindow.findViewById(R.id.main_wheel_left);
        wheelYear.setYearFrame(1900, 2100);
        wheelMonth = (WheelMonthPicker) popWindow.findViewById(R.id.main_wheel_center);
        wheelDay = (WheelDayPicker) popWindow.findViewById(R.id.main_wheel_right);
        cancel.setOnClickListener(this);
        sure.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                popupWindow.dismiss();
                backgroundAlpha(1f);
                break;
            case R.id.sure:
                //将选择的日趋回调回去
                String date = wheelYear.getCurrentYear() + "年" + wheelMonth.getCurrentMonth() + "月" + wheelDay.getCurrentDay() + "日";
                mListener.DateCallback(date);
                Toast.makeText(mContext, date, Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                backgroundAlpha(1f);
                break;
        }
    }

    public interface DateCallbackListener {
        void DateCallback(String time);
    }

    private DateCallbackListener mListener;

    public void setDateCallbackListener(DateCallbackListener listener) {
        this.mListener = listener;
    }
}
