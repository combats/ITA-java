package com.softserveinc.ita.interviewfactory.service.interviewServices;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.dao.InterviewDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
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
    AppointmentDAO appointmentDAO;



    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Override
    public List<Interview> getInterviewByApplicantID(String applicantId) throws HttpRequestException, InterviewNotFoundException {
        List<Interview> listForReturn = new ArrayList<>();
        List<Appointment> listWithApplicant = appointmentDAO.getAppointmentByApplicantId(applicantId);
        for (Appointment appointment : listWithApplicant){
            listForReturn.add(interviewDAO.getInterviewByAppointmentId(appointment.getAppointmentId()));
        }
        return listForReturn;
    }

    @Override
    public String putInterview(String appointmentID, String type) throws HttpRequestException, WrongCriteriaException {

        if (type.equals("InterviewWithoutQuestions")) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create(appointmentID);
            return interviewDAO.putInterview(interview);
        }
        if (type.equals("InterviewWithStandardQuestions")) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.InterviewWithStandardQuestions).create(appointmentID);
            return interviewDAO.putInterview(interview);
        }
        if (type.equals("InterviewWithUserAndStandardQuestions")) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointmentID);
            return interviewDAO.putInterview(interview);
        }
        throw new WrongCriteriaException("Type is wrong");
    }

    @Override
    public Interview getInterviewByAppointmentID(String appointmentId) throws WrongCriteriaException, HttpRequestException {
        Interview interview = interviewDAO.getInterviewByAppointmentId(appointmentId);
        if (interview == null){
            String interviewId = putInterview(appointmentId, "InterviewWithoutQuestions");
            interview = interviewDAO.getInterviewByAppointmentId(interviewId);
        }
        return interview;
    }

    @Override
    public void removeInterviewByAppointmentId(String interviewId) throws HttpRequestException, InterviewNotFoundException {
        interviewDAO.removeInterviewByAppointmentId(interviewId);
    }

    @Override
    public void updateInterview(Interview interview) throws HttpRequestException, InterviewNotFoundException {
        interviewDAO.updateInterview(interview);
    }
}
