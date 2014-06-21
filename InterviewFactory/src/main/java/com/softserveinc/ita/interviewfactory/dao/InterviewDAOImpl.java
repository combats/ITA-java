package com.softserveinc.ita.interviewfactory.dao;


import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.exceptions.InvalidUserIDException;
import com.softserveinc.ita.interviewfactory.InterviewFactory;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.service.mocks.AppointmentServiceMock;
import com.softserveinc.ita.utils.JsonUtil;
import exceptions.InterviewNotFoundException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public class InterviewDAOImpl implements InterviewDAO {


    private long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    //-------------VadimNaumenko mock for tests------------------------------------

//
//    @Autowired
//    private User user1;
//
//    @Autowired
//    private User user2;
//
//    @Autowired
//    private User user3;
//
//    @Autowired
//    private Applicant applicant1;
//
//    @Autowired
//    private Applicant applicant2;
    //  @Autowired
    //  private User user2;

    //  @Autowired
    //  private User user3;

    Appointment appointment1;
    Appointment appointment2;

    AppointmentService appointmentService = new AppointmentServiceMock();

    Interview interview1;
    Interview interview2;

    private User user1 = new User("1", "IT Project Manager");
    private User user2 = new User("2", "Software Developer");
    private User user3 = new User("3", "HR Manager");

    private Applicant applicant1 = new Applicant("1", "Gena");
    private Applicant applicant2 = new Applicant("2", "Gesha");

    List<String> usersIdList = new ArrayList<String>(); {

        Question question1 = new Question("Have you ever were connected with quality assurance engineering?", 2);
        Question question2 = new Question("Have you ever were connected with database developing?", 3);
        Question question3 = new Question("Tell me something about JUnit testing.", 2);
        Question question4 = new Question("Your last book you read?", 3);
        Question question5 = new Question("Where did you study?", 2);
        Question question6 = new Question("Are you married?", 3);
        List<Question> questionsList1 = new ArrayList<Question>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setQuestion(questionsList1);
        List<Question> questionsList2 = new ArrayList<Question>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestion(questionsList1);
        List<Question> questionsList3 = new ArrayList<Question>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestion(questionsList1);
        Collections.addAll(usersIdList, user1.getUserID(), user2.getUserID(), user3.getUserID());
    }

    List<String> appointmentIdList = new ArrayList<String>();{
        appointment1 = new Appointment(usersIdList, applicant1.getId(), startTime);
        appointment1.setAppointmentId("1");
        appointment2 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
        appointmentIdList.add(appointment1.getAppointmentId());
        appointmentIdList.add(appointment2.getAppointmentId());
    }

    List<Interview> interviewsList = new ArrayList<Interview>(); {

        try{
        interview1 = InterviewFactory.getInterview(appointment1.getAppointmentId(), InterviewType.InterviewWithUserAndStandardQuestions);
        interview2 = InterviewFactory.getInterview(appointment2.getAppointmentId(), InterviewType.InterviewWithUserAndStandardQuestions);
        }
        catch (Exception e){}

        interviewsList.add(interview1);
        interviewsList.add(interview2);
    }

    //-------------VadimNaumenko mock from tests



    @Override
    public Interview putInterview(Interview interview) {
        interviewsList.add(interview);
        return interviewsList.get(interviewsList.size() - 1);
    }

    @Override
    public List<Interview> getInterviewByApplicantID(String ID) throws Exception{
        List<Interview> interviewsListWithMyApplicant = new ArrayList<Interview>();
        for (Interview interview : interviewsList){
            if (appointmentService.getAppointmentByAppointmentId(interview.getAppointmentId()).getApplicantId().equals(ID))
                interviewsListWithMyApplicant.add(interview);
        }
        if (interviewsListWithMyApplicant.isEmpty())
            throw new InterviewNotFoundException("Wrong ID, interview not found");

        return interviewsListWithMyApplicant;
    }

}
