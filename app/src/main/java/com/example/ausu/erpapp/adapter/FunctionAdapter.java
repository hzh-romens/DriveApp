package com.example.ausu.erpapp.adapter;

import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.MyApplication;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.SelfActivity;
import com.example.ausu.erpapp.model.FunctionBean;

import java.util.List;

/**
 * Created by Lanxumit on 2016/7/27.
 */
public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.MyHolder> {
    private List<FunctionBean> mResult;

    public void bindData(List<FunctionBean> result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_function, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.functionText.setText(mResult.get(position).getName());
        holder.functionIcon.setImageResource(mResult.get(position).getDresId());
        holder.functionCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ItemClick(mResult.get(position).getName());
                //if (position == 0) {
                // Intent intent = new Intent(MyApplication.applicationContext, SelfActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //MyApplication.applicationContext.startActivity(intent);
                //  }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResult != null ? mResult.size() : 0;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private ImageView functionIcon;
        private TextView functionText;
        private LinearLayout functionCell;

        public MyHolder(View itemView) {
            super(itemView);
            functionIcon = (ImageView) itemView.findViewById(R.id.grid_img);
            functionText = (TextView) itemView.findViewById(R.id.grid_txt);
            functionCell = (LinearLayout) itemView.findViewById(R.id.functionCell);
        }
    }

    public interface ItemClickListener {
        void ItemClick(String value);
    }

    private ItemClickListener mListener;

    public void setClickListener(ItemClickListener listener) {
        this.mListener = listener;
    }
}
