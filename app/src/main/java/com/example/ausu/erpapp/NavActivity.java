package com.example.ausu.erpapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ausu.erpapp.utils.DrawableUtils;
import com.example.ausu.erpapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


public class NavActivity extends BaseActivity implements OnClickListener {
    private ViewPager mPager;
    FragmentManager fm;
    protected boolean isLastFlag;
    private View oval1, oval2, oval3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        setUpView();
        setUpListener();
    }

    private void setUpListener() {
        mPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    oval1.setBackground(DrawableUtils.createOvalDrawable(R.color.oval, R.color.oval, UIUtils.dip2px(4)));
                    oval2.setBackground(DrawableUtils.createOvalDrawable(R.color.md_white_1000, R.color.oval, UIUtils.dip2px(4)));
                    oval3.setBackground(DrawableUtils.createOvalDrawable(R.color.md_white_1000, R.color.oval, UIUtils.dip2px(4)));
                } else if (position == 1) {
                    oval1.setBackground(DrawableUtils.createOvalDrawable(R.color.md_white_1000, R.color.oval, UIUtils.dip2px(4)));
                    oval2.setBackground(DrawableUtils.createOvalDrawable(R.color.oval, R.color.oval, UIUtils.dip2px(4)));
                    oval3.setBackground(DrawableUtils.createOvalDrawable(R.color.md_white_1000, R.color.oval, UIUtils.dip2px(4)));
                } else {
                    oval1.setBackground(DrawableUtils.createOvalDrawable(R.color.md_white_1000, R.color.oval, UIUtils.dip2px(4)));
                    oval2.setBackground(DrawableUtils.createOvalDrawable(R.color.md_white_1000, R.color.oval, UIUtils.dip2px(4)));
                    oval3.setBackground(DrawableUtils.createOvalDrawable(R.color.oval, R.color.oval, UIUtils.dip2px(4)));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setUpView() {
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(new NavListFragmentAdapter());
        oval1 = findViewById(R.id.oval1);
        oval2 = findViewById(R.id.oval2);
        oval3 = findViewById(R.id.oval3);
        setOvalView();
        setViewPagerView();
    }

    private void setOvalView() {
        oval1.setBackground(DrawableUtils.createOvalDrawable(R.color.oval, R.color.oval, UIUtils.dip2px(4)));
        oval2.setBackground(DrawableUtils.createOvalDrawable(R.color.md_white_1000, R.color.oval, UIUtils.dip2px(4)));
        oval3.setBackground(DrawableUtils.createOvalDrawable(R.color.md_white_1000, R.color.oval, UIUtils.dip2px(4)));
    }

    private List<View> views = new ArrayList<View>();
    private TextView experience;

    private void setViewPagerView() {
        final View first = View.inflate(this, R.layout.layout_nav_first, null);
        View second = View.inflate(this, R.layout.layout_nav_second, null);
        View third = View.inflate(this, R.layout.layout_nav_third, null);
        experience = (TextView) third.findViewById(R.id.experience);
        experience.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavActivity.this, MainActivity.class));
                finish();
            }
        });
        views.add(first);
        views.add(second);
        views.add(third);
        mPager.setAdapter(new NavListFragmentAdapter());
    }

    @Override
    public void onClick(View v) {
    }


    public class NavListFragmentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
