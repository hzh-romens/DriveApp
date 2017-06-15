package com.example.ausu.erpapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.UserBean;
import com.example.ausu.erpapp.utils.AccountUtils;
import com.example.ausu.erpapp.utils.NetUtils;
import com.example.ausu.erpapp.utils.OkHttpClientManager;

import java.util.HashMap;

import okhttp3.Request;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView pswClear, phoneClear, pswShow, close;
    private String phoneStr = null, pswStr = null;
    private EditText pswEdit, phoneEdit;
    private boolean isShow = true;
    private TextView sureBtn, registerBnt, lostBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addListener();
    }


    private void initView() {
        phoneEdit = (EditText) findViewById(R.id.edit_phone);
        phoneClear = (ImageView) findViewById(R.id.number_clear);
        pswEdit = (EditText) findViewById(R.id.edit_psw);
        pswClear = (ImageView) findViewById(R.id.psw_clean);
        pswShow = (ImageView) findViewById(R.id.psw_show);
        sureBtn = (TextView) findViewById(R.id.sure);
        registerBnt = (TextView) findViewById(R.id.register);
        lostBtn = (TextView) findViewById(R.id.lostPwd);
        close = (ImageView) findViewById(R.id.close);
    }

    private void addListener() {
        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneStr = phoneEdit.getText().toString();
            }
        });
        pswEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pswStr = pswEdit.getText().toString();
            }
        });
        phoneEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //暂时不显示清除按钮
                    // phoneClear.setVisibility(View.VISIBLE);
                } else {
                    if (phoneStr != null) {
                        //暂时不显示清除按钮
                        //phoneClear.setVisibility(View.VISIBLE);
                    } else {
                        phoneClear.setVisibility(View.GONE);
                    }
                }
            }
        });
        pswEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //暂时不显示清除按钮
                    //pswClear.setVisibility(View.VISIBLE);
                } else {
                    if (pswStr != null) {
                        //暂时不显示清除按钮
                        //  pswClear.setVisibility(View.VISIBLE);
                    } else {
                        pswClear.setVisibility(View.GONE);
                    }
                }
            }
        });
        sureBtn.setOnClickListener(this);
        phoneClear.setOnClickListener(this);
        pswClear.setOnClickListener(this);
        pswShow.setOnClickListener(this);
        registerBnt.setOnClickListener(this);
        lostBtn.setOnClickListener(this);
        close.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.number_clear:
                phoneEdit.setText("");
                break;
            case R.id.psw_clean:
                pswEdit.setText("");
                break;
            case R.id.psw_show:
                if (isShow) {
                    pswEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isShow = !isShow;
                } else {
                    pswEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isShow = !isShow;
                }
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.lostPwd:
                break;
            case R.id.sure:
                if (phoneEdit.getText().toString() == null || pswEdit.getText().toString() == null || "".equals(phoneEdit.getText().toString()) || "".equals(pswEdit.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "您的账号或密码未填写", Toast.LENGTH_SHORT).show();
                } else {
                    String cuid;
                    cuid = AccountUtils.instance().getUuid("cuid");
                    NetUtils netUtils = new NetUtils();
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("loginname", phoneEdit.getText().toString());
                    hashMap.put("password", pswEdit.getText().toString());
                    hashMap.put("latitude", "39.26");
                    hashMap.put("longitude ", "115.25");
                    hashMap.put("cuid", cuid);
                    hashMap.put("source", "1");
                    OkHttpClientManager.getAsyn(Config.BASE_URL + "/users/login", new OkHttpClientManager.ResultCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(Object response) {
                            if (response.toString().contains("username")) {
                                UserBean userBean = UserBean.JsonToEntity(response.toString());
                                boolean b = AccountUtils.instance().saveAccount(userBean);
                                AccountUtils.instance().saveStatus("notify", true);
                                if (b) {
                                    finish();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "您的账号异常", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, hashMap);
                }
                break;
        }
    }
}
