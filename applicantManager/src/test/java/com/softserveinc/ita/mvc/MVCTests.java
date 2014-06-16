package com.softserveinc.ita.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
public class MVCTests extends BaseMVCTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetApplicantIDListAndExpectIsOk() throws Exception {
        mockMvc.perform(get("/applicants/applicantID"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetApplicantIDListAndExpectJson() throws Exception {
        mockMvc.perform(get("/applicants/applicantID"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
