package com.softserveinc.ita;
import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.InterviewFactory;
import com.softserveinc.ita.utils.JsonUtil;

import exceptions.WrongCriteriaException;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.skyscreamer.jsonassert.JSONAssert;



import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 19.06.14
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */

public class InterviewFactoryTests extends BaseInterviewFactoryTests {

    @Autowired
    private JsonUtil interviewUtilJson;

    @Autowired
    private User user1;

    @Autowired
    private User user2;

    @Autowired
    private User user3;

    @Autowired
    private Applicant applicant1;

    @Autowired
    private Applicant applicant2;

    private long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    Appointment appointment1;
    Appointment appointment2;

    private List<String> Users = new ArrayList<String>();
    JSONParser parser = new JSONParser();

    @Before
    public void setUp() {
        Users.add(user1.getUserID());
        Users.add(user2.getUserID());
        Users.add(user3.getUserID());

        appointment1 = new Appointment(Users, applicant1.getId(), startTime);
        appointment1.setAppointmentId("1");
        appointment2 = new Appointment(Users, applicant2.getId(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
    }

    @Test
    public void testCreateInterviewWithoutQuestions() throws Exception {
        Interview InterviewWithoutQuestions1 =
                InterviewFactory.getInterview(appointment1.getAppointmentId(), InterviewType.InterviewWithoutQuestions);
        Interview InterviewWithoutQuestions2 =
                InterviewFactory.getInterview(appointment2.getAppointmentId(), InterviewType.InterviewWithoutQuestions);


        BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithoutQuestions1.json")));
        Object object1 = parser.parse(reader1);
        String json1 = object1.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(InterviewWithoutQuestions1), json1, true);

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithoutQuestions2.json")));
        Object object2 = parser.parse(reader2);
        String json2 = object2.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(InterviewWithoutQuestions2), json2, true);
    }

    @Test
    public void testCreateInterviewWithStandardQuestions() throws Exception {
        Interview InterviewWithStandardQuestions1 =
                InterviewFactory.getInterview(appointment1.getAppointmentId(), InterviewType.InterviewWithStandardQuestions);
        Interview InterviewWithStandardQuestions2 =
                InterviewFactory.getInterview(appointment2.getAppointmentId(), InterviewType.InterviewWithStandardQuestions);


        BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithStandardQuestions1.json")));
        Object object1 = parser.parse(reader1);
        String json1 = object1.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(InterviewWithStandardQuestions1), json1, true);

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithStandardQuestions2.json")));
        Object object2 = parser.parse(reader2);
        String json2 = object2.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(InterviewWithStandardQuestions2), json2, true);
    }

    @Test
    public void testCreateInterviewWithUserAndStandardQuestions() throws Exception {
        Interview InterviewWithUserAndStandardQuestions1 =
                InterviewFactory.getInterview(appointment1.getAppointmentId(), InterviewType.InterviewWithUserAndStandardQuestions);
        Interview InterviewWithUserAndStandardQuestions2 =
                InterviewFactory.getInterview(appointment2.getAppointmentId(), InterviewType.InterviewWithUserAndStandardQuestions);


        BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithUserAndStandardQuestions1.json")));
        Object object1 = parser.parse(reader1);
        String json1 = object1.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(InterviewWithUserAndStandardQuestions1), json1, true);

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(
                        "/JsonTestInterviewWithUserAndStandardQuestions2.json")));
        Object object2 = parser.parse(reader2);
        String json2 = object2.toString();

        JSONAssert.assertEquals(interviewUtilJson.toJson(InterviewWithUserAndStandardQuestions2), json2, true);
    }
//
//    @Rule
//    public ExpectedException thrown= ExpectedException.none();

//    @Test
//    public void testCreateInterviewWithWrongCriteriaAndGetException() throws WrongCriteriaException {
//        thrown.expect(WrongCriteriaException.class);
//        thrown.expectMessage("Wrong criteria");
//
//        Interview InterviewWithUserAndStandardQuestions1 = InterviewFactory.getInterview(appointment1);
//    }
}

