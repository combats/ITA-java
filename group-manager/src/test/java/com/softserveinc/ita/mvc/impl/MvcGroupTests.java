package com.softserveinc.ita.mvc.impl;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
import com.softserveinc.ita.mvc.MvcGroupBaseTest;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class MvcGroupTests extends MvcGroupBaseTest {

    private MockMvc mockMvc;

    @Autowired
    private JsonUtil jsonUtil;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetGroupsByStatusAndExpectIsOk() throws Exception {
        mockMvc.perform(
                get("/groups/Boarding")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGroupsAndExpectIsOk() throws Exception {
        mockMvc.perform(
                get("/groups")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("groupsList"));
    }

    @Test
    public void testGetGroupsAndExpectJsonType() throws Exception {
        mockMvc.perform(
                get("/groups/status")
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetGroupsByStatusAndExpectReasonWasConvertedToJson() throws Exception {
        mockMvc.perform(
                get("/groups/wrong").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonUtil.toJson(new ExceptionJSONInfo("Group status does not exist"))));
    }

    @Test
    public void testGetApplicantsByGroupIDAndExpectOk() throws Exception {
        mockMvc.perform(
                get("/groups/applicants/TestGroupID")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetApplicantsByGroupIDAndExpectValidJson() throws Exception {
        List<Applicant> applicants = new ArrayList<>();
        Applicant applicantOne = new Applicant("TestApplicantOneName", "TestApplicantOneSurname");
        Applicant applicantTwo = new Applicant("TestApplicantTwoName", "TestApplicantTwoSurname");
        Applicant applicantThree = new Applicant("TestApplicantThreeName", "TestApplicantThreeSurname");
        Collections.addAll(applicants, applicantOne, applicantTwo, applicantThree);
        String expected = jsonUtil.toJson(applicants);

        mockMvc.perform(
                get("/groups/applicants/TestGroupID")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void testGetApplicantsByGroupIDAndExpectMediaTypeJson() throws Exception {
        List<Applicant> applicants = new ArrayList<>();
        Applicant applicantOne = new Applicant("TestApplicantOneName", "TestApplicantOneSurname");
        Applicant applicantTwo = new Applicant("TestApplicantTwoName", "TestApplicantTwoSurname");
        Applicant applicantThree = new Applicant("TestApplicantThreeName", "TestApplicantThreeSurname");
        Collections.addAll(applicants, applicantOne, applicantTwo, applicantThree);
        String expected = jsonUtil.toJson(applicants);

        mockMvc.perform(
                get("/groups/applicants/TestGroupID")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
