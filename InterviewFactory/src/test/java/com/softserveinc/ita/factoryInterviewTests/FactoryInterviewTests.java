package com.softserveinc.ita.factoryInterviewTests;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import exceptions.WrongCriteriaException;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

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

    JSONParser parser = new JSONParser();

    @Test
    public void testCreateInterviewWithoutQuestions() throws IOException, ParseException, JSONException, HttpRequestException, org.json.simple.parser.ParseException, WrongCriteriaException {
    Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("1");

    Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITHOUT_QUESTIONS).create("2");

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
    public void testCreateInterviewWithStandardQuestions() throws JSONException, IOException, ParseException, HttpRequestException, org.json.simple.parser.ParseException, WrongCriteriaException {
        Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITH_STANDARD_QUESTIONS).create("1");
        Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITH_STANDARD_QUESTIONS).create("2");

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
    public void testCreateInterviewWithUserAndStandardQuestions() throws HttpRequestException, IOException, org.json.simple.parser.ParseException, JSONException {
        Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITH_USER_AND_STANDARD_QUESTIONS).create("1");
        Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.INTERVIEW_WITH_USER_AND_STANDARD_QUESTIONS).create("2");

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
