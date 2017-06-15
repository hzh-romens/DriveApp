package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.ausu.erpapp.R;

/**
 * Created by Lanxumit on 2016/7/23.
 */
public class EditTextCell extends FrameLayout {
    private EditText editText;
    private View divider;

   public interface EditTextListener {
        void EditTextCallBack(String result);
    }

    public EditTextCell(Context context) {
        super(context);
        initView(context);
    }

    public EditTextCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EditTextCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, R.layout.list_item_edittext, null);
        editText = (EditText) inflate.findViewById(R.id.edittext);
        divider = inflate.findViewById(R.id.divider);
//        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    //将保存的内容回调回去
//
//                }
//            }
//        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mListener.EditTextCallBack(editText.getText().toString().trim());
            }
        });
        addView(inflate);
    }

    public void setValue(String hint, boolean isNeedDivider) {
        editText.setHint(hint);
        if (isNeedDivider) {
            divider.setVisibility(VISIBLE);
        } else {
            divider.setVisibility(GONE);
        }
    }

    private EditTextListener mListener;

    public void setEditTextListener(EditTextListener listenr) {
        this.mListener = listenr;
    }

}
