package com.softserveinc.ita.interviewingTests;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.questionInformationServices.QuestionsInformationServices;
import com.softserveinc.ita.interviewfactory.service.questionsBlocksServices.QuestionsBlockServices;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionNotFoundException;
import exceptions.QuestionsBlockNotFound;
import exceptions.WrongCriteriaException;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 08.07.14
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class InterviewingTests extends BaseInterviewingTests {

    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    private JsonUtil interviewUtilJson;

    @Autowired
    private JsonUtil  jsonUtilJaxson;

    @Autowired
    private QuestionsBlockServices questionsBlockServices;

    @Autowired
    private QuestionsInformationServices questionsInformationServices;

    //-------------------------------------Mocks----------------------------------------------------

    private long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    JSONParser parser = new JSONParser();

    Appointment appointment1;
    Appointment appointment2;

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
        applicant2.setId("2");
        appointment1 = new Appointment(usersIdList, applicant1.getId(), startTime);
        appointment1.setAppointmentId("1");
        appointment2 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
        appointmentIdList.add(appointment1.getAppointmentId());
        appointmentIdList.add(appointment2.getAppointmentId());
    }

    //-------------------------------------Mocks----------------------------------------------------

    @Test
    public void testGetQuestionsBlockByUserIdAndExpectOk() throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException {
        String userId = "1";
        QuestionsBlock questionsBlock = questionsBlockServices.getQuestionsBlockByUserId(userId, appointmentIdList.get(1));
      //  System.out.println(interviewUtilJson.toJson(questionsBlock));
        assertEquals(questionsBlock.getUser().getId(), userId);
    }

    @Test
    public void testGetStandardQuestionsBlockByIdAndExpectOk() throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException {
        QuestionsBlock questionsBlock = questionsBlockServices.getStandardQuestionsBlockFromInterview(appointmentIdList.get(1));
      //  System.out.println(interviewUtilJson.toJson(questionsBlock));
        assertEquals(questionsBlock.getUser(), null);
    }

    @Test
    public void testGetQuestionInformationByIdAndExpectOk() throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException, QuestionNotFoundException {
        String id = "1";
        QuestionInformation questionInformation = questionsInformationServices.getQuestionInformationById(id);
        System.out.println(interviewUtilJson.toJson(questionInformation));
        assertEquals(questionInformation.getQuestionInformationID(), id);
    }

    @Test
    public void editQuestionInformation() throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException, QuestionNotFoundException {
        QuestionInformation questionInformation = new QuestionInformation();
        questionInformation.setQuestion("wwwww");
        questionInformation.setQuestionsBlockId("1");
        questionInformation.setQuestionInformationID("1");
        questionInformation.setAnswer("wqwqwq");
        questionInformation.setWeight(2);
        questionInformation.setComment("ererer");
        questionInformation.setMark(3);
        QuestionInformation questionInformation1 = questionsInformationServices.editQuestionInformation(questionInformation);
        assertEquals(questionInformation, questionInformation1);
    }

}
