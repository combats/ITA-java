package com.softserveinc.ita.mvc;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
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

    private MockMvc mockMvc;

    @Autowired
    InterviewService interviewService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    InterviewFactory interviewFactory;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetInterviewByAppointmentIDAndExpectIsAccepted() throws Exception {

        mockMvc.perform(
                get("/interviews/" + "1")
        )
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetInterviewByExistingAppointmentIdAndExpectOk() throws Exception {

        MvcResult ExpectingObject = mockMvc.perform(
                get("/interviews/1")
        )
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
      System.out.println(ExpectingObject.getResponse().getContentAsString());
    }

    @Test
    public void testGetInterviewByNotExistingAppointmentIdAndExpectOk() throws Exception {

        MvcResult ExpectingObject = mockMvc.perform(
                get("/interviews/3")
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
    public void testRemoveInterviewByAppointmentIdAndExpectOk() throws Exception {

        mockMvc.perform(
                delete("/interviews/1")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetInterviewByAppointmentIdAndExpectInterviewEqualExpectedOne() throws HttpRequestException, WrongCriteriaException{
        Interview actual = interviewService.getInterviewByAppointmentID("1");
        Interview expected = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
        expected.setInterviewId("1");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetInterviewByApplicantIdAndExpectInterviewEqualExpectedOne() throws HttpRequestException, WrongCriteriaException {
        List<Interview> actual = interviewService.getInterviewByApplicantID("1");
        Interview expected = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
        expected.setInterviewId("1");
        assertEquals(expected, actual.get(0));
    }

}
