package com.softserveinc.ita.interviewfactory.service.interviewServices;

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

    List<Interview> getInterviewByApplicantID(String ID) throws HttpRequestException, InterviewNotFoundException;
    String putInterview(String appointmentID, String type) throws HttpRequestException, WrongCriteriaException;

    Interview getInterviewByAppointmentID(String interviewId) throws HttpRequestException, WrongCriteriaException;

    void removeInterviewByAppointmentId(String interviewId) throws HttpRequestException, InterviewNotFoundException;
    void updateInterview(Interview interview) throws HttpRequestException, InterviewNotFoundException;
}
