package com.softserveinc.ita.interviewfactory.dao.interviewDAO;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.service.exception.HttpRequestException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
public interface InterviewDAO {

    Interview getInterviewByAppointmentId(String appointmentId) throws HttpRequestException;

    String putInterview(Interview interview) throws HttpRequestException;

    void removeInterviewByAppointmentId(String appointmentId) throws HttpRequestException;

    void updateInterview(Interview interview) throws HttpRequestException;
    List<String> getAllInterviewsId() throws HttpRequestException;
    List<Interview> getAllInterviews() throws HttpRequestException;

}
