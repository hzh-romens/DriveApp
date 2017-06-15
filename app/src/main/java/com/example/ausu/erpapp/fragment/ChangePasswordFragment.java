package com.example.ausu.erpapp.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.NetUtils;
import com.example.ausu.erpapp.utils.ToastUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Lanxumit on 2016/10/8.
 */
public class ChangePasswordFragment extends Fragment implements View.OnClickListener {
    private MaterialEditText phoneView, codeView, passwordView, againView;
    private TextView sure;
    private FrameLayout codeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_changepwd, null);
        phoneView = (MaterialEditText) view.findViewById(R.id.edit_phone);
        codeView = (MaterialEditText) view.findViewById(R.id.identifyingCode);
        passwordView = (MaterialEditText) view.findViewById(R.id.edit_psw);
        againView = (MaterialEditText) view.findViewById(R.id.edit_again);
        sure = (TextView) view.findViewById(R.id.register);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListener();
    }

    private void setListener() {
        sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                if (phoneView.getText().toString() == null || "".equals(phoneView.getText().toString()) ||
                        codeView.getText().toString() == null || "".equals(codeView.getText().toString()) ||
                        passwordView.getText().toString() == null || "".equals(passwordView.getText().toString()) ||
                        againView.getText().toString() == null || "".equals(againView.getText().toString())) {
                    Toast.makeText(getActivity(), "你还未填写完", Toast.LENGTH_SHORT).show();
                } else if (!passwordView.getText().toString().equals(againView.getText().toString())) {
                    Toast.makeText(getActivity(), "您两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    //进行修改密码
                    toChange(phoneView.getText().toString(), codeView.getText().toString(), passwordView.getText().toString(), againView.getText().toString());
                }
                break;
        }

    }


    private int time;
    private Timer timer;
    private final Object object = new Object();
    private Handler handler = new Handler();

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

    private void toChange(String phone, String oldPsw, String newPsw, String copyPsw) {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("loginname", phone);
        hashMap.put("oldpsd", oldPsw);
        hashMap.put("newpsd", newPsw);
        hashMap.put("source", "1");
        netUtils.connectServer(hashMap, Config.BASE_URL + "/users/chgpsd");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                try {
                    if (result.contains("retcode")) {
                        JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                        String retcode = jsonNode.path("retcode").asText();
                        if ("000".equals(retcode)) {
                            //修改密码成功
                            ToastUtils.getInsatnce().showToastWithImage(getActivity(), R.mipmap.ic_password_complete, "修改成功", Toast.LENGTH_SHORT);
                            getActivity().finish();
                        } else {
                            //修改密码失败
                            ToastUtils.getInsatnce().showToastWithImage(getActivity(), R.mipmap.ic_password_complete, "修改成功", Toast.LENGTH_SHORT);
                        }
                    } else {
                        ToastUtils.getInsatnce().showToastWithImage(getActivity(), R.mipmap.ic_password_complete, "修改失败", Toast.LENGTH_SHORT);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
