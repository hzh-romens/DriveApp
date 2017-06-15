package com.example.ausu.erpapp.model;

/**
 * Created by Lanxumit on 2016/7/23.
 */
public class SingUpInfo {
    //班的Id
    private int classId;
    //班的名字
    private String className;
    //价格
    private double price;
    //学校名字
    private String DriverSchoolName;
    //班级教练名字
    private String coath;

    //学生名字
    private String studentName;
    //电话号码
    private String phoneNumber;
    //性别
    private int sex;
    //生日
    private String birthday;
    //是否是本地人
    private boolean isNative;
    //省市区
    private String address;
    //详细地址
    private String street;

    private String pay;

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDriverSchoolName() {
        return DriverSchoolName;
    }

    public void setDriverSchoolName(String driverSchoolName) {
        DriverSchoolName = driverSchoolName;
    }

    public String getCoath() {
        return coath;
    }

    public void setCoath(String coath) {
        this.coath = coath;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isNative() {
        return isNative;
    }

    public void setIsNative(boolean isNative) {
        this.isNative = isNative;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public static SingUpInfo toSingUpInfo(DriverClassBean driverClassBean) {
        SingUpInfo singUpInfo = new SingUpInfo();
        singUpInfo.setClassName(driverClassBean.getProductname());
        singUpInfo.setClassId(driverClassBean.getId());
        singUpInfo.setDriverSchoolName(driverClassBean.getDriverSchoolName());
        singUpInfo.setSex(-1);
        singUpInfo.setIsNative(false);
        singUpInfo.setBirthday("");
        singUpInfo.setAddress("");
        singUpInfo.setPrice(driverClassBean.getProductprice());
        return singUpInfo;
    }
}
