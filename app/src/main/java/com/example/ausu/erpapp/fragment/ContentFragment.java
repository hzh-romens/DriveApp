package com.example.ausu.erpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.ProcessAdapter;

/**
 * Created by AUSU on 2016/7/17.
 */
public class ContentFragment extends Fragment {
    private ListView listView;
    private ProcessAdapter processAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_content, null);
        listView = (ListView) inflate.findViewById(R.id.listview);
        processAdapter = new ProcessAdapter(getActivity());
        return inflate;
    }
}
