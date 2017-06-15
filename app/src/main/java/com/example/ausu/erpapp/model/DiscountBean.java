package com.example.ausu.erpapp.model;

import com.example.ausu.erpapp.utils.JacksonMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Created by Lanxumit on 2016/8/31.
 */
public class DiscountBean {
    private String id;
    private String schoolname;
    private Long discountdate;
    private String avatar;
    private Long productprice;
    private Long discountprice;
    private int enrollment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public Long getDiscountdate() {
        return discountdate;
    }

    public void setDiscountdate(Long discountdate) {
        this.discountdate = discountdate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getProductprice() {
        return productprice;
    }

    public void setProductprice(Long productprice) {
        this.productprice = productprice;
    }

    public Long getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(Long discountprice) {
        this.discountprice = discountprice;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public static DiscountBean jsonToEntity(JsonNode jsonNode) {
        DiscountBean discountBean = new DiscountBean();
        //  JsonNode jsonNode = JacksonMapper.getInstance().readTree(json);
        discountBean.setId(jsonNode.path("classId").asText());
        discountBean.setSchoolname(jsonNode.path("schoolname").asText());
        discountBean.setDiscountdate(jsonNode.path("discountdate").asLong());
        discountBean.setAvatar(jsonNode.path("avatar").asText());
        discountBean.setEnrollment(jsonNode.path("enrollment").asInt());
        discountBean.setProductprice(jsonNode.path("productprice").asLong());
        discountBean.setDiscountprice(jsonNode.path("discountprice").asLong());
        return discountBean;
    }
}
