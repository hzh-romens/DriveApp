package com.example.ausu.erpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.StudyScheduleAdapter;
import com.example.ausu.erpapp.model.StudyBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lanxumit on 2016/7/28.
 */
public class StudyScheduleFragment extends Fragment {
    private ExpandableListView expandableListView;
    private StudyScheduleAdapter studyScheduleAdapter;
    private Map<Integer, StudyBean> result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_studyschedule, null);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandedlistview);
        initData();
        //默认展开所有的Group
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int count = studyScheduleAdapter.getGroupCount();
                    for (int i = 0; i < count; i++) {
                        if (expandableListView != null) {
                            expandableListView.expandGroup(i);
                        }
                    }
                }
            });
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        return view;
    }

    private void initData() {
        result = new HashMap<Integer, StudyBean>();
        StudyBean studyBean = new StudyBean();
        studyBean.setTypeName("科目一");
        List<StudyBean.ItemBean> itemBeanList = new ArrayList<StudyBean.ItemBean>();
        StudyBean.ItemBean itemBean = studyBean.new ItemBean();
        itemBean.setIsComplete(false);
        itemBean.setItemName("倒库");
        itemBean.setItemId("1");
        itemBeanList.add(itemBean);
        studyBean.setItems(itemBeanList);
        result.put(0, studyBean);
        studyScheduleAdapter = new StudyScheduleAdapter(getActivity());
        studyScheduleAdapter.bindData(result);
        expandableListView.setAdapter(studyScheduleAdapter);
    }
}
