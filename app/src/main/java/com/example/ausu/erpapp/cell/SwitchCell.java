package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;

import java.util.List;

/**
 * Created by AUSU on 2016/7/17.
 */
public class SwitchCell extends FrameLayout{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    public SwitchCell(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, R.layout.list_item_switch, null);
        tabLayout= (TabLayout) inflate.findViewById(R.id.tableLayout);
        viewPager= (ViewPager) inflate.findViewById(R.id.viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        addView(inflate);
    }

    private List<String> titles;
    public void setValue(List<String> titles){
        this.titles=titles;
        initFragment();
    }

    private void initFragment() {

    }


    public SwitchCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
