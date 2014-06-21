package com.softserveinc.ita.interviewfactory.service;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
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

    List<Interview> getInterviewByApplicantID(String ID) throws Exception;
    Interview putInterview(String appointmentID, InterviewType type)  throws Exception;
}
