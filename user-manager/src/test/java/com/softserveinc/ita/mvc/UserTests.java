package com.softserveinc.ita.mvc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


public class UserTests extends BaseMVCTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void testGetAllUsersIDAndExpectIsOK() throws Exception {
        mockMvc.perform(get("/users/userId")).
                andExpect(status().isOk());
    }
}