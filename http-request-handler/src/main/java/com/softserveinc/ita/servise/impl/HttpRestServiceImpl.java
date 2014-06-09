package com.softserveinc.ita.servise.impl;

import com.softserveinc.ita.servise.exeption.HttpHandlerRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.LinkedList;

@ContextConfiguration("file:spring-config.xml")
public class HttpRestServiceImpl<T>  {


    public static void main(String[] args) {
        try {
            new HttpRestServiceImpl<>("http://www.1111111111.111.111").getAllObjectsID(new Object().getClass());
        } catch (HttpHandlerRequestException e) {
            e.printStackTrace();
        }
    }

    private final int STATUS_CODE_OK = 200;
    private final RestTemplate restTemplate = new RestTemplate();

    private String baseUrl;


    @Autowired
    private JsonUtil utilJson;


    public HttpRestServiceImpl(String baseUrl) {
        this.baseUrl = baseUrl;

    }


    public List<String> getAllObjectsID(Class objectClass) throws HttpHandlerRequestException {


        // add to URL path
        String subclass = objectClass.getSimpleName().toLowerCase();

        String urlReq = baseUrl + "/" + subclass + "s_id";

        // for generated from Json Objects
        LinkedList<String> list = new LinkedList<>();



        return (List<String>) getObject(urlReq, list.getClass());
    }


    public T getObjectByID(String id, Class<T> objectClass) throws HttpHandlerRequestException {


        // name subdirectory
        String subclass = objectClass.getSimpleName().toLowerCase();

        // add to URL path
        String urlReq = baseUrl + "/" + subclass + "s/" + id;

        return getObject(urlReq, objectClass);

    }


    private T getObject(String urlPath, Class clazz) throws HttpHandlerRequestException {

        ResponseEntity<String> entity;

        try {
            entity = restTemplate.getForEntity(urlPath, String.class);
        }catch (HttpServerErrorException httpServError) {
            throw new HttpHandlerRequestException(printException(httpServError));
        }

        if (entity.getStatusCode().value() != STATUS_CODE_OK) {
            throw new HttpHandlerRequestException("\nReason : " + entity.getStatusCode().getReasonPhrase() +
                    "\nStatus code : " + +entity.getStatusCode().value() +
                    "\nMessage : " + entity.getStatusCode().getReasonPhrase());
        }



        return (T) utilJson.fromJson(entity.getBody(), clazz);
    }




    public RestTemplate getRestTemplate(){ return restTemplate;}


    public String getBaseUrl() {
        return baseUrl;
    }


    private String printException(HttpStatusCodeException ex){
      return   "\nStatus text : " + ex.getStatusText()+
              "\nStatus code : "  +ex.getStatusCode() +
              "\nResponse body : " +  ex.getResponseBodyAsString();
    }


}