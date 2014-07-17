package com.softserveinc.ita.utils.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softserveinc.ita.utils.JsonUtil;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;

public class InterviewJsonUtilGsonImpl implements JsonUtil {
    private Gson gson = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setDateFormat(DateFormat.DEFAULT)
            .setPrettyPrinting()
        //    .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Override
    public <T> T fromJson(String s, Class<T> classOfT) {
        return gson.fromJson(s, classOfT);
    }

    @Override
    public String toJson(Object o) {
        return gson.toJson(o);
    }

//    ObjectMapper mapper = new ObjectMapper();
//    @Override
//    public <T> T fromJson(String s, Class<T> classOfT) {
//        try {
//            return mapper.readValue(s, classOfT);
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    @Override
//    public String toJson(Object o) {
//        try {
//            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
//        } catch (IOException e) {
//            return null;
//        }
//    }

}

