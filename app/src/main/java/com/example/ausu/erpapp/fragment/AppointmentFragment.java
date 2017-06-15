package com.example.ausu.erpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ausu.erpapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/28.
 * 预约直考界面
 */
public class AppointmentFragment extends Fragment {
    private ViewPager viewPager;
    private CuoponFragmentPagerAdapter fragmentPagerAdapter;
    private TabLayout tabLayout;
    private List<Fragment> fragmentsList;
    private List<String> titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_appointment, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tableLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        initFragments();
        initTitles();
        fragmentPagerAdapter = new CuoponFragmentPagerAdapter(getChildFragmentManager(), fragmentsList, titles);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void initFragments() {
        fragmentsList = new ArrayList<Fragment>();
        ExamFragment examFragment = new ExamFragment();
        fragmentsList.add(examFragment);
        DriverExamFragment driverExamFragment = new DriverExamFragment();
        fragmentsList.add(driverExamFragment);
        ExamFragment examFragment2 = new ExamFragment();
        fragmentsList.add(examFragment2);

    }

    private void initTitles() {
        titles = new ArrayList<String>();
        titles.add("理论考试");
        titles.add("科目二考试");
        titles.add("理论考试");
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
    }


    public class CuoponFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<String> mTitles;
        private List<Fragment> mFragmentList;

        public CuoponFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titles) {
            super(fm);
            this.mFragmentList = fragmentList;
            this.mTitles = titles;
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }

        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}
