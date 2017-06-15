package com.example.ausu.erpapp.cell;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.model.AppointmentBean;
import com.example.ausu.erpapp.model.ProcessBean;
import com.example.ausu.erpapp.utils.DateUtils;
import com.example.ausu.erpapp.utils.PopWindowUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/30.
 */
public class AppointmentCell extends FrameLayout {
    private TextView adress, appointmentBtn, time, item, hour, count, day;
    private FrameLayout appointmentTime, appointmentItem;

    public AppointmentCell(Context context) {
        super(context);
        initView(context);
    }

    public AppointmentCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AppointmentCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        final View view = View.inflate(context, R.layout.list_item_appointment, null);
        adress = (TextView) view.findViewById(R.id.adress);
        time = (TextView) view.findViewById(R.id.time);
        item = (TextView) view.findViewById(R.id.item);
        hour = (TextView) view.findViewById(R.id.hour);
        day = (TextView) view.findViewById(R.id.day);
        count = (TextView) view.findViewById(R.id.count);
        appointmentBtn = (TextView) view.findViewById(R.id.appointmentBtn);
        appointmentItem = (FrameLayout) view.findViewById(R.id.appointmentItem);
        appointmentTime = (FrameLayout) view.findViewById(R.id.appointmentTime);
        adress.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        appointmentBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.AppointmentBtnCallback();
            }
        });
        item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.AppointmentItemCallback("");
            }
        });
        time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List nextServenDays = DateUtils.getNextServenDays();
                List<String> times = new ArrayList<String>();
                times.add("08:00-12:00");
                times.add("13:00:17:00");
                PopWindowUtils popWindowUtils = new PopWindowUtils();
                popWindowUtils.showAppointTime((Activity) context, nextServenDays, times, view);
                popWindowUtils.setTimeCallBackListener(new PopWindowUtils.TimeCallBackListener() {
                    @Override
                    public void timeCallBack(String time) {
                        mListener.AppointmentTimeCallback(time, "");
                    }
                });
            }
        });
        addView(view);
    }


    public void setValue(ProcessBean processBean) {
        adress.setText(processBean.getSchool().getBizAdress());
        day.setText("历时" + processBean.getContinuedmonth() + "个月" + processBean.getContinuedday() + "天");
        count.setText("预约" + processBean.getOrdercount() + "次");
        hour.setText("您已累计练习" + processBean.getStudyhours() + "学时");
    }

    public void setAppointValue(AppointmentBean appointValue) {
        time.setText(appointValue.getAppointmentTime());
        item.setText(appointValue.getProjrcts());
    }

    public interface UpdateListener {
        void Navigation();

        void AppointmentTimeCallback(String day, String time);

        void AppointmentItemCallback(String item);

        void AppointmentBtnCallback();
    }

    private UpdateListener mListener;

    public void setUpdateListener(UpdateListener listener) {
        this.mListener = listener;
    }
}
