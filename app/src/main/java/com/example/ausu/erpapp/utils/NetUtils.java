package com.example.ausu.erpapp.utils;

import android.util.Log;
import android.widget.Toast;

import com.example.ausu.erpapp.MyApplication;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.UserBean;

import java.util.HashMap;

import okhttp3.Request;

/**
 * Created by Lanxumit on 2016/8/31.
 */
public class NetUtils {

    private HashMap<String, String> mParams;
    private String mRequestUrl;


    private CallBackListener mListener;


    public interface CallBackListener {
        void CallBackResult(String result);
    }

    public void setCallBackListener(CallBackListener listener) {
        this.mListener = listener;
    }


    public void connectServer(HashMap<String, String> params, String requestUrl) {
        this.mParams = params;
        this.mRequestUrl = requestUrl;
        //请求数据时需要的参数，用map传递过来
        if (AccountUtils.instance().getUuid("suid") != null && !"".equals(AccountUtils.instance().getUuid("suid"))) {
            requestData();
        } else {
            //如果连接不成功，则弹出提示框，让用户去选择是否重新连接
            final HashMap<String, String> map = new HashMap<String, String>();
            String cuid;
            if (AccountUtils.instance().getUuid("cuid") != null && !"".equals(AccountUtils.instance().getUuid("cuid"))) {
                cuid = AccountUtils.instance().getUuid("cuid");
            } else {
                cuid = EncryptUtils.instance().getUuid(40);
            }
            AccountUtils.instance().saveUuid("cuid", cuid);
            map.put("cuid", cuid);
            map.put("source", "1");
            connect(map);
        }
    }


    public void connect(final HashMap<String, String> map) {
        OkHttpClientManager.getAsyn(Config.CONNECT_URL, new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("connect错误信息-----", e.toString());
            }

            @Override
            public void onResponse(Object response) {
                UserBean userBean = UserBean.JsonToEntity(response.toString());
                boolean b = AccountUtils.instance().saveAccount(userBean);
                if (b) {
                    //连接成功，保存用户信息成功
                    //先判断是否有suid，如果有，生成security，继续二次请求，如果没有
                    if (AccountUtils.instance().getUuid("suid") != null && !"".equals(AccountUtils.instance().getUuid("suid"))) {
                        requestData();
                    } else {
                        //如果连接不成功，则弹出提示框，让用户去选择是否重新连接
                        connect(map);
                    }

                } else {

                    Toast.makeText(MyApplication.applicationContext, "连接异常", Toast.LENGTH_SHORT).show();
                }
            }
        }, map);
    }

    public void requestData() {
        //在这里拼装一下url和参数，形成一个新的url
        StringBuffer stringBuffer = new StringBuffer();
        for (String key : mParams.keySet()) {
            stringBuffer.append(key);
            stringBuffer.append("=");
            stringBuffer.append(mParams.get(key));
            stringBuffer.append("&");
        }
        String requestUrl = mRequestUrl;
        if (stringBuffer != null && stringBuffer.length() != 0) {
            StringBuffer delete = stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
            requestUrl = mRequestUrl + "?" + delete;
        }
        OkHttpClientManager.getAsyn_request(requestUrl, new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("request错误信息-----", e.toString());
            }

            @Override
            public void onResponse(Object response) {
                mListener.CallBackResult(response.toString());
            }
        });
    }
}
