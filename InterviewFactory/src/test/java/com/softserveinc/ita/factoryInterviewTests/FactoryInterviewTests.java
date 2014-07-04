package com.softserveinc.ita.factoryInterviewTests;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 26.06.14
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public class FactoryInterviewTests extends BaseFactoryInterviewTests {

    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    private JsonUtil interviewUtilJson;

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
        user1.setQuestion(questionsList1);
        List<Question> questionsList2 = new ArrayList<Question>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestion(questionsList1);
        List<Question> questionsList3 = new ArrayList<Question>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestion(questionsList1);
        Collections.addAll(usersIdList, user1.getId(), user2.getId(), user3.getId());
    }

    List<String> appointmentIdList = new ArrayList<String>();{
        appointment1 = new Appointment(usersIdList, applicant1.getApplicantID(), startTime);
        appointment1.setAppointmentId("1");
        appointment2 = new Appointment(usersIdList, applicant2.getApplicantID(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
        appointmentIdList.add(appointment1.getAppointmentId());
        appointmentIdList.add(appointment2.getAppointmentId());
    }

    @Test
    public void testCreateInterviewWithoutQuestions() throws IOException, ParseException, JSONException, HttpRequestException, org.json.simple.parser.ParseException {
    Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create(appointmentIdList.get(0));

    Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create(appointmentIdList.get(1));
        interview1.setInterviewId("1");
        interview2.setInterviewId("2");

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithoutQuestions1.json")));
        Object object1 = parser.parse(reader1);
        String json1 = object1.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(interview1), json1, true);

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithoutQuestions2.json")));
        Object object2 = parser.parse(reader2);
        String json2 = object2.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(interview2), json2, true);

    }

    @Test
    public void testCreateInterviewWithStandardQuestions() throws JSONException, IOException, ParseException, HttpRequestException, org.json.simple.parser.ParseException {
        Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithStandardQuestions).create(appointmentIdList.get(0));
        Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithStandardQuestions).create(appointmentIdList.get(1));
        interview1.setInterviewId("1");
        interview2.setInterviewId("2");

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithStandardQuestions1.json")));
        Object object1 = parser.parse(reader1);
        String json1 = object1.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(interview1), json1, true);

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithStandardQuestions2.json")));
        Object object2 = parser.parse(reader2);
        String json2 = object2.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(interview2), json2, true);
    }

    @Test
    public void testCreateInterviewWithUserAndStandardQuestions() throws JSONException, IOException, ParseException, HttpRequestException, org.json.simple.parser.ParseException {
        Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointmentIdList.get(0));
        Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create(appointmentIdList.get(1));
        interview1.setInterviewId("1");
        interview2.setInterviewId("2");

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithUserAndStandardQuestions1.json")));
        Object object1 = parser.parse(reader1);
        String json1 = object1.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(interview1), json1, true);

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithUserAndStandardQuestions2.json")));
        Object object2 = parser.parse(reader2);
        String json2 = object2.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(interview2), json2, true);
    }
}
