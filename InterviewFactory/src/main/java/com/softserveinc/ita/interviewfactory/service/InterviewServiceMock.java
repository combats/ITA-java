package com.softserveinc.ita.interviewfactory.service;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;
import com.softserveinc.ita.exceptions.InvalidUserIDException;
import com.softserveinc.ita.interviewfactory.InterviewFactory;
import com.softserveinc.ita.service.*;

import com.softserveinc.ita.service.mocks.AppointmentServiceMock;
import exceptions.InterviewNotFoundException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public class InterviewServiceMock implements InterviewService {


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
    public Interview putInterview(String appointmentID, InterviewType type) throws WrongCriteriaException, InvalidUserIDException, ApppoinmentNotFoundException {

        if (type.equals(InterviewType.InterviewWithoutQuestions)) {
            Interview InterviewWithoutQuestions =
                    InterviewFactory.getInterview(appointmentID, InterviewType.InterviewWithoutQuestions);
            interviewsList.add(InterviewWithoutQuestions);
            return interviewsList.get(interviewsList.size() - 1);
        }
        if (type.equals(InterviewType.InterviewWithStandardQuestions)) {
            Interview InterviewWithStandardQuestions =
                    InterviewFactory.getInterview(appointmentID, InterviewType.InterviewWithStandardQuestions);
            interviewsList.add(InterviewWithStandardQuestions);
            return interviewsList.get(interviewsList.size() - 1);
        }
        if (type.equals(InterviewType.InterviewWithUserAndStandardQuestions)) {
            Interview InterviewWithUserAndStandardQuestions =
                    InterviewFactory.getInterview(appointmentID, InterviewType.InterviewWithUserAndStandardQuestions);
            interviewsList.add(InterviewWithUserAndStandardQuestions);
            return interviewsList.get(interviewsList.size() - 1);
        }
        throw new WrongCriteriaException("Type is wrong");
    }

    @Override
    public List<Interview> getInterviewByApplicantID(String ID) throws ApppoinmentNotFoundException, InterviewNotFoundException {
        List<Interview> interviewsListWithMyApplicant = new ArrayList<Interview>();
        for (Interview interview : interviewsList){
            if (appointmentService.getAppointmentByAppointmentId(interview.getAppointmentId()).getApplicantId().equals(ID))
                interviewsListWithMyApplicant.add(interview);
        }
        if (interviewsListWithMyApplicant.isEmpty())
            throw new InterviewNotFoundException("Wrong ID, interview not found");

        return interviewsListWithMyApplicant;
    }

    @Override
    public List<Interview> getInterviewByAppointmentID(String appointmentId) throws InterviewNotFoundException, ApppoinmentNotFoundException {
        List<Interview> interviewsListWithMyAppointments = new ArrayList<Interview>();
        for (Interview interview : interviewsList){
            if (appointmentService.getAppointmentByAppointmentId(interview.getAppointmentId()).equals(appointmentId))
                interviewsListWithMyAppointments.add(interview);
        }
        if (interviewsListWithMyAppointments.isEmpty())
            throw new InterviewNotFoundException("Wrong ID, interview not found");

        return interviewsListWithMyAppointments;
    }

    @Override
    public void removeInterviewById(String interviewId) throws InterviewNotFoundException {
        while(true){
         for (int i = 0; i < interviewsList.size(); i++){
         if (interviewsList.get(i).getInterviewId().equals(interviewId)){
             interviewsList.remove(i);
             break;

             }
         }
            throw new InterviewNotFoundException("Wrong ID, interview not found");
        }
    }

    @Override
    public Interview getInterviewByInterviewID(String interviewId) throws InterviewNotFoundException {
        for (Interview interview : interviewsList){
            if (interview.getInterviewId().equals(interviewId))
                return interview;
        }

        throw new InterviewNotFoundException("Wrong ID, interview not found");
    }
}
