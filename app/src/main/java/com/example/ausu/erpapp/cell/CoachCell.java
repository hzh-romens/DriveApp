package com.example.ausu.erpapp.cell;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.MessageActivity;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.view.CloudImageView;

/**
 * Created by Lanxumit on 2016/7/25.
 */
public class CoachCell extends FrameLayout {
    private CloudImageView head;
    private TextView name, coachYear, coachTime, rate, coachDay, words;


    public CoachCell(Context context) {
        super(context);
        initView(context);
    }

    public CoachCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CoachCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        View view = View.inflate(context, R.layout.list_item_coachinfo, null);
        head = (CloudImageView) view.findViewById(R.id.avatar_coach);
        name = (TextView) view.findViewById(R.id.coach_name);
        coachYear = (TextView) view.findViewById(R.id.coachYear);
        coachTime = (TextView) view.findViewById(R.id.coachTime);
        rate = (TextView) view.findViewById(R.id.rate);
        coachDay = (TextView) view.findViewById(R.id.day);
        words = (TextView) view.findViewById(R.id.words);
        words.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到教练留言页面
                context.startActivity(new Intent(context, MessageActivity.class));
            }
        });
        addView(view);
    }

    public void setValue(String avatar, String coacName, int teachYear, int age, String rates, String time, String day, int sex) {
        String url = "http://b.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=72153cdbf536afc30e5937638329c7fc/4034970a304e251fe49095e3a586c9177f3e5341.jpg";
        head.setImagePath(url);
        Drawable drawable = null;
        name.setText(coacName);
        if (sex == 1) {
            //男  设置drawableright
            drawable = getResources().getDrawable(R.mipmap.ic_gender_man);
        } else {
            //女
            drawable = getResources().getDrawable(R.mipmap.ic_gender_woman);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        name.setCompoundDrawables(null, null, drawable, null);
        coachYear.setText("年龄：" + age + "   教龄：" + teachYear);
        SpannableString timeStr = getSpannableString(time, "累计教授时长");
        SpannableString rateStr = getSpannableString(rates, "通过率");
        SpannableString dayStr = getSpannableString(day, "平均拿证天数");
        coachTime.setText(timeStr);
        coachDay.setText(dayStr);
        rate.setText(rateStr);
    }

    private SpannableString getSpannableString(String prefix, String suffix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append("\n");
        stringBuilder.append(suffix);
        SpannableString spannableString = new SpannableString(stringBuilder);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.theme_text_price)), 0, prefix.length(),
                //setSpan时需要指定的 flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括).
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan absoluteSizeSpan1 = new AbsoluteSizeSpan(16, true);
        spannableString.setSpan(absoluteSizeSpan1, 0, prefix.toString().length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }
}
