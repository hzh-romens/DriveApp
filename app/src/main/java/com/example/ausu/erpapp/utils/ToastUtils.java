package com.example.ausu.erpapp.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ausu.erpapp.R;

import org.w3c.dom.Text;

/**
 * Created by Lanxumit on 2016/10/12.
 */
public class ToastUtils {
    private static ToastUtils insatnce = null;

    public static synchronized ToastUtils getInsatnce() {
        if (insatnce == null) {
            synchronized (ToastUtils.class) {
                if (insatnce == null) {
                    insatnce = new ToastUtils();
                }
            }
        }
        return insatnce;
    }

    public void showToastWithImage(Context context, int imageId, String content, int duration) {
        Toast toast = new Toast(context);
        View view = View.inflate(context, R.layout.layout_toast, null);
        TextView contentView = (TextView) view.findViewById(R.id.content);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        contentView.setText(content);
        iconView.setImageResource(imageId);
        toast.setView(view);
        toast.setDuration(duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
