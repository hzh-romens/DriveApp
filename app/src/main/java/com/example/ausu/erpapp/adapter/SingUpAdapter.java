package com.example.ausu.erpapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.SingupToSuccessActivity;
import com.example.ausu.erpapp.cell.EditTextCell;
import com.example.ausu.erpapp.cell.ShadowSectionCell;
import com.example.ausu.erpapp.cell.SureButtonCell;
import com.example.ausu.erpapp.cell.TextViewWithIcon;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.SingUpInfo;
import com.example.ausu.erpapp.utils.AccountUtils;
import com.example.ausu.erpapp.utils.NetUtils;

import java.util.HashMap;

/**
 * Created by Lanxumit on 2016/7/23.
 */
public class SingUpAdapter extends BaseAdapter {
    private SparseIntArray mTypes;
    public SingUpInfo mData;
    private Context mContext;

    public SingUpAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(SparseIntArray type, SingUpInfo data) {
        this.mTypes = type;
        this.mData = data;
        notifyDataSetChanged();
    }

    public void notifiData(SingUpInfo data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTypes != null ? mTypes.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypes.get(position) == 5) {
            return 0;
        } else if (mTypes.get(position) == 15) {
            return 2;
        } else if (mTypes.get(position) == 99) {
            return 99;
        } else if (mTypes.get(position) == 8 || mTypes.get(position) == 9 || mTypes.get(position) == 14) {
            return 3;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1) {
            if (convertView == null) {
                convertView = new TextViewWithIcon(mContext);
            }
            TextViewWithIcon textViewWithIcon = (TextViewWithIcon) convertView;
            if (mTypes.get(position) == 1) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                textViewWithIcon.setValue("套餐", mData.getClassName(), false, false, false);
            } else if (mTypes.get(position) == 2) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                textViewWithIcon.setValue("驾校", mData.getDriverSchoolName(), false, false, false);
            } else if (mTypes.get(position) == 3) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                textViewWithIcon.setValue("教练", mData.getCoath(), false, false, false);
            } else if (mTypes.get(position) == 4) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_text_price);
                textViewWithIcon.setValue("共计", mData.getPrice() + "", false, false, false);
            } else if (mTypes.get(position) == 6) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                textViewWithIcon.setValue("支付方式", "在线支付", false, true, false);
            } else if (mTypes.get(position) == 7) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                textViewWithIcon.setValue("基本信息填写", "", false, false, true);
                textViewWithIcon.setBackGroundColor(R.color.theme_bgc_grey);
            } else if (mTypes.get(position) == 10) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                String sex = "";
                if (mData.getSex() == -1) {
                    sex = "";
                } else if (mData.getSex() == 1) {
                    sex = "男";
                } else {
                    sex = "女";
                }
                textViewWithIcon.setValue("性别", sex, false, true, false);
            } else if (mTypes.get(position) == 11) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                textViewWithIcon.setValue("出生日期", mData.getBirthday(), false, true, false);
            } else if (mTypes.get(position) == 12) {
                String nativeValue = "否";
                if (mData.isNative()) {
                    nativeValue = "是";
                }
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                textViewWithIcon.setValue("是否是本地人", nativeValue, false, true, false);
            } else if (mTypes.get(position) == 13) {
                textViewWithIcon.setTextColor(R.color.theme_light_black, R.color.theme_black);
                textViewWithIcon.setValue("省、市、区", mData.getAddress(), false, true, false);
            }

        } else if (itemViewType == 2) {
            if (convertView == null) {
                convertView = new SureButtonCell(mContext);
            }
            SureButtonCell cell = (SureButtonCell) convertView;
            cell.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //提交信息//提交成功則跳轉到以一個頁面
                    SingUpInfo mData = SingUpAdapter.this.mData;
                    HashMap hashMap = new HashMap();
                    hashMap.put("classId", mData.getClassId() + "");
                    hashMap.put("paytype", "1");
                    hashMap.put("username", mData.getStudentName());
                    hashMap.put("contactphone", mData.getPhoneNumber());
                    hashMap.put("gender", mData.getSex() + "");
                    hashMap.put("birthdate", mData.getBirthday());
                    hashMap.put("localcitizen", mData.isNative() ? "1" : "0");
                    hashMap.put("districtid", "1");
                    hashMap.put("homeaddress", mData.getStreet());
                    NetUtils netUtils = new NetUtils();
                    netUtils.connectServer(hashMap, Config.BASE_URL + "/enroll/join");
                    netUtils.setCallBackListener(new NetUtils.CallBackListener() {
                        @Override
                        public void CallBackResult(String result) {
                            if (result.contains("retcode")) {
                                AccountUtils.instance().saveStatus("notify", true);
                                mContext.startActivity(new Intent(mContext, SingupToSuccessActivity.class));
                                ((Activity) mContext).finish();
                            } else {
                                Toast.makeText(mContext, "报名失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        } else if (itemViewType == 99) {
            //展示一个错误页面

        } else if (itemViewType == 3) {
            //编辑并保存内容
            if (convertView == null) {
                convertView = new EditTextCell(mContext);
            }
            EditTextCell cell = (EditTextCell) convertView;
            final int i = mTypes.get(position);
            if (mTypes.get(position) == 8) {
                cell.setValue("真实姓名", false);
            } else if (mTypes.get(position) == 9) {
                cell.setValue("联系电话", false);
            } else if (mTypes.get(position) == 14) {
                cell.setValue("详细地址", false);
            }
            cell.setEditTextListener(new EditTextCell.EditTextListener() {
                @Override
                public void EditTextCallBack(String result) {
                    if (i == 8) {
                        //更新Bean中相對應的字段
                        mData.setStudentName(result);
                    } else if (i == 9) {
                        mData.setPhoneNumber(result);
                    } else if (i == 14) {
                        mData.setStreet(result);
                    }
                    //notifyDataSetChanged();
                }
            });

        } else {
            if (convertView == null) {
                convertView = new ShadowSectionCell(mContext);
            } else {
                ShadowSectionCell cell = (ShadowSectionCell) convertView;
            }
        }

        return convertView;
    }
}
