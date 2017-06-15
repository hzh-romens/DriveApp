package com.example.ausu.erpapp.utils;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.WindowManager;

import com.example.ausu.erpapp.MyApplication;
import com.example.ausu.erpapp.R;

/**
 * Created by Lanxumit on 2016/8/14.
 */
public class DrawableUtils {
    public static class MyImageSpan extends ImageSpan {

        public MyImageSpan(Drawable d) {
            super(d);
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            Drawable drawable = getDrawable();
            Rect rect = drawable.getBounds();
            if (fm != null) {
                Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
                int fontHeight = fmPaint.bottom - fmPaint.top;
                int drawableHeight = rect.bottom - rect.top;
                int top = drawableHeight / 2 - fontHeight / 4;
                int bottom = drawableHeight / 2 + fontHeight / 4;
                fm.ascent = -bottom;
                fm.top = -bottom;
                fm.bottom = top;
                fm.descent = top;
            }
            return rect.right;
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            paint.setColor(Color.WHITE);
            paint.setTextSize(28);
            Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
            float v = paint.measureText(text, start, end) + UIUtils.dip2px(4);
            float dimension = MyApplication.applicationContext.getResources().getDimension(R.dimen.text_large);
            getDrawable().setBounds(0, 0, (int) v, (int) dimension);
            super.draw(canvas, text, start, end, x, top, y, bottom - (bottom - y) / 2, paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(28);
            canvas.drawText(text.subSequence(start, end).toString(), x + UIUtils.dip2px(2), y - (bottom - y) / 2, paint);
            canvas.translate(0, UIUtils.dip2px(10));
            canvas.restore();
        }

    }

    public static GradientDrawable createCheckedDrawable(int contentColor, int strokeColor, int radius) {
        GradientDrawable drawable = new GradientDrawable(); // 生成Shape
        drawable.setGradientType(GradientDrawable.RECTANGLE); // 设置矩形
        drawable.setColor(contentColor);// 内容区域的颜色21
        drawable.setStroke(1, UIUtils.getContext().getResources().getColor(strokeColor)); // 四周描边,描边后四角真正为圆角，不会出现黑色阴影。如果父窗体是可以滑动的，需要把父View设置setScrollCache(false)
        drawable.setCornerRadius(radius); // 设置四角都为圆角
        return drawable;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public static GradientDrawable createOvalDrawable(int contentColor, int strokeColor, int radius) {
        GradientDrawable drawable = new GradientDrawable(); // 生成Shape
        drawable.setGradientType(GradientDrawable.OVAL); // 设置矩形
        drawable.setColor(UIUtils.getContext().getResources().getColor(contentColor));// 内容区域的颜色21
        drawable.setStroke(1, UIUtils.getContext().getResources().getColor(strokeColor)); // 四周描边,描边后四角真正为圆角，不会出现黑色阴影。如果父窗体是可以滑动的，需要把父View设置setScrollCache(false)
        drawable.setCornerRadius(radius); // 设置四角都为圆角
        return drawable;
    }

}
