package com.example.ausu.erpapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.utils.UIUtils;
import com.example.ausu.erpapp.view.CloudImageView;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/13.
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyHolder> {
    private List<String> mUrls;

    public void bindData(List<String> urls) {
        this.mUrls = urls;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_image, parent, false);
        view.setLayoutParams(new AbsListView.LayoutParams(UIUtils.getScreenWidth() / 4, ViewGroup.LayoutParams.MATCH_PARENT));
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Log.i("图片地址----", mUrls.get(position));
        holder.functionIcon.setImagePath(mUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return mUrls != null ? mUrls.size() : 0;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private CloudImageView functionIcon;

        public MyHolder(View itemView) {
            super(itemView);
            functionIcon = (CloudImageView) itemView.findViewById(R.id.image);
            functionIcon.setBackgroundResource(R.mipmap.ic_launcher);

        }
    }
}
