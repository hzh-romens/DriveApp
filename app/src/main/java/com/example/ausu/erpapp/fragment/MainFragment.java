package com.example.ausu.erpapp.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.ausu.erpapp.CoachInfoActivity;
import com.example.ausu.erpapp.CoachListActivity;
import com.example.ausu.erpapp.DriverSchoolDetailActivity;
import com.example.ausu.erpapp.LoginActivity;
import com.example.ausu.erpapp.MyApplication;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.SearchActivity;
import com.example.ausu.erpapp.SingUpActivity;
import com.example.ausu.erpapp.adapter.MainAdapter;
import com.example.ausu.erpapp.city.NewCityModel;
import com.example.ausu.erpapp.city.NewDistrictModel;
import com.example.ausu.erpapp.city.NewProvinceModel;
import com.example.ausu.erpapp.city.NewXmlParserHandler;
import com.example.ausu.erpapp.city.ProvinceModel;
import com.example.ausu.erpapp.city.XmlParserHandler;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.CoachInfoBean;
import com.example.ausu.erpapp.model.DiscountBean;
import com.example.ausu.erpapp.model.DriverClassBean;
import com.example.ausu.erpapp.model.DriverSchoolListBean;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.LocationUtils;
import com.example.ausu.erpapp.utils.NetUtils;
import com.example.ausu.erpapp.utils.UIHelper;
import com.example.ausu.erpapp.utils.UIUtils;
import com.example.ausu.erpapp.view.AreaPopWindow;
import com.example.ausu.erpapp.view.CloudImageView;
import com.example.ausu.erpapp.view.MyExpandListView;
import com.example.ausu.erpapp.view.ReviseRadioButton;
import com.example.ausu.erpapp.web.ADWebActivity;
import com.fasterxml.jackson.databind.JsonNode;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * Created by pc on 2016/4/25.
 * 报名
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private MyExpandListView listView;
    private Toolbar toolbar;
    private MainAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    location.setText(msg.obj.toString());
                    break;
            }
        }
    };
    private RelativeLayout loading;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_actionbar, null);
        listView = (MyExpandListView) view.findViewById(R.id.myListView);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        loading = (RelativeLayout) view.findViewById(R.id.loading);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);

        UIHelper.setupSwipeRefreshLayoutProgress(refreshLayout);
        UIHelper.updateSwipeRefreshProgressBarTop(getActivity(), refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                refreshLayout.setRefreshing(false);
                if (!refreshLayout.isRefreshing()) {
                    //handler.post(connectRunnable);
                    handler.post(requestRunnable);
                }
                refreshLayout.setRefreshing(false);
            }
        });
        initToolBar();
        handler.post(LocaitionRunnable);
        handler.post(connectRunnable);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            //滚动到底部，添加下一页的数据
                            pageNum++;
                            handler.post(requestRunnable);
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        return view;
    }

    Runnable connectRunnable = new Runnable() {
        @Override
        public void run() {
            refreshLayout.setRefreshing(true);
            getData();
        }
    };
    Runnable requestRunnable = new Runnable() {
        @Override
        public void run() {
            initData();
        }
    };

    //获取地址信息
    Runnable LocaitionRunnable = new Runnable() {
        @Override
        public void run() {
            getLocation();
        }
    };


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            handler.sendMessage(handler.obtainMessage(1, aMapLocation.getCity()));
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    //定位
    private void getLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApplication.applicationContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setMockEnable(false);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setWifiActiveScan(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }


    private String typeValue;
    private String areaValue;
    private SparseBooleanArray typeStatus = new SparseBooleanArray();
    private SparseBooleanArray areaStatus = new SparseBooleanArray();

    private void initAreaPopWindow(View v) {
        typeValue = "";
        areaValue = "";

        final AreaPopWindow popWindowUtils = new AreaPopWindow();
        getPopWindowData();
        getStatusData();
        popWindowUtils.showPopWindow(getActivity(), v, types, areas, typeStatus, areaStatus);
        popWindowUtils.setCallBackListener(new AreaPopWindow.CallBackListener() {
            @Override
            public void callbackType(int pos) {
                if (pos != -1) {
                    Toast.makeText(getActivity(), types.get(pos), Toast.LENGTH_SHORT).show();
                    typeValue = types.get(pos);
                } else {
                    //重置选中的选项
                    typeValue = "";
                    getStatusData();
                    popWindowUtils.notifyStatus(typeStatus, areaStatus);
                }
            }

            @Override
            public void callbackArea(int pos) {
                if (pos != -1) {
                    Toast.makeText(getActivity(), areas.get(pos), Toast.LENGTH_SHORT).show();
                    areaValue = areas.get(pos);
                } else {
                    //重置选中的选项
                    areaValue = "";
                }
            }
        });
        popWindowUtils.setSureCallBackListener(new AreaPopWindow.SureCallBackListener() {
            @Override
            public void sureCallBack() {
                //点击确认键，调用接口，筛选页面数据
            }
        });

    }

    private void getStatusData() {
        for (int i = 0; i < areas.size(); i++) {
            areaStatus.append(i, false);
        }
        for (int i = 0; i < types.size(); i++) {
            typeStatus.append(i, false);
        }
    }

    private List<String> types;
    private List<String> areas;

    private void getPopWindowData() {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        netUtils.connectServer(hashMap, Config.BASE_URL + "/school/allLicensetype");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                Log.i("筛选数据-----", result);
            }
        });
        types = new ArrayList<String>();
        areas = new ArrayList<String>();
        types.add("不限");
        types.add("c1小车手动挡");
        types.add("c1小车自动挡");

        areas.add("不限");
        areas.add("海淀区");
        areas.add("丰台区");
        areas.add("石景山区");
        areas.add("房山区");
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private TextView location;

    private void initToolBar() {
        View view = View.inflate(getActivity(), R.layout.layout_search_cell, null);
        toolbar.addView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        toolbar.setBackgroundColor(getResources().getColor(R.color.themeColor));
        TextView searchView = (TextView) view.findViewById(R.id.searchView);
        location = (TextView) view.findViewById(R.id.qrCode);
        ImageView message = (ImageView) view.findViewById(R.id.message);
        message.setOnClickListener(this);
        location.setOnClickListener(this);
        searchView.setOnClickListener(this);
    }

    private int previouPosition;

    private List<DriverSchoolListBean> groupData;
    private Map<String, List<DriverClassBean>> childData;
    private int pageNum = 1;
    private int pageSize = 10;


    private void initData() {
        //获取数据并将之解析成相应的格式,第一次加载pageNum为1，翻页的时候+1
        final String requestUrl = Config.BASE_URL + "/school/list";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("pageNum", pageNum + "");
        params.put("pageSize", "10");
        params.put("latitude", "39.26");
        params.put("longitude ", "115.25");
        params.put("cityId", "1");
        NetUtils netUtils = new NetUtils();
        netUtils.connectServer(params, requestUrl);
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                try {
                    JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                    JsonNode pageList = jsonNode.path("pageList");
                    if (pageList != null && pageList.size() != 0) {
                        groupData = new ArrayList<DriverSchoolListBean>();
                        childData = new HashMap<String, List<DriverClassBean>>();
                        for (int i = 0; i < pageList.size(); i++) {
                            DriverSchoolListBean driverSchoolListBean = DriverSchoolListBean.jsonToEntity(pageList.get(i));
                            List<DriverClassBean> classList = driverSchoolListBean.getClassList();
                            childData.put(driverSchoolListBean.getId() + "", classList);
                            groupData.add(driverSchoolListBean);
                        }
                        if (pageNum == 1) {
                            adapter = new MainAdapter(getActivity());
                            adapter.bindData(groupData, childData);
                            listView.setAdapter(adapter);
                        } else {
                            adapter.addData(groupData, childData);
                        }
                        refreshLayout.setRefreshing(false);
                        if (getActivity() != null && adapter != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < adapter.getGroupCount(); i++) {
                                        listView.expandGroup(i);
                                    }
                                }
                            });
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //跳转到驾校详情
                startActivity(new Intent(getActivity(), DriverSchoolDetailActivity.class));
                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //跳转到班级详情
                return false;
            }
        });
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.flag_filter:
                Rect r = new Rect();
                v.getGlobalVisibleRect(r);
                int screenHeight = UIUtils.getScreenHeight();
                Rect rectangle = new Rect();
                Window window = getActivity().getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
                int statusBarHeight = rectangle.top;
                int actonBarHeght = UIUtils.dip2px(48);
                int distance = r.top - statusBarHeight - actonBarHeght;
                listView.scrollListBy(distance);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initAreaPopWindow(v);
                    }
                }, 300);
                break;
            case R.id.searchView:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.singUp:
                Intent intent = new Intent(getActivity(), ADWebActivity.class);
                intent.putExtra(ADWebActivity.ARGUMENTS_KEY_TARGET_URL, "file:///android_asset/baoming.html");
                startActivity(intent);
                break;
            case R.id.question:
                Intent questionIntent = new Intent(getActivity(), ADWebActivity.class);
                questionIntent.putExtra(ADWebActivity.ARGUMENTS_KEY_TARGET_URL, "file:///android_asset/xieyi.html");
                startActivity(questionIntent);
                break;
            case R.id.process:
                Intent processIntent = new Intent(getActivity(), ADWebActivity.class);
                processIntent.putExtra(ADWebActivity.ARGUMENTS_KEY_TARGET_URL, "file:///android_asset/xueche.html");
                startActivity(processIntent);
                break;
            case R.id.qrCode:
                getProvince();
                getCitys();
                getDistrict();

                break;
            case R.id.message:
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
    }

    private void getProvince() {
        try {
            List<ProvinceModel> provinceList = null;
            AssetManager asset = MyApplication.applicationContext.getResources().getAssets();
            InputStream input = asset.open("province_address.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            NewXmlParserHandler handler = new NewXmlParserHandler();
            parser.parse(input, handler);
            input.close();
            List<NewProvinceModel> dataList = handler.getDataList();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void getCitys() {
        try {
            List<ProvinceModel> provinceList = null;
            AssetManager asset = MyApplication.applicationContext.getResources().getAssets();
            InputStream input = asset.open("city_address.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            NewXmlParserHandler handler = new NewXmlParserHandler();
            parser.parse(input, handler);
            input.close();
            List<NewCityModel> dataList = handler.getCityList();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void getDistrict() {
        try {
            List<ProvinceModel> provinceList = null;
            AssetManager asset = MyApplication.applicationContext.getResources().getAssets();
            InputStream input = asset.open("district_address.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            NewXmlParserHandler handler = new NewXmlParserHandler();
            parser.parse(input, handler);
            input.close();
            List<NewDistrictModel> dataList = handler.getDistrictList();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }


    private void getData() {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> params = new HashMap<String, String>();
        String requestUrl = Config.BASE_URL + "/homepage/discount";
        netUtils.connectServer(params, requestUrl);
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                sortData(result);
                handler.post(requestRunnable);
            }
        });

    }

    private TextView endTime, driverName, discount, price, enroll, enroll_number;
    private CloudImageView carIcon;
    private TextView singUp, process, question;
    private ReviseRadioButton reviseRadioButton;
    private LinearLayout coachLayout;

    public void sortData(String result) {
        try {
            JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
            if (jsonNode.has("discount")) {
                //添加优惠
                JsonNode discountJson = jsonNode.path("discount");
                final DiscountBean discountBean = DiscountBean.jsonToEntity(discountJson);
                View head3 = View.inflate(getActivity(), R.layout.list_item_main_activity, null);
                FrameLayout schoolItem = (FrameLayout) head3.findViewById(R.id.schoolItem);
                endTime = (TextView) head3.findViewById(R.id.endTime);
                driverName = (TextView) head3.findViewById(R.id.driverName);
                discount = (TextView) head3.findViewById(R.id.discount);
                price = (TextView) head3.findViewById(R.id.price);
                enroll = (TextView) head3.findViewById(R.id.enroll);
                enroll_number = (TextView) head3.findViewById(R.id.enroll_number);
                carIcon = (CloudImageView) head3.findViewById(R.id.carIcons);
                endTime.setText("截止日期：" + discountBean.getDiscountdate() + "");
                driverName.setText(discountBean.getSchoolname());

                SpannableString spannableString = new SpannableString("￥" + discountBean.getDiscountprice() + "   市场价 ￥" + discountBean.getProductprice());
                ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.themeColor));
                //将这个Span应用于指定范围的字体
                spannableString.setSpan(span, 0, discountBean.getDiscountprice().toString().length() + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                ForegroundColorSpan span1 = new ForegroundColorSpan(getResources().getColor(R.color.light_black));
                spannableString.setSpan(span1, discountBean.getDiscountprice().toString().length() + 1, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(10, true);
                spannableString.setSpan(absoluteSizeSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                AbsoluteSizeSpan absoluteSizeSpan1 = new AbsoluteSizeSpan(16, true);
                spannableString.setSpan(absoluteSizeSpan1, 1, discountBean.getDiscountprice().toString().length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                discount.setText(discountBean.getProductprice() + "");
                price.setText(spannableString);
                schoolItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), DriverSchoolDetailActivity.class));
                    }
                });
                enroll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), SingUpActivity.class);
                        Bundle bundle = new Bundle();
                        DriverClassBean driverClassBean = DriverClassBean.ToDriverClassBean(discountBean);
                        bundle.putSerializable("DriverClass", driverClassBean);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                enroll_number.setText("已报名人数" + discountBean.getEnrollment() + "人");
                carIcon.setImagePath(discountBean.getAvatar());
                listView.addHeaderView(head3);
            }

            View head2 = View.inflate(getActivity(), R.layout.list_item_main_function, null);
            listView.addHeaderView(head2);
            head2.findViewById(R.id.singUp).setOnClickListener(this);
            head2.findViewById(R.id.process).setOnClickListener(this);
            head2.findViewById(R.id.question).setOnClickListener(this);

            if (jsonNode.has("coachList")) {
                JsonNode coachList = jsonNode.path("coachList");
                if (coachList != null && coachList.size() != 0) {
                    View head4 = View.inflate(getActivity(), R.layout.list_item_main_coach, null);
                    coachLayout = (LinearLayout) head4.findViewById(R.id.layout_coach);
                    RelativeLayout moreCoach = (RelativeLayout) head4.findViewById(R.id.moreCoach);
                    for (int i = 0; i < coachList.size(); i++) {
                        final CoachInfoBean coachInfoBean = CoachInfoBean.jsonToEntity(coachList.get(i));
                        View item = View.inflate(getActivity(), R.layout.list_item_main_coachinfo, null);
                        CloudImageView avatar_coach = (CloudImageView) item.findViewById(R.id.avatar_coach);
                        TextView coach_name = (TextView) item.findViewById(R.id.coach_name);
                        TextView coachYear = (TextView) item.findViewById(R.id.coachYear);
                        TextView coachInfo = (TextView) item.findViewById(R.id.coachInfo);
                        avatar_coach.setImagePath(coachInfoBean.getAvatar());
                        coach_name.setText(coachInfoBean.getUsername());
                        if (coachInfoBean.getGender() == 1) {
                            setRightDrawable(R.mipmap.ic_gender_man, coach_name);
                        } else {
                            setRightDrawable(R.mipmap.ic_gender_woman, coach_name);
                        }
                        coachYear.setText("教龄：" + coachInfoBean.getTeachage() + "年");
                        coachInfo.setText("平均拿证天数：" + coachInfoBean.getAvgachieveperiod() + "    通过率%" + coachInfoBean.getPassratio());
                        item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //跳转到相应的教练详情界面
                                Intent intent = new Intent(getActivity(), CoachInfoActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("coach", coachInfoBean);
                                intent.putExtras(bundle);
                                getActivity().startActivity(intent);
                            }
                        });
                        coachLayout.addView(item);
                    }
                    listView.addHeaderView(head4);
                    moreCoach.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(), CoachListActivity.class));
                        }
                    });
                }
            }
            View head1 = View.inflate(getActivity(), R.layout.list_item_main_filter, null);
            listView.addHeaderView(head1);
            head1.findViewById(R.id.flag_filter).setOnClickListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRightDrawable(int dresId, TextView textView) {
        Drawable drawable = getResources().getDrawable(dresId);
/// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
        textView.setCompoundDrawablePadding(4);
    }


}
