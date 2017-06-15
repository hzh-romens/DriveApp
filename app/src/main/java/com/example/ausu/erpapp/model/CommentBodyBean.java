package com.example.ausu.erpapp.model;

import java.util.List;

/**
 * Created by Lanxumit on 2016/8/6.
 */
public class CommentBodyBean {
    //头像地址
    private String avatar;
    //名字
    private String name;
    //帖子的主要内容
    private String body;
    //发表的图片集合
    private List<String> images;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
