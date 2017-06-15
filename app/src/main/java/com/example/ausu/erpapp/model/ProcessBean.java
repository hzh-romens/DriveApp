package com.example.ausu.erpapp.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Lanxumit on 2016/9/22.
 */
public class ProcessBean {
    private int userid;                                //用户id，备用
    private String username;                          //用户姓名，备用
    private int studentstatus;                            //用户状态： 0 锁定  1 已报名  2 信息不完整  3 未报名
    private SchoolBean school;
    private int studyhours;                            //累计练习学时
    private int ordercount;                           //累计预约次数
    private int continuedmonth;                         //报名历时月
    private int continuedday;                           //报名历时非整月的天数

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStudentstatus() {
        return studentstatus;
    }

    public void setStudentstatus(int studentstatus) {
        this.studentstatus = studentstatus;
    }

    public SchoolBean getSchool() {
        return school;
    }

    public void setSchool(SchoolBean school) {
        this.school = school;
    }

    public int getStudyhours() {
        return studyhours;
    }

    public void setStudyhours(int studyhours) {
        this.studyhours = studyhours;
    }

    public int getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(int ordercount) {
        this.ordercount = ordercount;
    }

    public int getContinuedmonth() {
        return continuedmonth;
    }

    public void setContinuedmonth(int continuedmonth) {
        this.continuedmonth = continuedmonth;
    }

    public int getContinuedday() {
        return continuedday;
    }

    public void setContinuedday(int continuedday) {
        this.continuedday = continuedday;
    }

    public static ProcessBean jsonToEntity(JsonNode jsonNode) {
        ProcessBean processBean = new ProcessBean();
        processBean.setUserid(jsonNode.path("userid").asInt());
        processBean.setUsername(jsonNode.path("username").asText());
        processBean.setContinuedday(jsonNode.path("continuedday").asInt());
        processBean.setStudentstatus(jsonNode.path("studentstatus").asInt());
        processBean.setContinuedmonth(jsonNode.path("studyhours").asInt());
        processBean.setStudyhours(jsonNode.path("studyhours").asInt());
        processBean.setOrdercount(jsonNode.path("ordercount").asInt());
        JsonNode school = jsonNode.path("school");
        SchoolBean schoolBean = SchoolBean.jsonToSchool(school);
        processBean.setSchool(schoolBean);
        return processBean;
    }

    public static class SchoolBean {
        private int id;
        private String schoolName;
        private String bizAdress;
        private Long longitude;
        private Long latitude;

        public static SchoolBean jsonToSchool(JsonNode jsonNode) {
            SchoolBean schoolBean = new SchoolBean();
            schoolBean.setId(jsonNode.path("id").asInt());
            schoolBean.setSchoolName(jsonNode.path("schoolName").asText());
            schoolBean.setBizAdress(jsonNode.path("bizAdress").asText());
            schoolBean.setLongitude(jsonNode.path("longitude").asLong());
            schoolBean.setLatitude(jsonNode.path("latitude").asLong());
            return schoolBean;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getBizAdress() {
            return bizAdress;
        }

        public void setBizAdress(String bizAdress) {
            this.bizAdress = bizAdress;
        }

        public Long getLongitude() {
            return longitude;
        }

        public void setLongitude(Long longitude) {
            this.longitude = longitude;
        }

        public Long getLatitude() {
            return latitude;
        }

        public void setLatitude(Long latitude) {
            this.latitude = latitude;
        }
        //        {                                  //所报驾校信息，如果未报名（studentstatus!=1），则为空
//            "id":13,                                //所报驾校id
//                "schoolName":"伟达驾校",                //所报驾校名称
//                "bizAdress":"北京市海淀区北清路38号院", //所报驾校学车地点
//                "longitude":116.679722,                 //所报驾校坐标经度
//                "latitude":39.943889                    //所报驾校坐标纬度
//        }
    }
}
