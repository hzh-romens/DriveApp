package com.example.ausu.erpapp.model;

import com.example.ausu.erpapp.utils.JacksonMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Created by Lanxumit on 2016/8/20.
 */
public class UserBean {
    private String userName;
    private String mobile;
    private String suid;
    private int gender;
    private int userType;
    private int userId;
    //字段为1 代表  已经在其他位置登录，那么提示信息，并且后台断开登录
    private int duplicatelogin;
    private String duplicatemsg;

    public int getDuplicatelogin() {
        return duplicatelogin;
    }

    public void setDuplicatelogin(int duplicatelogin) {
        this.duplicatelogin = duplicatelogin;
    }

    public String getDuplicatemsg() {
        return duplicatemsg;
    }

    public void setDuplicatemsg(String duplicatemsg) {
        this.duplicatemsg = duplicatemsg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static UserBean JsonToEntity(String json) {
        UserBean userBean = new UserBean();
        try {
            JsonNode jsonNode = JacksonMapper.getInstance().readTree(json);
            userBean.setUserName(jsonNode.path("userName").asText());
            userBean.setMobile(jsonNode.path("mobile").asText());
            userBean.setSuid(jsonNode.path("suid").asText());
            userBean.setGender(jsonNode.path("gender").asInt());
            userBean.setUserId(jsonNode.path("userid").asInt());
            userBean.setUserType(jsonNode.path("userType").asInt());
            if (jsonNode.has("duplicatelogin")) {
                userBean.setDuplicatelogin(jsonNode.path("duplicatelogin").asInt());
            }
            if (jsonNode.has("duplicatemsg")) {
                userBean.setDuplicatemsg(jsonNode.path("duplicatemsg").asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userBean;
    }
}
