package com.softserveinc.ita.interviewfactory.service.mainServices;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
import exceptions.WrongCriteriaException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public interface InterviewService {

    List<Interview> getInterviewByApplicantID(String ID) throws InterviewNotFoundException, HttpRequestException;
    Interview putInterview(String appointmentID, String type) throws WrongCriteriaException, HttpRequestException;

    Interview getInterviewByAppointmentID(String appointmentId) throws InterviewNotFoundException, WrongCriteriaException, HttpRequestException;

    Interview getInterviewByInterviewID(String interviewId) throws InterviewNotFoundException, HttpRequestException;

    void removeInterviewById(String interviewId) throws InterviewNotFoundException, HttpRequestException;
    Interview setInterview(Interview interview) throws InterviewNotFoundException, HttpRequestException;
}
