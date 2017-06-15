package com.example.ausu.erpapp.model;

import java.util.List;

/**
 * Created by Lanxumit on 2016/7/19.
 */
public class FilterBean {
    //学校Id
    private String driverSchoolId;
    //学校名字
    private String driverSchoolName;
    //学校图片url
    private String avatar;
    //学校地址
    private String address;
    //距离
    private String distance;
    private List<DriverClassBean> driverClasses;

    public String getDriverSchoolId() {
        return driverSchoolId;
    }

    public void setDriverSchoolId(String driverSchoolId) {
        this.driverSchoolId = driverSchoolId;
    }

    public String getDriverSchoolName() {
        return driverSchoolName;
    }

    public void setDriverSchoolName(String driverSchoolName) {
        this.driverSchoolName = driverSchoolName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<DriverClassBean> getDriverClasses() {
        return driverClasses;
    }

    public void setDriverClasses(List<DriverClassBean> driverClasses) {
        this.driverClasses = driverClasses;
    }


}
