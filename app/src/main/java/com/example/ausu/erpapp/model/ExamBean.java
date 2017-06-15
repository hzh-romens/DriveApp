package com.example.ausu.erpapp.model;

/**
 * Created by Lanxumit on 2016/7/28.
 */
public class ExamBean {
    private String name;
    private String examID;
    private boolean isSingup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public boolean isSingup() {
        return isSingup;
    }

    public void setIsSingup(boolean isSingup) {
        this.isSingup = isSingup;
    }
}
