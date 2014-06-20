package com.softserveinc.ita.mvc;

import com.softserveinc.ita.BaseITATest;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.utils.JsonUtil;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class ApplicantTest extends BaseITATest {

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
    public void testPostNewApplicantAndExpectIsOk() throws Exception {

        Applicant applicant = new Applicant();
        String applicantJson = jsonUtil.toJson(applicant);

        mockMvc.perform(
                 post("/applicants")
                        .content(applicantJson).contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testPostNewApplicantAndGetTheSameApplicant() throws Exception {

        String applicantJson = jsonUtil.toJson(new Applicant());

        String newApplicantJson = mockMvc.perform(
                post("/applicants")
                        .content(applicantJson).contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Applicant newApplicant = jsonUtil.fromJson(newApplicantJson, Applicant.class);

        String applicantFromServerJson = mockMvc.perform(
                get("/applicants/" + newApplicant.getId())
        )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Applicant applicantFromServer = jsonUtil.fromJson(applicantFromServerJson, Applicant.class);
        assertEquals(newApplicant, applicantFromServer);
    }
}