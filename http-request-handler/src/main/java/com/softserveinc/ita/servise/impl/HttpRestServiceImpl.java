package com.softserveinc.ita.servise.impl;

import com.softserveinc.ita.servise.HttpRequestHandler;
import com.softserveinc.ita.servise.exeption.HttpHandlerRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.LinkedList;

public class HttpRestServiceImpl  implements HttpRequestHandler {




    private final int STATUS_CODE_OK = 200;
    private final RestTemplate restTemplate = new RestTemplate();


    private String baseUrl;




    public HttpRestServiceImpl(String baseUrl) {
        this.baseUrl = baseUrl;

    }

        @Override
    public List<String> getAllObjectsID(Class objectClass) throws HttpHandlerRequestException {

        // add to URL path
        String subclass = objectClass.getSimpleName().toLowerCase();

        String urlReq = baseUrl + "/" + subclass + "s_id";

        return  getObject(urlReq, LinkedList.class);
    }

        @Override
    public <T> T getObjectByID(String id, Class<T> objectClass) throws HttpHandlerRequestException {


        // name subdirectory
        String subclass = objectClass.getSimpleName().toLowerCase();

        // add to URL path
        String urlReq = baseUrl + "/" + subclass + "s/" + id;

        return getObject(urlReq, objectClass);

    }


    private <T> T getObject(String urlPath, Class<T> clazz) throws HttpHandlerRequestException {

        ResponseEntity<T> entity;

        try {
            entity = restTemplate.getForEntity(urlPath, clazz );
        }catch (HttpServerErrorException httpServError) {
            throw new HttpHandlerRequestException(printException(httpServError), httpServError);
        }

        if (entity.getStatusCode().value() != STATUS_CODE_OK) {
            throw new HttpHandlerRequestException("\nReason : " + entity.getStatusCode().getReasonPhrase() +
                    "\nStatus code : " + +entity.getStatusCode().value() +
                    "\nMessage : " + entity.getStatusCode().getReasonPhrase());
        }



        return entity.getBody();
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