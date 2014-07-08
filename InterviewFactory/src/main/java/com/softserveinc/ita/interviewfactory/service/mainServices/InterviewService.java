package com.softserveinc.ita.interviewfactory.service.mainServices;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionsBlockNotFound;
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

    List<Interview> getInterviewByApplicantID(String ID) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException;
    Interview putInterview(String appointmentID, String type) throws WrongCriteriaException, HttpRequestException, QuestionsBlockNotFound, InterviewNotFoundException;

    Interview getInterviewByAppointmentID(String interviewId) throws InterviewNotFoundException, WrongCriteriaException, HttpRequestException, QuestionsBlockNotFound;

    void removeInterviewByAppointmentId(String interviewId) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException;
    Interview setInterview(Interview interview) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException;
}
