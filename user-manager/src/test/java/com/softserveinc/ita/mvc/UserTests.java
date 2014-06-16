package com.softserveinc.ita.mvc;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


public class UserTests extends BaseMVCTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    protected WebApplicationContext wac;
    @Mock
    private ArrayList<User> users;
    private MockMvc mockMvc;
    @Autowired
    private JsonUtil jsonUtil;

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