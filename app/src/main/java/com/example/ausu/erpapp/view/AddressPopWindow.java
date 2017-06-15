package com.example.ausu.erpapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ausu.erpapp.MyApplication;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.city.CityModel;
import com.example.ausu.erpapp.city.DistrictModel;
import com.example.ausu.erpapp.city.ProvinceModel;
import com.example.ausu.erpapp.city.XmlParserHandler;
import com.example.ausu.erpapp.city.adapters.ArrayWheelAdapter;
import com.example.ausu.erpapp.city.widget.OnWheelChangedListener;
import com.example.ausu.erpapp.city.widget.WheelView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Lanxumit on 2016/8/23.
 * 地址选择器，界面需要微调
 */
public class AddressPopWindow implements OnWheelChangedListener, View.OnClickListener {
    private PopupWindow popupWindow;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Context mContext;
    private TextView sure, cancle;

    public void showAddressPopWindow(final Activity context, final View targetView) {
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            this.mContext = context;
            View popWindow = inflater.inflate(R.layout.layout_popwindow_address, null);
            setUpViews(popWindow);
            setUpListener();
            setUpData();
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


    private void setUpViews(View popWindow) {
        mViewProvince = (WheelView) popWindow.findViewById(R.id.id_province);
        mViewCity = (WheelView) popWindow.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) popWindow.findViewById(R.id.id_district);
        cancle = (TextView) popWindow.findViewById(R.id.cancel_action);
        sure = (TextView) popWindow.findViewById(R.id.sure_action);
    }

    private void setUpListener() {
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        cancle.setOnClickListener(this);
        sure.setOnClickListener(this);

    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mProvinceDatas));
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    private String[] mProvinceDatas;
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    private Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    private Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();
    private String mCurrentProviceName;
    private String mCurrentCityName;
    private String mCurrentDistrictName = "";
    private String mCurrentZipCode = "";

    private void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = MyApplication.applicationContext.getResources().getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            provinceList = handler.getDataList();
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }

            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(mContext, areas));
        mViewDistrict.setCurrentItem(0);
    }


    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(mContext, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure_action:
                String address = mCurrentProviceName + mCurrentCityName + mCurrentDistrictName;
                mListener.AddressCallback(address);
                //Toast.makeText(mContext, address, Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                backgroundAlpha(1f);
                break;
            case R.id.cancel_action:
                popupWindow.dismiss();
                backgroundAlpha(1f);
                break;
        }
    }

    public interface AddressCallbackListener {
        void AddressCallback(String address);
    }

    private AddressCallbackListener mListener;

    public void setAddressCallbackListener(AddressCallbackListener listener) {
        this.mListener = listener;
    }
}
