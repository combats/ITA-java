package com.softserveinc.ita.servise;

import com.softserveinc.ita.servise.exeption.HttpHandlerRequestException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Rossoha on 09.06.2014.
 */
public interface HttpRequestHandler {


    List<String> getAllObjectsID(Class objectClass) throws HttpHandlerRequestException;

    <T> T getObjectByID(String id, Class<T> objectClass) throws HttpHandlerRequestException;

    RestTemplate getRestTemplate();

    String getBaseUrl();
}
