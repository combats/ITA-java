package com.softserveinc.ita.interviewfactory.service.mainServices;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
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


    //-------------VadimNaumenko mock for tests------------------------------------
    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    private JsonUtil interviewUtilJson;

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    private long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    Appointment appointment1;
    Appointment appointment2;
    Appointment appointment3;

    private User user1 = new User("1", "IT Project Manager");
    private User user2 = new User("2", "Software Developer");
    private User user3 = new User("3", "HR Manager");

    private Applicant applicant1 = new Applicant("1", "Gena");
    private Applicant applicant2 = new Applicant("2", "Gesha");

    List<String> usersIdList = new ArrayList<>(); {

        Question question1 = new Question("Have you ever were connected with quality assurance engineering?", 2);
        Question question2 = new Question("Have you ever were connected with database developing?", 3);
        Question question3 = new Question("Tell me something about JUnit testing.", 2);
        Question question4 = new Question("Your last book you read?", 3);
        Question question5 = new Question("Where did you study?", 2);
        Question question6 = new Question("Are you married?", 3);
        List<Question> questionsList1 = new ArrayList<>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setId("1");
        user2.setId("2");
        user3.setId("3");
        user1.setQuestions(questionsList1);
        List<Question> questionsList2 = new ArrayList<>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestions(questionsList1);
        List<Question> questionsList3 = new ArrayList<>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestions(questionsList1);
        Collections.addAll(usersIdList, user1.getId(), user2.getId(), user3.getId());
    }

    List<String> appointmentIdList = new ArrayList<>();{
        applicant1.setId("1");
        appointment1 = new Appointment(usersIdList, applicant1.getId(), startTime);
        appointment1.setAppointmentId("1");
        applicant2.setId("2");
        appointment2 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
        appointment3 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment3.setAppointmentId("3");
        appointmentIdList.add(appointment1.getAppointmentId());
        appointmentIdList.add(appointment2.getAppointmentId());
        appointmentIdList.add(appointment3.getAppointmentId());
    }

    //-------------VadimNaumenko mock from tests

    @Override
    public Interview putInterview(String appointmentID, String type) throws HttpRequestException, WrongCriteriaException {

        List<Interview> interviewsList = new ArrayList<>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }

        if (type.equals("InterviewWithoutQuestions")) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create(appointmentID);
            interview.setType(InterviewType.InterviewWithoutQuestions);
            interviewsList.add(interview);
            return interviewsList.get(interviewsList.size() - 1);
        }
        if (type.equals("InterviewWithStandardQuestions")) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.InterviewWithStandardQuestions).create(appointmentID);
            interview.setType(InterviewType.InterviewWithStandardQuestions);
            interviewsList.add(interview);
            return interviewsList.get(interviewsList.size() - 1);
        }
        if (type.equals("InterviewWithUserAndStandardQuestions")) {
            Interview interview =
                    interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointmentID);
            interview.setType(InterviewType.InterviewWithUserAndStandardQuestions);
            interviewsList.add(interview);
            return interviewsList.get(interviewsList.size() - 1);
        }
        throw new WrongCriteriaException("Type is wrong");
    }

    @Override
    public List<Interview> getInterviewByApplicantID(String ID) throws HttpRequestException, InterviewNotFoundException {

        List<Interview> interviewsList = new ArrayList<>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }

        List<Interview> interviewsListWithMyApplicant = new ArrayList<>();
        for (Interview interview : interviewsList)
        {
            if (httpRequestExecutor.getObjectByID(interview.getAppointmentId(), Appointment.class).getApplicantId().equals(ID)) {
                interviewsListWithMyApplicant.add(interview);
            }
        }
        if (interviewsListWithMyApplicant.isEmpty())
            throw new InterviewNotFoundException("Wrong ID, interview not found");

        return interviewsListWithMyApplicant;
    }

    @Override
    public Interview getInterviewByAppointmentID(String interviewId) throws HttpRequestException, WrongCriteriaException {

        List<Interview> interviewsList = new ArrayList<>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }

        Interview interviewForReturn = null;
        for (Interview interview : interviewsList){
            if (httpRequestExecutor.getObjectByID(interview.getAppointmentId(), Appointment.class).getAppointmentId().equals(interviewId)){
                interviewForReturn = interview;
            }
        }

        if (interviewForReturn == null)  {
            interviewForReturn = putInterview(interviewId, "InterviewWithUserAndStandardQuestions");
        }

        return interviewForReturn;
    }

    @Override
    public void removeInterviewByAppointmentId(String interviewId) throws HttpRequestException, InterviewNotFoundException {

        List<Interview> interviewsList = new ArrayList<>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }


         for (int i = 0; i < interviewsList.size(); i++){

         if (interviewsList.get(i).getAppointmentId().equals(interviewId)){
             interviewsList.remove(i);
             return;
             }
         }
         throw new InterviewNotFoundException("Wrong ID, interview not found");

    }

    @Override
    public Interview updateInterview(Interview interview) throws HttpRequestException, InterviewNotFoundException {
        List<Interview> interviewsList = new ArrayList<>(); {

            Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment1.getAppointmentId());
            Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointment2.getAppointmentId());
            interviewsList.add(interview1);
            interviewsList.add(interview2);
        }
        for (int i = 0; i < interviewsList.size(); i++){
            if (interviewsList.get(i).getAppointmentId().equals(interview.getAppointmentId()))   {
                interviewsList.add(i, interview);
                return interviewsList.get(i);
            }
        }
        throw new InterviewNotFoundException("Wrong interviewID");

    }
}
