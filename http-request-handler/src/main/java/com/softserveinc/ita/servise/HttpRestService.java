package com.softserveinc.ita.servise;

import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Rossoha on 03.06.2014.
 */
public interface HttpRestService<T> {



    /**
     * The method makes a request,
     * and returns ID all object as @List<Integer>
     * @param objectClass the type of the return value
     * @return List<Integer>
     */
    List<Integer> getAllObjectsID(Class<T> objectClass);

    /**
     * The method makes a request,
     * and returns an object of class @type Class<T> objectClass;
     * @param id current Object
     * @param objectClass the type of the return object
     * @return the converted object type Class objectClass;
     */
    <T>T getObjectByID(int id, Class<T> objectClass);

    RestTemplate getRestTemplate();

    String getBaseUrl();



}
