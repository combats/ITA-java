package com.softserveinc.ita.servise.impl;

import com.softserveinc.ita.servise.HttpRestService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.LinkedList;

 @ContextConfiguration("file:spring-config.xml")
public class HttpRestServiseImpl<T>  implements HttpRestService {

 private   String  baseUrl;
    @Autowired
    private JsonUtil utilJson;

    public HttpRestServiseImpl() {
    }

    RestTemplate restTemplate = new RestTemplate();

    public HttpRestServiseImpl(String baseUrl) {
        this.baseUrl = baseUrl;

    }

    /**
     * REST client that makes a call to a URL and handle success and error by
     * returning them in error String.
     */

    @Override
    public List<Integer> getAllObjectsID(Class objectClass) {
        String response="";


        String subclass = objectClass.getSimpleName().toLowerCase();

        List<Integer> list = new LinkedList<Integer>();



        response = restTemplate.getForObject(baseUrl+"/"+subclass+"/",String.class);



        list =utilJson.fromJson(response, list.getClass());

        return list;
    }


    @Override
    public T getObjectByID(int id, Class objectClass) {

        T obj =null;
         String response = "{}";
         String subclass = objectClass.getSimpleName().toLowerCase();



          response =  restTemplate.getForObject(baseUrl+"/"+subclass+"/id="+id,String.class);


          obj = (T) utilJson.fromJson(response, objectClass);


        return obj;
    }

    @Override
    public RestTemplate getRestTemplate(){ return restTemplate;}

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }


}