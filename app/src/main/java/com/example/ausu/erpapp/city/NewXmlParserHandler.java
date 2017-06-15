package com.example.ausu.erpapp.city;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/9/7.
 */
public class NewXmlParserHandler extends DefaultHandler {

    private List<NewProvinceModel> provinceList = new ArrayList<NewProvinceModel>();
    private List<NewCityModel> cityList = new ArrayList<NewCityModel>();
    private List<NewDistrictModel> districtList = new ArrayList<NewDistrictModel>();
    private String type;


    public NewXmlParserHandler() {

    }

    public List<NewProvinceModel> getDataList() {
        return provinceList;
    }

    public List<NewCityModel> getCityList() {
        return cityList;
    }

    public List<NewDistrictModel> getDistrictList() {
        return districtList;
    }


    @Override
    public void startDocument() throws SAXException {

    }

    NewProvinceModel provinceModel = new NewProvinceModel();
    NewCityModel cityModel = new NewCityModel();
    NewDistrictModel districtModel = new NewDistrictModel();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (localName.equals("dict")) {
            String value = attributes.getValue(0);
            if ("province".equals(value)) {
                type = "province";
                provinceModel = new NewProvinceModel();
            } else if ("city".equals(value)) {
                type = "city";
                cityModel = new NewCityModel();
            } else if ("district".equals(value)) {
                type = "district";
                districtModel = new NewDistrictModel();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(uri, localName, qName);
        if ("province".equals(type)) {
            if ("id".equals(localName)) {
                provinceModel.setId(content);
            } else if ("name".equals(localName)) {
                provinceModel.setName(content);
            } else if ("memo".equals(localName)) {
                provinceModel.setMemo(content);
            }else if ("dict".equals(localName)) {
                provinceList.add(provinceModel);
            }

        } else if ("city".equals(type)) {
            if ("id".equals(localName)) {
                cityModel.setId(content);
            } else if ("name".equals(localName)) {
                cityModel.setName(content);
            } else if ("zipcode".equals(localName)) {
                cityModel.setZipcode(content);
            } else if ("parentid".equals(localName)) {
                cityModel.setParentid(content);
            } else if ("dict".equals(localName)) {
                cityList.add(cityModel);
            }

        } else if ("district".equals(type)) {
            if ("id".equals(localName)) {
                districtModel.setId(content);
            } else if ("name".equals(localName)) {
                districtModel.setName(content);
            } else if ("zipcode".equals(localName)) {
                districtModel.setZipcode(content);
            } else if ("parentid".equals(localName)) {
                districtModel.setParentid(content);
            }else if ("dict".equals(localName)) {
                districtList.add(districtModel);
            }
        }
    }

    //声明一个字符串变量
    private String content;

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        content = new String(ch, start, length);
    }

}
