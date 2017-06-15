package com.example.ausu.erpapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Lanxumit on 2016/5/13.
 */
public class JacksonMapper {
    private static final ObjectMapper jacksonMapper=new ObjectMapper();
    private JacksonMapper() {
    }

    public static ObjectMapper getInstance() {
        return jacksonMapper;
    }
}
