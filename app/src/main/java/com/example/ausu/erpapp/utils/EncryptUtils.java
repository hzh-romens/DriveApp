package com.example.ausu.erpapp.utils;

import android.util.Log;

import com.forange.common.util.StringUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by Lanxumit on 2016/8/20.
 * 加密工具类
 */
public class EncryptUtils {
    private static EncryptUtils encrypInstance = null;
    public static Object sync = new Object();

    public static synchronized EncryptUtils instance() {
        if (encrypInstance == null) {
            synchronized (EncryptUtils.class) {
                if (encrypInstance == null) {
                    encrypInstance = new EncryptUtils();
                }
            }
        }
        return encrypInstance;
    }

    //生成多少位的随机字符串
    public String getUuid(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public int start;
    public int end;

    //生成suid
    public String getRandomCuid() {
        String cuid = AccountUtils.instance().getUuid("cuid");
        Random random = new Random();
        int start = random.nextInt(36);
        int length = cuid.length();
        int i = length - start - 4;
        int end = random.nextInt(i) + start + 4;
        this.start = start;
        this.end = end;
        if (start == 35) {
            return cuid.substring(start);
        } else {
            return cuid.substring(start, end+1);
        }
    }

    public String getSecurity(String value, int start, int end) {
        StringBuilder sb = new StringBuilder();
        String aesStr = StringUtil.forangeEncrypt1(value);
        if (start < 10) {
            sb.append("0");
            sb.append(start);
        } else {
            sb.append(start);
        }
        if (end < 10) {
            sb.append("0");
            sb.append(end);
        } else {
            sb.append(end);
        }
        sb.append(aesStr);
        return sb.toString();
    }

}
