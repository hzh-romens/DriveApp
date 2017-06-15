package com.example.ausu.erpapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ausu.erpapp.adapter.SingUpAdapter;
import com.example.ausu.erpapp.model.DriverClassBean;
import com.example.ausu.erpapp.model.SingUpInfo;
import com.example.ausu.erpapp.utils.UIHelper;
import com.example.ausu.erpapp.view.AddressPopWindow;
import com.example.ausu.erpapp.view.DatePopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/21.
 * 确认报名
 */
public class SingUpActivity extends BaseActivity {
    private ListView listView;
    private ImageView btnBack;
    private DriverClassBean driverClassBean;
    private SparseIntArray types;
    private SingUpAdapter singUpAdapter;
    private SingUpInfo singUpInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_singup);
        listView = (ListView) findViewById(R.id.listview);
        btnBack = (ImageView) findViewById(R.id.btn_back);
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            if (extras.containsKey("DriverClass")) {
                driverClassBean = (DriverClassBean) extras.get("DriverClass");
            }
        }
        singUpAdapter = new SingUpAdapter(this);
        initData();
        listView.setAdapter(singUpAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //startActivityForResult
                if (position == 9) {
                    //选择性别
                    String[] sexs = new String[]{"男", "女"};
                    new AlertDialog.Builder(SingUpActivity.this, R.style.CustomStyle).setTitle("性别选择").setSingleChoiceItems(sexs, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                //男
                                singUpInfo.setSex(1);
                            } else if (which == 1) {
                                singUpInfo.setSex(0);
                            } else {
                                singUpInfo.setSex(1);
                            }
                            singUpAdapter.notifiData(singUpInfo);
                            dialog.dismiss();
                        }
                    }).setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                //男
                                singUpInfo.setSex(1);
                            } else if (which == 1) {
                                singUpInfo.setSex(0);
                            } else {
                                singUpInfo.setSex(1);
                            }
                            singUpAdapter.notifiData(singUpInfo);
                            dialog.dismiss();
                        }
                    }).create().show();
                } else if (position == 10) {
                    //选择出生日期
                    DatePopWindow datePopWindow = new DatePopWindow();
                    datePopWindow.showDatePopWindow(SingUpActivity.this, view);
                    datePopWindow.setDateCallbackListener(new DatePopWindow.DateCallbackListener() {
                        @Override
                        public void DateCallback(String time) {
                            //回调选择的时间
                            singUpInfo.setBirthday(time);
                            singUpAdapter.notifiData(singUpInfo);
                        }
                    });
                } else if (position == 11) {
                    //选择是否是本地人
                    String[] sexs = new String[]{"是", "否"};
                    new AlertDialog.Builder(SingUpActivity.this, R.style.CustomStyle).setTitle("选择").setSingleChoiceItems(sexs, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                singUpInfo.setIsNative(true);
                            } else if (which == 1) {
                                singUpInfo.setIsNative(false);
                            } else {
                                singUpInfo.setIsNative(true);
                            }
                            singUpAdapter.notifiData(singUpInfo);
                            dialog.dismiss();
                        }
                    }).setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                singUpInfo.setIsNative(true);
                            } else if (which == 1) {
                                singUpInfo.setIsNative(false);
                            } else {
                                singUpInfo.setIsNative(true);
                            }
                            singUpAdapter.notifiData(singUpInfo);
                            dialog.dismiss();
                        }
                    }).create().show();
                } else if (position == 12) {
                    //选择省市区
                    AddressPopWindow addressPopWindow = new AddressPopWindow();
                    addressPopWindow.showAddressPopWindow(SingUpActivity.this, view);
                    addressPopWindow.setAddressCallbackListener(new AddressPopWindow.AddressCallbackListener() {
                        @Override
                        public void AddressCallback(String address) {
                            singUpInfo.setAddress(address);
                            singUpAdapter.notifiData(singUpInfo);
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //初始化数据
    private void initData() {
        types = new SparseIntArray();
        if (driverClassBean != null) {
            //  driverClassBean.s
            singUpInfo = SingUpInfo.toSingUpInfo(driverClassBean);
            for (int i = 0; i < 15; i++) {
                types.put(i, i + 1);
            }
        } else {
            types.put(0, 99);
        }

        singUpAdapter.setData(types, singUpInfo);

    }

    //更新数据
    private void updateData() {
        singUpAdapter.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
