package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.adapter.FunctionAdapter;
import com.example.ausu.erpapp.model.FunctionBean;
import com.example.ausu.erpapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AUSU on 2016/7/12.
 */
public class FunctionCell extends FrameLayout {
    private RecyclerView recyclerView;
    private FunctionAdapter functionAdapter;

    public FunctionCell(Context context) {
        super(context);
        initView(context);
    }

    public FunctionCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FunctionCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private GridLayoutManager gridLayoutManager;
    private List<FunctionBean> result;

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_function, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        //创建默认的线性LayoutManager
        gridLayoutManager = new GridLayoutManager(context, UIUtils.getScreenWidth() / UIUtils.dip2px(96), GridLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(gridLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        // initData();
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            final Paint paint = new Paint();

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, UIUtils.dip2px(1), 0);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                // super.onDrawOver(c, parent, state);
                paint.setColor(getResources().getColor(R.color.material_grey_100));
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = parent.getChildAt(i);
                    c.drawLine(childAt.getX(), childAt.getY(), childAt.getX(), childAt.getHeight() + childAt.getY(), paint);
                }
            }
        });

        addView(view, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(96)));
    }

    public void initData(List<FunctionBean> result) {
        functionAdapter = new FunctionAdapter();
        functionAdapter.bindData(result);
        recyclerView.setAdapter(functionAdapter);
        functionAdapter.setClickListener(new FunctionAdapter.ItemClickListener() {
            @Override
            public void ItemClick(String value) {
                mListener.OnItemClick(value);
            }
        });
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(String name);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
}
