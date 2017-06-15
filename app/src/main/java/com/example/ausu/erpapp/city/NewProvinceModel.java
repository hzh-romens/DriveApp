package com.example.ausu.erpapp.city;

import java.util.List;

/**
 * Created by Lanxumit on 2016/9/7.
 */
public class NewProvinceModel {
    private String id;
    private String name;
    private String memo;
    private List<NewCityModel> cityList;


    public NewProvinceModel() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<NewCityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<NewCityModel> cityList) {
        this.cityList = cityList;
    }
}
