package com.example.ausu.erpapp.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Lanxumit on 2016/9/5.
 */
public class ClassDetailBean {
    private int id;
    private String productname;                     //班级名称
    private String contracttel;                       //咨询电话	-----班级咨询
    private int enrollment;                                    //报名人数
    private String discountprice;                              //折扣价
    private String productprice;                                 //市场价
    private String feememo;      //费用说明	-------班级费用
    private String car;                            //训练车型
    private String studyCarrera;     //学时说明--后台组装
    private String practiseMode;                           //一车几人--练车方式
    private String shcoolBusArea;        //班车接送区域----增加驾校的属性
    private String productdetail;                                 //其他说明:-----班型描述//可能有换行
    private String productsummary;

    public String getProductsummary() {
        return productsummary;
    }

    public void setProductsummary(String productsummary) {
        this.productsummary = productsummary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getContracttel() {
        return contracttel;
    }

    public void setContracttel(String contracttel) {
        this.contracttel = contracttel;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getFeememo() {
        return feememo;
    }

    public void setFeememo(String feememo) {
        this.feememo = feememo;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getStudyCarrera() {
        return studyCarrera;
    }

    public void setStudyCarrera(String studyCarrera) {
        this.studyCarrera = studyCarrera;
    }

    public String getPractiseMode() {
        return practiseMode;
    }

    public void setPractiseMode(String practiseMode) {
        this.practiseMode = practiseMode;
    }

    public String getShcoolBusArea() {
        return shcoolBusArea;
    }

    public void setShcoolBusArea(String shcoolBusArea) {
        this.shcoolBusArea = shcoolBusArea;
    }

    public String getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(String productdetail) {
        this.productdetail = productdetail;
    }

    public static ClassDetailBean jsonToEntity(JsonNode jsonNode) {
        ClassDetailBean classDetailBean = new ClassDetailBean();
        classDetailBean.setId(jsonNode.path("id").asInt());
        classDetailBean.setContracttel(jsonNode.path("contracttel").asText());
        classDetailBean.setProductname(jsonNode.path("productname").asText());
        classDetailBean.setDiscountprice(jsonNode.path("discountprice").asText());
        classDetailBean.setCar(jsonNode.path("car").asText());
        classDetailBean.setEnrollment(jsonNode.path("enrollment").asInt());
        classDetailBean.setProductprice(jsonNode.path("productprice").asText());
        classDetailBean.setProductdetail(jsonNode.path("productdetail").asText());
        classDetailBean.setFeememo(jsonNode.path("feememo").asText());
        classDetailBean.setPractiseMode(jsonNode.path("practiseMode").asText());
        classDetailBean.setStudyCarrera(jsonNode.path("studyCarrera").asText());
        classDetailBean.setShcoolBusArea(jsonNode.path("shcoolBusArea").asText());
        classDetailBean.setProductsummary(jsonNode.path("productsummary").asText());
        return classDetailBean;
    }
}
