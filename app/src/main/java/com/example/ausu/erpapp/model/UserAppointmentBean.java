package com.example.ausu.erpapp.model;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class UserAppointmentBean {
    private int type;
    private String appointmentTime;
    private String item;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
