package com.example.ausu.erpapp.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Lanxumit on 2016/7/25.
 */
public class CommentBean {
    private int studentid;
    private String avatar;
    private String studentname;
    private int stars;
    private String evaluateContent;
    private long time;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static CommentBean jsonToEntity(JsonNode jsonNode) {
        CommentBean commentBean = new CommentBean();
        commentBean.setAvatar(jsonNode.path("avatar").asText());
        commentBean.setEvaluateContent(jsonNode.path("evaluateContent").asText());
        commentBean.setStars(jsonNode.path("stars").asInt());
        commentBean.setStudentid(jsonNode.path("studentid").asInt());
        commentBean.setStudentname(jsonNode.path("studentname").asText());
        if (jsonNode.has("time")) {
            commentBean.setTime(jsonNode.path("time").asLong());
        }
        return commentBean;
    }
}
