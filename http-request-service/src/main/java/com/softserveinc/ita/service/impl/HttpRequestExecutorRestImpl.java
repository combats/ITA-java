package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class HttpRequestExecutorRestImpl implements HttpRequestExecutor {

    private final int STATUS_CODE_OK = 200;
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public HttpRequestExecutorRestImpl(String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate  = new RestTemplate();
    }

        @Override
    public List<String> getAllObjectsID(Class objectClass) throws HttpRequestException {

        // add to URL path
        String subclass = objectClass.getSimpleName().toLowerCase();

        String urlReq = baseUrl + "/" + subclass + "s_id";

        return  getObject(urlReq, List.class);
    }

        @Override
    public <T> T getObjectByID(String id, Class<T> objectClass) throws HttpRequestException {

        // name subdirectory
        String subclass = objectClass.getSimpleName().toLowerCase();

        // add to URL path
        String urlReq = baseUrl + "/" + subclass + "s/" + id;

        return getObject(urlReq, objectClass);

    }


    private <T> T getObject(String urlPath, Class<T> clazz) throws HttpRequestException {

        ResponseEntity<T> entity;

        try {
            entity = restTemplate.getForEntity(urlPath, clazz );
        }catch (HttpServerErrorException httpServError) {
            throw new HttpRequestException(printException(httpServError), httpServError);
        }

        if (entity.getStatusCode().value() != STATUS_CODE_OK) {
            throw new HttpRequestException("\nReason : " + entity.getStatusCode().getReasonPhrase() +
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