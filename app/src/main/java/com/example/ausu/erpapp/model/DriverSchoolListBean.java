package com.example.ausu.erpapp.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/9/3.
 * 驾校的数据模型
 */
public class DriverSchoolListBean {
    private int id;
    private String schoolname;
    private double longtitude;
    private double altitude;
    //路径
    private String shuttleroute;
    private String bizadress;
    private String bizcontry;
    private String bizprovince;
    private String bizcity;
    private String bizdistrict;
    private int distance;
    private String avatar;
    private String summaryscore;
    //学校描述
    private String schoolmemo;

    private List<DriverClassBean> classList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getShuttleroute() {
        return shuttleroute;
    }

    public void setShuttleroute(String shuttleroute) {
        this.shuttleroute = shuttleroute;
    }

    public String getBizadress() {
        return bizadress;
    }

    public void setBizadress(String bizadress) {
        this.bizadress = bizadress;
    }

    public String getBizcontry() {
        return bizcontry;
    }

    public void setBizcontry(String bizcontry) {
        this.bizcontry = bizcontry;
    }

    public String getBizprovince() {
        return bizprovince;
    }

    public void setBizprovince(String bizprovince) {
        this.bizprovince = bizprovince;
    }

    public String getBizcity() {
        return bizcity;
    }

    public void setBizcity(String bizcity) {
        this.bizcity = bizcity;
    }

    public String getBizdistrict() {
        return bizdistrict;
    }

    public void setBizdistrict(String bizdistrict) {
        this.bizdistrict = bizdistrict;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSummaryscore() {
        return summaryscore;
    }

    public void setSummaryscore(String summaryscore) {
        this.summaryscore = summaryscore;
    }

    public List<DriverClassBean> getClassList() {
        return classList;
    }

    public void setClassList(List<DriverClassBean> classList) {
        this.classList = classList;
    }

    public String getSchoolmemo() {
        return schoolmemo;
    }

    public void setSchoolmemo(String schoolmemo) {
        this.schoolmemo = schoolmemo;
    }

    public static DriverSchoolListBean jsonToEntity(JsonNode jsonNode) {
        DriverSchoolListBean driverSchoolListBean = new DriverSchoolListBean();
        driverSchoolListBean.setId(jsonNode.path("id").asInt());
        driverSchoolListBean.setSchoolname(jsonNode.path("schoolname").asText());
        driverSchoolListBean.setAvatar(jsonNode.path("avatar").asText());
        driverSchoolListBean.setBizadress(jsonNode.path("bizadress").asText());
        driverSchoolListBean.setDistance(jsonNode.path("distance").asInt());
        JsonNode classList = jsonNode.path("classList");
        if (classList != null && classList.size() != 0) {
            List<DriverClassBean> driverClassBeans = new ArrayList<DriverClassBean>();
            for (int i = 0; i < classList.size(); i++) {
                DriverClassBean driverClassBean = DriverClassBean.jsomToEntity(classList.get(i));
                driverClassBeans.add(driverClassBean);
            }
            driverSchoolListBean.setClassList(driverClassBeans);
        }
        return driverSchoolListBean;
    }
}
