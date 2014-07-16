package com.softserveinc.ita.interviewfactory.service.interviewServices;


import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.dao.InterviewDAO;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewDAO interviewDAO;

    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Override
    public List<Interview> getInterviewByApplicantID(String applicantId) throws HttpRequestException {
        List<Interview> listForReturn = new ArrayList<>();
        List<Appointment> listWithApplicant = httpRequestExecutor.getListOfObjectsByID(applicantId, Appointment.class);

        for (Appointment appointment : listWithApplicant){
            listForReturn.add(interviewDAO.getInterviewByAppointmentId(appointment.getAppointmentId()));
        }
        return listForReturn;
    }

    @Override
    public String putInterview(String appointmentID, InterviewType type) throws HttpRequestException, WrongCriteriaException {

        if (type == null) throw new WrongCriteriaException("Type is wrong");
        else
        if (type.equals(InterviewType.INTERVIEW_WITHOUT_QUESTIONS)) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create(appointmentID);
            return interviewDAO.putInterview(interview);
        }
        else
        if (type.equals(InterviewType.INTERVIEW_WITH_STANDARD_QUESTIONS)) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITH_STANDARD_QUESTIONS).create(appointmentID);
            return interviewDAO.putInterview(interview);
        }
        else
        if (type.equals(InterviewType.INTERVIEW_WITH_USER_AND_STANDARD_QUESTIONS)) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITH_USER_AND_STANDARD_QUESTIONS).create(appointmentID);
            return interviewDAO.putInterview(interview);
        }
        else
        throw new WrongCriteriaException("Type is wrong");
    }

    @Override
    public Interview getInterviewByAppointmentID(String appointmentId) throws WrongCriteriaException, HttpRequestException {
        Interview interview = interviewDAO.getInterviewByAppointmentId(appointmentId);
        if (interview == null){
            String interviewId = putInterview(appointmentId, InterviewType.INTERVIEW_WITHOUT_QUESTIONS);
            interview = interviewDAO.getInterviewByAppointmentId(interviewId);
        }
        return interview;
    }

    @Override
    public void removeInterviewByAppointmentId(String interviewId) throws HttpRequestException {
        interviewDAO.removeInterviewByAppointmentId(interviewId);
    }

    @Override
    public void updateInterview(Interview interview) throws HttpRequestException {
        interviewDAO.updateInterview(interview);
    }

    @Override
    public List<String> getAllInterviewsId() throws HttpRequestException {
        return interviewDAO.getAllInterviewsId();
    }

    @Override
    public List<Interview> getAllInterviews() throws HttpRequestException {
        return interviewDAO.getAllInterviews();
    }
}
