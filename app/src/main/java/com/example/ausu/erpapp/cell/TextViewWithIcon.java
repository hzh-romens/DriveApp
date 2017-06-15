package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.utils.UIUtils;

/**
 * Created by Lanxumit on 2016/7/23.
 */
public class TextViewWithIcon extends FrameLayout {
    private TextView textName, textDetail;
    private FrameLayout item;
    private View divider;

    public TextViewWithIcon(Context context) {
        super(context);
        initView(context);
    }

    public TextViewWithIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TextViewWithIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, R.layout.list_item_textwhiticon, null);
        textName = (TextView) inflate.findViewById(R.id.textName);
        textDetail = (TextView) inflate.findViewById(R.id.textDetail);
        divider = inflate.findViewById(R.id.divider);
        item = (FrameLayout) inflate.findViewById(R.id.item);
        addView(inflate);
    }

    public void setValue(String name, String detail, boolean isNeedDivider, boolean isNeedIcon, boolean isVisibility) {
        textDetail.setText(detail);
        textName.setText(name);
        if (isNeedDivider) {
            divider.setVisibility(VISIBLE);
        } else {
            divider.setVisibility(GONE);
        }
        if (isNeedIcon) {
            Drawable drawable = getResources().getDrawable(R.mipmap.btn_right);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textDetail.setCompoundDrawables(null, null, drawable, null);
        }
        if (isVisibility) {
            textDetail.setVisibility(GONE);
        } else {
            textDetail.setVisibility(VISIBLE);
        }

    }

    public void setTextNameDrawableLeft(boolean isNeed, int dres) {
        if (isNeed) {
            Drawable drawable = getResources().getDrawable(dres);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textName.setCompoundDrawablePadding(UIUtils.dip2px(4));
            textName.setCompoundDrawables(drawable, null, null, null);
        }
    }

    public void setTextColor(int textNameColor, int textDetailColor) {
        textDetail.setTextColor(getResources().getColor(textDetailColor));
        textName.setTextColor(getResources().getColor(textNameColor));
    }

    public void setBackGroundColor(int color) {
        item.setBackgroundColor(getResources().getColor(color));
    }
}
