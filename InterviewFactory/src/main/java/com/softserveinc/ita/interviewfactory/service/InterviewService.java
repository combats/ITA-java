package com.softserveinc.ita.interviewfactory.service;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;
import com.softserveinc.ita.exceptions.InvalidUserIDException;
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

    List<Interview> getInterviewByApplicantID(String ID) throws ApppoinmentNotFoundException, InterviewNotFoundException;
    Interview putInterview(String appointmentID, String type) throws ApppoinmentNotFoundException, WrongCriteriaException, InvalidUserIDException;

    List<Interview> getInterviewByAppointmentID(String appointmentId) throws InterviewNotFoundException, ApppoinmentNotFoundException;

    Interview getInterviewByInterviewID(String interviewId) throws InterviewNotFoundException;

    void removeInterviewById(String interviewId) throws InterviewNotFoundException;
}
