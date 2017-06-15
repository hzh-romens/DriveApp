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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/31.
 */
public class PracticeFragment extends Fragment {

    private List<Fragment> fragmentsList;
    private ViewPager viewPager;
    private CuoponFragmentPagerAdapter fragmentPagerAdapter;
    private TabLayout tabLayout;
    private List<String> titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_practice, null);
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
        SingleFragment singleFragment = new SingleFragment();
        fragmentsList.add(singleFragment);
        SingleFragment singleFragment1 = new SingleFragment();
        fragmentsList.add(singleFragment1);
    }


    private void initTitles() {
        titles = new ArrayList<String>();
        titles.add("知识汇总");
        titles.add("习题自测");
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
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
