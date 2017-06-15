package com.example.ausu.erpapp.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/9/27.
 */
public class PracticeItemBean {
    private int id;//科目id
    private String coursename;        //科目名称
    private int coursehours;               //科目预计学时
    private int mystudyhours;               //我的学习时间，备用
    private int isFinished;                  //我是否学习完成，备用

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getCoursehours() {
        return coursehours;
    }

    public void setCoursehours(int coursehours) {
        this.coursehours = coursehours;
    }

    public int getMystudyhours() {
        return mystudyhours;
    }

    public void setMystudyhours(int mystudyhours) {
        this.mystudyhours = mystudyhours;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public static List<PracticeItemBean> JsonToList(JsonNode jsonNode) {
        List<PracticeItemBean> results = new ArrayList<PracticeItemBean>();
        for (int i = 0; i < jsonNode.size(); i++) {
            PracticeItemBean practiceItemBean = new PracticeItemBean();
            JsonNode jsonNode1 = jsonNode.get(i);
            practiceItemBean.setId(jsonNode1.path("id").asInt());
            practiceItemBean.setCoursename(jsonNode1.path("coursename").asText());
            practiceItemBean.setCoursehours(jsonNode1.path("coursehours").asInt());
            practiceItemBean.setMystudyhours(jsonNode1.path("mystudyhours").asInt());
            practiceItemBean.setIsFinished(jsonNode1.path("isFinished").asInt());
            results.add(practiceItemBean);
        }
        return results;
    }
}
