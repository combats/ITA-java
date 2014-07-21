package com.softserveinc.ita.interviewfactory.service.questionInformationServices;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.mainServices.InterviewService;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionNotFoundException;
import exceptions.QuestionsBlockNotFound;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 07.07.14
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsInformationServicesMock implements QuestionsInformationServices {

    //-------------VadimNaumenko mock for tests------------------------------------
    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Autowired
    private JsonUtil interviewUtilJson;

    @Autowired
    InterviewService interviewService;

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

    List<String> usersIdList = new ArrayList<String>(); {

        Question question1 = new Question("Have you ever were connected with quality assurance engineering?", 2);
        Question question2 = new Question("Have you ever were connected with database developing?", 3);
        Question question3 = new Question("Tell me something about JUnit testing.", 2);
        Question question4 = new Question("Your last book you read?", 3);
        Question question5 = new Question("Where did you study?", 2);
        Question question6 = new Question("Are you married?", 3);
        List<Question> questionsList1 = new ArrayList<Question>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setId("1");
        user2.setId("2");
        user3.setId("3");
        user1.setQuestions(questionsList1);
        List<Question> questionsList2 = new ArrayList<Question>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestions(questionsList1);
        List<Question> questionsList3 = new ArrayList<Question>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestions(questionsList1);
        Collections.addAll(usersIdList, user1.getId(), user2.getId(), user3.getId());
    }

    List<String> appointmentIdList = new ArrayList<String>();{
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
    List<QuestionInformation> questionInformationList;


    @Override
    public QuestionInformation editQuestionInformation(QuestionInformation questionInformation) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException, QuestionNotFoundException {
        //-----------mock--------------
        questionInformationList =
                interviewService.getInterviewByAppointmentID("1").getQuestionsBlocks().get(Integer.valueOf(questionInformation.getQuestionsBlockId())).getQuestions();
        //--------------mock--------------------
        for (int i = 0; i < questionInformationList.size(); i++){
            if (questionInformationList.get(i).getQuestionInformationID().equals(questionInformation.getQuestionInformationID())) {
                questionInformationList.add(i, questionInformation);
                return questionInformation;
            }
        }
        throw new QuestionNotFoundException("Wrong id");

    }

    @Override
    public QuestionInformation getQuestionInformationById(String questionsInformationId) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException, QuestionNotFoundException {
        //-----------mock--------------
        questionInformationList =
                interviewService.getInterviewByAppointmentID("1").getQuestionsBlocks().get(1).getQuestions();
        //--------------mock--------------------

        for (int i = 0; i < questionInformationList.size(); i++){
                if (questionInformationList.get(i).getQuestionInformationID().equals(questionsInformationId)) {
                    return questionInformationList.get(i);
                }
    }
        throw new QuestionNotFoundException("Wrong id");
    }

    @Override
    public QuestionInformation postQuestionInformation(QuestionInformation questionInformation) {
        questionInformationList.add(questionInformation);
        return questionInformation;
    }

    @Override
    public void deleteQuestionInformationById(String questionsInformationId) {
        for (int i = 0; i < questionInformationList.size(); i++){
            if (questionInformationList.get(i).getQuestionInformationID().equals(questionsInformationId)) {
                questionInformationList.remove(i);
            }
        }
    }

}
