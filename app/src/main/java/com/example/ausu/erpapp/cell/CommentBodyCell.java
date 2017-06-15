package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.CommentImageAdapter;
import com.example.ausu.erpapp.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/6.
 */
public class CommentBodyCell extends FrameLayout {
    private MyGridView gridView;
    private CommentImageAdapter adapter;
    private Context mContext;

    public CommentBodyCell(Context context) {
        super(context);
        initView(context);
    }

    public CommentBodyCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CommentBodyCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_content_body, null);
        gridView = (MyGridView) view.findViewById(R.id.gridview);
        mContext = context;
        List<String> test = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            test.add(1 + "");
        }
        setImageList(test);
        addView(view);
    }

    public void setImageList(List<String> imageList) {
        adapter = new CommentImageAdapter(mContext);
        adapter.setImages(imageList);
        gridView.setAdapter(adapter);
    }

}
