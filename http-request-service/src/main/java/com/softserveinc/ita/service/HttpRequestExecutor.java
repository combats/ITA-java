package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.exception.HttpRequestException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public interface HttpRequestExecutor {


    List<String> getAllObjectsID(Class objectClass) throws HttpRequestException;

    <T> T getObjectByID(String id, Class<T> objectClass) throws HttpRequestException;

    RestTemplate getRestTemplate();

    String getBaseUrl();

    List<Appointment> getListOfObjectsByID(String applicantId, Class<Appointment> appointmentClass);
}
