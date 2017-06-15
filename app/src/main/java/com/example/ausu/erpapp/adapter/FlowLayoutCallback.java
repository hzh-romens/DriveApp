package com.example.ausu.erpapp.adapter;

import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by siery on 15/8/18.
 */
public interface FlowLayoutCallback {
    void setData(SparseBooleanArray typeStatus, SparseBooleanArray areaStatus);

    int getCount();

    View getView(int position, ViewGroup container);
}
