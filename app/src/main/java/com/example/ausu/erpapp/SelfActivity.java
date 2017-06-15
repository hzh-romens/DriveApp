package com.example.ausu.erpapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.ausu.erpapp.fragment.PracticeFragment;
import com.example.ausu.erpapp.fragment.SingleFragment;

/**
 * Created by Lanxumit on 2016/7/30.
 */
public class SelfActivity extends BaseActivity {
    private RadioGroup radioGroup;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        container = (FrameLayout) findViewById(R.id.container);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio:
                        PracticeFragment practiceFragment = new PracticeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("item", "科目一");
                        practiceFragment.setArguments(bundle);
                        changeFragment(practiceFragment);
                        break;
                    case R.id.radioTwo:
                        PracticeFragment practiceFragment2 = new PracticeFragment();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("item", "科目四");
                        practiceFragment2.setArguments(bundle2);
                        changeFragment(practiceFragment2);
                        break;
                }
            }
        });
        radioGroup.check(R.id.radio);
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
