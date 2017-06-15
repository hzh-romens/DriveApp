package com.example.ausu.erpapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;
//import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by AUSU on 2016/5/9.
 */
public class ClearEdittext extends FrameLayout {
    private Context mContext;
    private Drawable clearDrawable;
    private Drawable watchDrawable;


    public ClearEdittext(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ClearEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public ClearEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        //LayoutInflater.from(mContext,)
        clearDrawable = mContext.getResources().getDrawable(R.mipmap.abc_ic_clear_search_api_disabled_holo_light);
        watchDrawable = mContext.getResources().getDrawable(R.mipmap.ic_password_hidden);
    }
}
