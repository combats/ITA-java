package com.softserveinc.ita.interviewfactory.service.mainServices;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.QuestionInformation;
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

    List<Interview> getInterviewByApplicantID(String ID) throws ApppoinmentNotFoundException, InterviewNotFoundException, InvalidUserIDException;
    Interview putInterview(String appointmentID, String type) throws ApppoinmentNotFoundException, WrongCriteriaException, InvalidUserIDException;

    Interview getInterviewByAppointmentID(String appointmentId) throws InterviewNotFoundException, ApppoinmentNotFoundException, WrongCriteriaException, InvalidUserIDException;

    Interview getInterviewByInterviewID(String interviewId) throws InterviewNotFoundException, InvalidUserIDException, ApppoinmentNotFoundException;

    void removeInterviewById(String interviewId) throws InterviewNotFoundException, InvalidUserIDException, ApppoinmentNotFoundException;
    Interview setInterview(Interview interview) throws InvalidUserIDException, ApppoinmentNotFoundException, InterviewNotFoundException;
}
