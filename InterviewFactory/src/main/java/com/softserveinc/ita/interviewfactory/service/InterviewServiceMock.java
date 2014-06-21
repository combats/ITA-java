package com.softserveinc.ita.interviewfactory.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.interviewfactory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.dao.InterviewDAO;
import com.softserveinc.ita.interviewfactory.dao.InterviewDAOImpl;
import com.softserveinc.ita.service.*;

import exceptions.InterviewNotFoundException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public class InterviewServiceMock implements InterviewService {


    private InterviewDAO interviewDAO = new InterviewDAOImpl();

    @Override
    public Interview putInterview(String appointmentID, InterviewType type) throws Exception {

        if (type.equals(InterviewType.InterviewWithoutQuestions)) {
            Interview InterviewWithoutQuestions =
                    InterviewFactory.getInterview(appointmentID, InterviewType.InterviewWithoutQuestions);
            return interviewDAO.putInterview(InterviewWithoutQuestions);
        }
        if (type.equals(InterviewType.InterviewWithStandardQuestions)) {
            Interview InterviewWithStandardQuestions =
                    InterviewFactory.getInterview(appointmentID, InterviewType.InterviewWithStandardQuestions);
            return interviewDAO.putInterview(InterviewWithStandardQuestions);
        }
        if (type.equals(InterviewType.InterviewWithUserAndStandardQuestions)) {
            Interview InterviewWithUserAndStandardQuestions =
                    InterviewFactory.getInterview(appointmentID, InterviewType.InterviewWithUserAndStandardQuestions);
            return interviewDAO.putInterview(InterviewWithUserAndStandardQuestions);
        }
        throw new WrongCriteriaException("Type is wrong");
    }

    @Override
    public List<Interview> getInterviewByApplicantID(String ID) throws Exception {
        return interviewDAO.getInterviewByApplicantID(ID);
    }
}