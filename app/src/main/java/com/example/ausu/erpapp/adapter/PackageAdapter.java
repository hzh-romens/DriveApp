package com.example.ausu.erpapp.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.cell.ClassDetailCell;
import com.example.ausu.erpapp.cell.PackageCoachCell;
import com.example.ausu.erpapp.cell.ShadowSectionCell;
import com.example.ausu.erpapp.cell.TextViewWithIcon;
import com.example.ausu.erpapp.model.ClassDetailBean;
import com.example.ausu.erpapp.model.CoachInfoBean;
import com.example.ausu.erpapp.utils.UIUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Lanxumit on 2016/9/5.
 */
public class PackageAdapter extends BaseAdapter {
    private Context mContext;
    private ClassDetailBean mResult;
    private List<Integer> mTypes;
    private CoachInfoBean mCoachInfoBean;

    public PackageAdapter(Context context) {
        this.mContext = context;
    }

    public void bindData(ClassDetailBean result) {
        this.mResult = result;
        notifyDataSetChanged();
    }

    public void bindInfo(CoachInfoBean coachInfoBean) {
        this.mCoachInfoBean = coachInfoBean;
        notifyDataSetChanged();
    }

    public void bindTypes(List<Integer> types) {
        this.mTypes = types;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTypes != null ? mTypes.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mTypes.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        int i = mTypes.get(position).intValue();
        if (i == 0) {
            return 0;
        } else if (i == 1 || i == 3) {
            return 1;
        } else if (i == 2) {
            return 2;
        } else if (i == 4 || i == 5) {
            return 3;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            if (convertView == null) {
                convertView = new TextViewWithIcon(mContext);
            }
            TextViewWithIcon textViewWithIcon = (TextViewWithIcon) convertView;
            textViewWithIcon.setValue(mResult.getProductname(), "￥" + mResult.getDiscountprice(), true, false, false);
            textViewWithIcon.setTextColor(R.color.theme_black, R.color.themeColor);
        } else if (itemViewType == 1) {
            if (convertView == null) {
                convertView = new ShadowSectionCell(mContext);
            }
            ShadowSectionCell shadowSectionCell = (ShadowSectionCell) convertView;
        } else if (itemViewType == 2) {
            if (convertView == null) {
                convertView = new PackageCoachCell(mContext);
            }
            PackageCoachCell cell = (PackageCoachCell) convertView;
        } else if (itemViewType == 3) {
            int i = mTypes.get(position).intValue();
            if (convertView == null) {
                convertView = new ClassDetailCell(mContext);
            }
            ClassDetailCell classDetailCell = (ClassDetailCell) convertView;
            if (i == 4) {
                //费用详情
                classDetailCell.setTitle("费用详情");
                LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(UIUtils.dip2px(15), UIUtils.dip2px(15), UIUtils.dip2px(15), UIUtils.dip2px(15));
                linearLayout.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView textView = new TextView(mContext);
                SpannableString spannableString = new SpannableString("总费用￥" + mResult.getDiscountprice());
                ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.theme_black));
                //将这个Span应用于指定范围的字体
                spannableString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                ForegroundColorSpan span1 = new ForegroundColorSpan(mContext.getResources().getColor(R.color.themeColor));
                spannableString.setSpan(span1, 3, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                textView.setText(spannableString);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.bottomMargin = UIUtils.dip2px(15);
                textView.setLayoutParams(layoutParams);
                linearLayout.addView(textView);
                TextView textView1 = new TextView(mContext);
                textView1.setTextColor(0xff555555);
                textView1.setText(mResult.getProductsummary());
                textView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView1.setLayoutParams(layoutParams1);
                linearLayout.addView(textView1);
                classDetailCell.addItem(position, linearLayout);
                View view = new View(mContext);
                view.setBackgroundColor(mContext.getResources().getColor(R.color.line_color));
                view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                classDetailCell.addItem(position + 1, view);
            } else {
                //套餐服务
                classDetailCell.setTitle("套餐服务");
                LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(UIUtils.dip2px(15), UIUtils.dip2px(15), UIUtils.dip2px(15), UIUtils.dip2px(15));
                linearLayout.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView textView = new TextView(mContext);
                SpannableString spannableString = new SpannableString("训练车型     " + mResult.getCar());
                ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.theme_black));
                //将这个Span应用于指定范围的字体
                spannableString.setSpan(span, 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                textView.setText(spannableString);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.bottomMargin = UIUtils.dip2px(10);
                textView.setLayoutParams(layoutParams);
                linearLayout.addView(textView);
                TextView textView1 = new TextView(mContext);
                SpannableString spannableString1 = new SpannableString("学时    " + mResult.getStudyCarrera());
                ForegroundColorSpan span1 = new ForegroundColorSpan(mContext.getResources().getColor(R.color.theme_black));
                //将这个Span应用于指定范围的字体
                spannableString1.setSpan(span1, 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                textView1.setText(spannableString1);
                textView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams1.bottomMargin = UIUtils.dip2px(10);
                textView1.setLayoutParams(layoutParams1);
                linearLayout.addView(textView1);
                TextView textView2 = new TextView(mContext);
                SpannableString spannableString2 = new SpannableString("练车方式    " + mResult.getPractiseMode());
                ForegroundColorSpan span2 = new ForegroundColorSpan(mContext.getResources().getColor(R.color.theme_black));
                //将这个Span应用于指定范围的字体
                spannableString2.setSpan(span2, 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                textView2.setText(spannableString2);
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams2.bottomMargin = UIUtils.dip2px(10);
                textView2.setLayoutParams(layoutParams2);
                linearLayout.addView(textView2);
                classDetailCell.addItem(position, linearLayout);
                View view = new View(mContext);
                view.setBackgroundColor(mContext.getResources().getColor(R.color.line_color));
                view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                classDetailCell.addItem(position + 1, view);

                LinearLayout linearLayout2 = new LinearLayout(mContext);
                linearLayout2.setOrientation(LinearLayout.VERTICAL);
                linearLayout2.setPadding(UIUtils.dip2px(15), UIUtils.dip2px(15), UIUtils.dip2px(15), UIUtils.dip2px(15));
                linearLayout2.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView textView3 = new TextView(mContext);
                textView3.setText("班车接送服务");
                textView3.setTextColor(mContext.getResources().getColor(R.color.theme_black));
                textView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams3.bottomMargin = UIUtils.dip2px(10);
                textView3.setLayoutParams(layoutParams3);
                linearLayout2.addView(textView3);
                TextView textView4 = new TextView(mContext);
                textView4.setTextColor(0xff555555);
                textView4.setText(mResult.getShcoolBusArea());
                textView4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams4.bottomMargin = UIUtils.dip2px(10);
                textView4.setLayoutParams(layoutParams4);
                linearLayout2.addView(textView4);
                TextView textView5 = new TextView(mContext);
                textView5.setText("具体上车地点请咨询客服");
                textView5.setTextColor(0xff2c81ff);
                textView5.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams5.bottomMargin = UIUtils.dip2px(10);
                textView5.setLayoutParams(layoutParams5);
                linearLayout2.addView(textView5);
                classDetailCell.addItem(position + 2, linearLayout2);
                View view1 = new View(mContext);
                view1.setBackgroundColor(mContext.getResources().getColor(R.color.line_color));
                view1.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                classDetailCell.addItem(position + 3, view1);

                TextView textView6 = new TextView(mContext);
                textView6.setPadding(UIUtils.dip2px(15), UIUtils.dip2px(15), UIUtils.dip2px(15), UIUtils.dip2px(15));
                textView6.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView6.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                SpannableString spannableString3 = new SpannableString("其他说明\n\n" + mResult.getProductdetail());
                ForegroundColorSpan span3 = new ForegroundColorSpan(mContext.getResources().getColor(R.color.theme_black));
                //将这个Span应用于指定范围的字体
                spannableString3.setSpan(span3, 0, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                textView6.setText(spannableString3);
                classDetailCell.addItem(position + 4, textView6);
            }
        }
        return convertView;
    }
}
