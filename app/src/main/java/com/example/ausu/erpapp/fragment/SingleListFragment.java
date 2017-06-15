package com.example.ausu.erpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/2.
 */
public class SingleListFragment extends Fragment implements View.OnClickListener {
    private MyViewPager viewPager;
    private TextView previousPage, nextPage, pageNumber;
    private CuoponFragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragmentsList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_singlelist, null);
        viewPager = (MyViewPager) view.findViewById(R.id.viewpager);
        previousPage = (TextView) view.findViewById(R.id.previousPage);
        pageNumber = (TextView) view.findViewById(R.id.pageNumber);
        nextPage = (TextView) view.findViewById(R.id.nextPage);
        viewPager.setScanScroll(false);
        //  viewPager.setIsScrollable(false);
        initData();
        initFragments();
        previousPage.setOnClickListener(this);
        nextPage.setOnClickListener(this);
        return view;
    }

    private void initFragments() {
        fragmentsList = new ArrayList<Fragment>();
        fragmentsList.add(new SingleFragment());
        fragmentsList.add(new SingleFragment());
        fragmentsList.add(new SingleFragment());
        fragmentPagerAdapter = new CuoponFragmentPagerAdapter(getChildFragmentManager(), fragmentsList);
        viewPager.setAdapter(fragmentPagerAdapter);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        int currentItem = viewPager.getCurrentItem();
        switch (v.getId()) {
            case R.id.previousPage:
                Toast.makeText(getActivity(), "上一题", Toast.LENGTH_SHORT).show();
                // viewPager.setCurrentItem(2);
                viewPager.setCurrentItem(--currentItem);
                break;
            case R.id.nextPage:
                Toast.makeText(getActivity(), "下一题", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(++currentItem);
                break;
        }


    }

    public class CuoponFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList;

        public CuoponFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.mFragmentList = fragmentList;
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

    }
}
