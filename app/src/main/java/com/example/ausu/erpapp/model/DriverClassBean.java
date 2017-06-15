package com.example.ausu.erpapp.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;

/**
 * Created by Lanxumit on 2016/7/19.
 */
public class DriverClassBean implements Serializable {
    //班的Id
    private int id;
    //班的名字
    private String productname;
    //打折价
    private double discountprice;
    //原价
    private double productprice;
    //报名人数
    private int enrollment;
    //学校名字
    private String DriverSchoolName;
    //学校id
    private int schoolid;
    //班型简介
    private String productsummary;
    //班型1描述
    private String productdetail;
    //班型特色
    private String productspecial;
    //接送方式
    private int shuttletype;
    //练车方式
    private int studytype;
    //拿证时间
    private int studyperiod;
    //一车几人
    private int personsoncar;
    //优惠截止日期
    private long discountdate;
    //联系电话
    private String contracttel;
    //费用说明
    private String feememo;

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


    public double getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(double discountprice) {
        this.discountprice = discountprice;
    }

    public double getProductprice() {
        return productprice;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public String getDriverSchoolName() {
        return DriverSchoolName;
    }

    public void setDriverSchoolName(String driverSchoolName) {
        DriverSchoolName = driverSchoolName;
    }

    public int getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(int schoolid) {
        this.schoolid = schoolid;
    }

    public String getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(String productdetail) {
        this.productdetail = productdetail;
    }

    public int getShuttletype() {
        return shuttletype;
    }

    public void setShuttletype(int shuttletype) {
        this.shuttletype = shuttletype;
    }

    public String getProductsummary() {
        return productsummary;
    }

    public void setProductsummary(String productsummary) {
        this.productsummary = productsummary;
    }

    public String getProductspecial() {
        return productspecial;
    }

    public void setProductspecial(String productspecial) {
        this.productspecial = productspecial;
    }

    public int getStudytype() {
        return studytype;
    }

    public void setStudytype(int studytype) {
        this.studytype = studytype;
    }

    public int getStudyperiod() {
        return studyperiod;
    }

    public void setStudyperiod(int studyperiod) {
        this.studyperiod = studyperiod;
    }

    public int getPersonsoncar() {
        return personsoncar;
    }

    public void setPersonsoncar(int personsoncar) {
        this.personsoncar = personsoncar;
    }

    public long getDiscountdate() {
        return discountdate;
    }

    public void setDiscountdate(long discountdate) {
        this.discountdate = discountdate;
    }

    public String getContracttel() {
        return contracttel;
    }

    public void setContracttel(String contracttel) {
        this.contracttel = contracttel;
    }

    public String getFeememo() {
        return feememo;
    }

    public void setFeememo(String feememo) {
        this.feememo = feememo;
    }

    public static DriverClassBean ToDriverClassBean(DiscountBean bean) {
        DriverClassBean driverClassBean = new DriverClassBean();
        if (bean.getId() != null && !"".equals(bean.getId())) {
            driverClassBean.setId(Integer.parseInt(bean.getId()));
        }
        driverClassBean.setDriverSchoolName(bean.getSchoolname());
        driverClassBean.setEnrollment(bean.getEnrollment());
        driverClassBean.setDiscountdate(bean.getDiscountprice());
        return driverClassBean;
    }


    public static DriverClassBean jsomToEntity(JsonNode jsonNode) {
        DriverClassBean driverClassBean = new DriverClassBean();
        driverClassBean.setId(jsonNode.path("id").asInt());
        if (jsonNode.has("discountdate")) {
            driverClassBean.setDiscountdate(jsonNode.path("discountdate").asLong());
        }
        driverClassBean.setProductname(jsonNode.path("productname").asText());
        if (jsonNode.has("discountprice")) {
            driverClassBean.setDiscountprice(jsonNode.path("discountprice").asDouble());
        }
        if (jsonNode.has("productprice")) {
            driverClassBean.setProductprice(jsonNode.path("productprice").asDouble());
        }
        driverClassBean.setEnrollment(jsonNode.path("enrollment").asInt());
        if (jsonNode.has("schoolid")) {
            driverClassBean.setSchoolid(jsonNode.path("schoolid").asInt());
        }
        driverClassBean.setProductsummary(jsonNode.path("productsummary").asText());
        if (jsonNode.has("productdetail")) {
            driverClassBean.setProductdetail(jsonNode.path("productdetail").asText());
        }
        if (jsonNode.has("shuttletype")) {
            driverClassBean.setShuttletype(jsonNode.path("shuttletype").asInt());
        }
        if (jsonNode.has("studytype")) {
            driverClassBean.setStudytype(jsonNode.path("studytype").asInt());
        }
        if (jsonNode.has("studyperiod")) {
            driverClassBean.setStudyperiod(jsonNode.path("studyperiod").asInt());
        }
        if (jsonNode.has("personsoncar")) {
            driverClassBean.setPersonsoncar(jsonNode.path("personsoncar").asInt());
        }
        if (jsonNode.has("contracttel")) {
            driverClassBean.setContracttel(jsonNode.path("contracttel").asText());
        }
        if (jsonNode.has("feememo")) {
            driverClassBean.setFeememo(jsonNode.path("feememo").asText());
        }
        return driverClassBean;
    }
}