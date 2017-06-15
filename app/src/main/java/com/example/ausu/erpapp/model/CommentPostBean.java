package com.example.ausu.erpapp.model;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/6.
 * 评论的帖子
 */
public class CommentPostBean {

    //名字
    private String name;
    //发布时间
    private long time;
    //评论的人的类型（区分楼主和游客）
    private int postType;
    //头像地址
    private String avater;
    //评论的内容的集合
    private List<String> commemts;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public List<String> getCommemts() {
        return commemts;
    }

    public void setCommemts(List<String> commemts) {
        this.commemts = commemts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
