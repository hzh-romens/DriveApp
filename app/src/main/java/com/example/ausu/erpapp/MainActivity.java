package com.example.ausu.erpapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.fragment.MainFragment;
import com.example.ausu.erpapp.fragment.UserFragment;
import com.example.ausu.erpapp.fragment.ForumFragment;
import com.example.ausu.erpapp.fragment.ProcessFragment;
import com.example.ausu.erpapp.model.UserBean;
import com.example.ausu.erpapp.utils.AccountUtils;
import com.example.ausu.erpapp.utils.EncryptUtils;
import com.example.ausu.erpapp.utils.OkHttpClientManager;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.lang.ref.SoftReference;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by AUSU on 2016/5/16.
 */
public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private RadioGroup tabs;
    private List<Fragment> fragments;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabs = (RadioGroup) findViewById(R.id.tabContainer);

        initFragment();
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                RadioButton childAt = (RadioButton) tabs.getChildAt(position);
                for (int i = 0; i < tabs.getChildCount(); i++) {
                    if (position == i) {
                        childAt.setChecked(true);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i = 0; i < tabs.getChildCount(); i++) {
            final int currentItem = i;
            tabs.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(currentItem);
                }
            });
        }
    }


    //初始化Fragment;
    private void initFragment() {
        fragments = new ArrayList<Fragment>();
        MainFragment mainFragment = new MainFragment();
        fragments.add(mainFragment);
        fragments.add(new ProcessFragment());
        fragments.add(new ForumFragment());
        fragments.add(new UserFragment());

    }

    private class MainFragmentAdapter extends FragmentPagerAdapter {

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //   fragments.get(position).
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            if (fragments != null) {
                return fragments.size();
            }
            return 0;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        //  for (MyTouchListener listener : myTouchListeners) {
        // listener.onTouchEvent(ev);
        // }
        // mListener.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }

    private MyTouchListener mListener;

    public void setMyTouchListener(MyTouchListener listener) {
        this.mListener = listener;
    }


}