package com.example.ausu.erpapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.fragment.LetterFragment;


/**
 * Created by Lanxumit on 2016/7/23.
 */
public class UIHelper {
    //打开基本信息填写页面
    public static void openReverseInfotActivity(Activity context, String title, int requestCode) {
//        Intent intent = new Intent(context, ReverseInforActivity.class);
//        intent.putExtra("title", title);
//        context.startActivityForResult(intent,requestCode);
    }


    public static void setupSwipeRefreshLayoutProgress(SwipeRefreshLayout refreshLayout) {
        if (refreshLayout != null) {
            refreshLayout.setColorSchemeResources(
                    R.color.refresh_progress_1,
                    R.color.refresh_progress_2,
                    R.color.refresh_progress_3);
        }
    }

    public static void updateSwipeRefreshProgressBarTop(Context context, SwipeRefreshLayout refreshLayout) {
        if (refreshLayout == null) {
            return;
        }
        int end = UIUtils.dip2px(64);
        refreshLayout.setProgressViewOffset(false, 0, end);
    }
}
