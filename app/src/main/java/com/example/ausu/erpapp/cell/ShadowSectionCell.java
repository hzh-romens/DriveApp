package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.utils.UIUtils;

/**
 * Created by AUSU on 2016/7/12.
 */
public class ShadowSectionCell extends FrameLayout {
    private static Paint paint;

    public ShadowSectionCell(Context context) {
        super(context);
        setGreyDivider(context);
    }

    public ShadowSectionCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGreyDivider(context);
    }

    public ShadowSectionCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGreyDivider(context);
    }

    private void setGreyDivider(Context var1) {
        //this.setBackgroundResource(R.drawable.greydivider);
        this.setBackgroundColor(0xffeeeeee);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(UIUtils.dip2px(12), MeasureSpec.EXACTLY));
    }
}

//    protected void onMeasure(int var1, int var2) {
//        super.onMeasure(var1, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(12.0F), 1073741824));
//    }
//}
