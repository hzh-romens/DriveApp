package com.example.ausu.erpapp.city;

import java.util.List;

public class NewCityModel {
    private String id;
    private String name;
    private String zipcode;
    private String parentid;
    private List<NewDistrictModel> districtList;

    public NewCityModel() {
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public List<NewDistrictModel> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<NewDistrictModel> districtList) {
        this.districtList = districtList;
    }
}
