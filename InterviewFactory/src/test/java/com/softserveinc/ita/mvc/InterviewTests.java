package com.softserveinc.ita.mvc;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.mainServices.InterviewService;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import exceptions.InterviewNotFoundException;
import exceptions.WrongCriteriaException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 01.07.14
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class InterviewTests extends BaseMVCTest {

    private static long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private MockMvc mockMvc;

    @Autowired
    InterviewService interviewService;
    private static Appointment appointment1 = null;
    private static Appointment appointment2 = null;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    private JsonUtil interviewUtilJson;

    @Autowired
    private JsonUtil jsonUtilJaxson;

    @Autowired
    private JsonUtil jsonUtil;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    //-------------VadimNaumenko mock for tests------------------------------------

    @BeforeClass
    public static void setUpOnce() {

        User user1 = new User("1", "IT Project Manager");
        User user2 = new User("2", "Software Developer");
        User user3 = new User("3", "HR Manager");

        Applicant applicant1 = new Applicant("1", "Gena");
        Applicant applicant2 = new Applicant("2", "Gesha");

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

        appointment1 = new Appointment(usersIdList, applicant1.getApplicantID(), startTime);
        appointment1.setAppointmentId("1");

        appointment2 = new Appointment(usersIdList, applicant2.getApplicantID(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
    }

    //-------------VadimNaumenko mock from tests

    @Test
    public void testGetInterviewByAppointmentIDAndExpectIsAccepted() throws Exception {

        mockMvc.perform(
                get("/interviews/appointments/" + appointment1.getAppointmentId())
        )
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetInterviewByExistingAppointmentIdAndExpectOk() throws Exception {

        MvcResult ExpectingObject = mockMvc.perform(
                get("/interviews/appointments/1")
        )
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

//        System.out.println(ExpectingObject.getResponse().getContentAsString());
//        Interview interview = jsonUtil.fromJson(ExpectingObject.getResponse().getContentAsString(), Interview.class);
//        interview.setInterviewId("1");
//
//        Interview expected = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create("1");
//        assertEquals(interview, expected);

      System.out.println(ExpectingObject.getResponse().getContentAsString());
    }

    @Test
    public void testGetInterviewByNotExistingAppointmentIdAndExpectOk() throws Exception {

        MvcResult ExpectingObject = mockMvc.perform(
                get("/interviews/appointments/3")
        )
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(ExpectingObject.getResponse().getContentAsString());
    }

    @Test
    public void testGetInterviewByApplicantIdAndExpectOk() throws Exception {

        MvcResult ExpectingObject = mockMvc.perform(
                get("/interviews/applicants/1")
        )
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(ExpectingObject.getResponse().getContentAsString());
    }

    @Test
    public void testRemoveInterviewByIdAndExpectOk() throws Exception {

        mockMvc.perform(
                delete("/interviews/1")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInterviewByInterviewIdAndExpectOk() throws Exception {

        MvcResult ExpectingObject = mockMvc.perform(
                get("/interviews/1")
        )
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(ExpectingObject.getResponse().getContentAsString());
    }

    @Test
    public void testGetInterviewByInterviewIdAndExpectInterviewEqualExpectedOne() throws InterviewNotFoundException, HttpRequestException {
        Interview actual = interviewService.getInterviewByInterviewID("1");
        Interview expected = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create("1");
        expected.setInterviewId("1");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetInterviewByApplicantIdAndExpectInterviewEqualExpectedOne() throws InterviewNotFoundException, HttpRequestException {
        List<Interview> actual = interviewService.getInterviewByApplicantID("1");
        Interview expected = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create("1");
        expected.setInterviewId("1");
        assertEquals(expected, actual.get(0));
    }

    @Test
    public void testGetInterviewByAppointmentIdAndExpectInterviewEqualExpectedOne() throws InterviewNotFoundException, WrongCriteriaException, HttpRequestException {
        Interview actual = interviewService.getInterviewByAppointmentID("1");
        Interview expected = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create("1");
        expected.setInterviewId("1");
        assertEquals(expected, actual);
    }

}
