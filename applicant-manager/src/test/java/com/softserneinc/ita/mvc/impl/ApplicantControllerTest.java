package com.softserneinc.ita.mvc.impl;

import com.google.gson.reflect.TypeToken;
import com.softserneinc.ita.mvc.BaseMVCTest;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class ApplicantControllerTest extends BaseMVCTest {
    private MockMvc mockMvc;
    List<Applicant> standart;

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
    public void testGetApplicantsListAngExpectedIsOk() throws Exception {
        mockMvc.perform(get("/applicants")
               .content("get applicants list"))
               .andExpect(status()
               .isOk());

    }

    @Test
    public void testGetApplicantsListAngExpectedValid() throws Exception {
        standart = new ArrayList<>();

        standart.add(new Applicant("123"));
        standart.add(new Applicant("124"));
        standart.add(new Applicant("125"));

        String responce = mockMvc.perform(get("/applicants")
                .content("get applicants list"))
                .andExpect(status()
                .isOk())
                .andReturn().getResponse().getContentAsString();

        String jsonstandart = jsonUtil.toJson(standart);

        assertEquals(jsonstandart, responce);
    }
}
