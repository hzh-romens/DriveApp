package com.example.ausu.erpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.ausu.erpapp.R;

/**
 * Created by Lanxumit on 2016/8/9.
 */
public class WebFragment extends Fragment {
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_web, null);
        webView = (WebView) view.findViewById(R.id.webvew);
        return view;
    }
}
