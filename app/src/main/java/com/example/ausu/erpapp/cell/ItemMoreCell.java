package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;

import org.w3c.dom.Text;

/**
 * Created by Lanxumit on 2016/7/26.
 */
public class ItemMoreCell extends FrameLayout {
    private TextView button;

    public ItemMoreCell(Context context) {
        super(context);
        initView(context);
    }


    public ItemMoreCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ItemMoreCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.list_item_more, null);
        button = (TextView) view.findViewById(R.id.button);
        addView(view);
    }

    public void setValue(String name, boolean isNeedIcon, int dresId) {
        button.setText(name);
        if (isNeedIcon&&dresId!=-1) {
            Drawable drawable = getResources().getDrawable(dresId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            button.setCompoundDrawables(null, null, drawable, null);
        }
    }
}
