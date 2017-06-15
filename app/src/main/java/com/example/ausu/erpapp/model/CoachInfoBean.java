package com.example.ausu.erpapp.model;

import com.example.ausu.erpapp.utils.JacksonMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Lanxumit on 2016/7/25.
 */
public class CoachInfoBean implements Serializable {
    private int id;
    private String username;
    private int teachage;
    private int gender;
    private String avatar;
    private String passratio;
    private int avgachieveperiod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTeachage() {
        return teachage;
    }

    public void setTeachage(int teachage) {
        this.teachage = teachage;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassratio() {
        return passratio;
    }

    public void setPassratio(String passratio) {
        this.passratio = passratio;
    }

    public int getAvgachieveperiod() {
        return avgachieveperiod;
    }

    public void setAvgachieveperiod(int avgachieveperiod) {
        this.avgachieveperiod = avgachieveperiod;
    }

    public static CoachInfoBean jsonToEntity(JsonNode jsonNode) {
        CoachInfoBean coachInfoBean = new CoachInfoBean();
        //JsonNode jsonNode = JacksonMapper.getInstance().readTree(json);
        if (jsonNode.has("username")) {
            coachInfoBean.setUsername(jsonNode.path("username").asText());
        }
        if (jsonNode.has("teachage")) {
            coachInfoBean.setTeachage(jsonNode.path("teachage").asInt());
        }
        if (jsonNode.has("avgachieveperiod")) {
            coachInfoBean.setAvgachieveperiod(jsonNode.path("avgachieveperiod").asInt());
        }
        if (jsonNode.has("passratio")) {
            coachInfoBean.setPassratio(jsonNode.path("passratio").asText());
        }
        coachInfoBean.setId(jsonNode.path("id").asInt());
        coachInfoBean.setAvatar(jsonNode.path("avatar").asText());
        if (jsonNode.has("gender")) {
            coachInfoBean.setGender(jsonNode.path("gender").asInt());
        }

        return coachInfoBean;
    }
}
