package com.example.ausu.erpapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.ausu.erpapp.PracticeActivity;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.ProcessAdapter;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.AppointmentBean;
import com.example.ausu.erpapp.model.ProcessBean;
import com.example.ausu.erpapp.utils.AccountUtils;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.NetUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by pc on 2016/4/25.
 */
public class ProcessFragment extends Fragment {
    private ListView listView;
    private ImageView message;
    private ProcessAdapter adapter;
    private SparseIntArray types;
    private AppointmentBean appointmentBean;
    private ProcessBean processBean;
    private RelativeLayout loadView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_news, null);
        listView = (ListView) view.findViewById(R.id.listview);
        message = (ImageView) view.findViewById(R.id.message);
        loadView = (RelativeLayout) view.findViewById(R.id.loading);
        initData();
        adapter = new ProcessAdapter(getActivity());
        listView.setAdapter(adapter);
        appointmentBean = new AppointmentBean();
        adapter.setAppointClickListener(new ProcessAdapter.AppointClickListener() {
            @Override
            public void ItemClickCallBack() {
                startActivityForResult(new Intent(getActivity(), PracticeActivity.class), 0);
            }

            @Override
            public void TimeClickCallBack(String appointTime) {
                appointmentBean.setAppointmentTime(appointTime);
                adapter.notifyData(appointmentBean);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccountUtils.instance().getStatus("notify")) {
            if (loadView.getVisibility() == View.GONE) {
                loadView.setVisibility(View.VISIBLE);
            }
            initData();
        }
    }

    private void initData() {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        netUtils.connectServer(hashMap, Config.BASE_URL + "/study/summary");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                try {
                    types = new SparseIntArray();
                    if ("请求资源无法授权给未验证的用户".equals(result)) {
                        types.append(0, -1);
                        processBean = new ProcessBean();
                        adapter.setData(processBean, types);
                        loadView.setVisibility(View.GONE);
                    } else {
                        JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                        processBean = ProcessBean.jsonToEntity(jsonNode);
                        //  用户状态： 0 锁定  1 已报名  2 信息不完整  3 未报名
                        if (processBean.getStudentstatus() != 1) {
                            //未报名状态
                            types.put(0, 1);
                            types.put(1, 3);
                        } else {
                            types.put(0, 0);
                            types.put(1, 2);
                        }
                        //types.put(2, 3);
                        //types.put(3, 4);
                        types.put(2, 4);
                        adapter.setData(processBean, types);
                        loadView.setVisibility(View.GONE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == 0) {
            String item = data.getExtras().getString("item");
            if ("".equals(item)) {
                appointmentBean.setProjrcts("无");
            } else {
                appointmentBean.setProjrcts(item);
            }
            adapter.notifyData(appointmentBean);
        }
    }
}
