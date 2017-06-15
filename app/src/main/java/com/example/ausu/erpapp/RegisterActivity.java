package com.example.ausu.erpapp;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.NetUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by AUSU on 2016/7/4.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private MaterialEditText phoneView, codeView, passwordView, againView;
    private TextView getCode, register, agreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpView();
        setListener();
    }

    private void setUpView() {
        phoneView = (MaterialEditText) findViewById(R.id.edit_phone);
        codeView = (MaterialEditText) findViewById(R.id.identifyingCode);
        passwordView = (MaterialEditText) findViewById(R.id.edit_psw);
        againView = (MaterialEditText) findViewById(R.id.edit_again);
        getCode = (TextView) findViewById(R.id.getCode);
        register = (TextView) findViewById(R.id.register);
        agreement = (TextView) findViewById(R.id.agreement);
        SpannableString spannableString = new SpannableString("您已同意快乐学车《用户使用协议》");
        ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.theme_light_black));
        spannableString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan span1 = new ForegroundColorSpan(0xff555555);
        spannableString.setSpan(span1, 4, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        UnderlineSpan span2 = new UnderlineSpan();
        spannableString.setSpan(span2, 4, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        agreement.setText(spannableString);

    }

    private void setListener() {
        getCode.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getCode:
                time = 60;
                createTimer();
                if (phoneView.getText().toString() != null && !"".equals(phoneView.getText().toString())) {
                    getIndefineCode(phoneView.getText().toString());
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register:
                if (phoneView.getText().toString() == null || "".equals(phoneView.getText().toString()) ||
                        codeView.getText().toString() == null || "".equals(codeView.getText().toString()) ||
                        passwordView.getText().toString() == null || "".equals(passwordView.getText().toString()) ||
                        againView.getText().toString() == null || "".equals(againView.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "您的信息填写不完整", Toast.LENGTH_SHORT).show();
                } else if (!passwordView.getText().toString().equals(againView.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "您密码输入不正确", Toast.LENGTH_SHORT).show();
                } else {
                    //进行注册
                    toRegister(phoneView.getText().toString(), codeView.getText().toString(), passwordView.getText().toString(), againView.getText().toString());
                }
                break;
        }
    }

    private void toRegister(String phone, String code, String psw, String copyPsw) {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("loginname", phone);
        hashMap.put("password", psw);
        hashMap.put("verifycode", code);
        hashMap.put("source", "1");
        netUtils.connectServer(hashMap, Config.BASE_URL + "/users/register");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                Log.i("注册信息-----", result);
            }
        });
    }

    private int time;
    private Timer timer;
    private final Object object = new Object();
    private Handler handler = new Handler();

    public void createTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (time > 0) {
                            getCode.setClickable(false);
                            getCode.setText("已发送(" + time + ")");
                            time--;
                        } else {
                            getCode.setClickable(true);
                            //initGradientDrawable(R.color.theme_primary);
                            //textView.setBackground(textDrawable);
                            getCode.setText("再次获取");
                            destoryTimer();
                        }

                    }
                });

            }
        }, 0, 1000);
    }


    private GradientDrawable textDrawable;

    private void initGradientDrawable(int colorId) {
        textDrawable = new GradientDrawable();
        textDrawable.setCornerRadius(8);
        textDrawable.setColor(getResources().getColor(colorId));
    }

    public void destoryTimer() {
        synchronized (object) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

    private void getIndefineCode(String phoneNumber) {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("mobile", phoneNumber);
        hashMap.put("source", "1");
        hashMap.put("type", "1");
        netUtils.connectServer(hashMap, Config.BASE_URL + "/users/sendverify");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                try {
                    JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                    String retcode = jsonNode.path("retcode").asText();
                    if ("000".equals(retcode)) {
                        //短信验证成功
                    } else {
                        //短信验证失败
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("信息接受测试------", result);
            }
        });
    }
}
