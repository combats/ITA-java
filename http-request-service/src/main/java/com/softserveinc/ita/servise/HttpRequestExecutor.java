package com.softserveinc.ita.servise;

import com.softserveinc.ita.servise.exeption.HttpRequestException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public interface HttpRequestExecutor {


    List<String> getAllObjectsID(Class objectClass) throws HttpRequestException;

    <T> T getObjectByID(String id, Class<T> objectClass) throws HttpRequestException;

    RestTemplate getRestTemplate();

    String getBaseUrl();
}
