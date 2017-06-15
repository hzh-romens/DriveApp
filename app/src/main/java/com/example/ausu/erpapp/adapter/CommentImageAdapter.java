package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ausu.erpapp.R;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/6.
 */
public class CommentImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mImages;

    public CommentImageAdapter(Context context) {
        this.mContext = context;
    }

    public void setImages(List<String> images) {
        this.mImages = images;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImages != null ? mImages.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder = null;
        if (convertView == null) {
            holder = new MyHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_image, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        //f (mImages.get(position)!=null&&!"".equals(mImages.get(position))){
        //    mImages.set
        // }
        holder.imageView.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
        return convertView;
    }

    public class MyHolder {
        private ImageView imageView;
    }
}
