package com.softserveinc.ita.mvc;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.mvc.BaseMVCTest;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


public class UserTests extends BaseMVCTest {
    private MockMvc mockMvc;

    @Autowired
    private JsonUtil jsonUtil;

    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void testGetValidUserByIDAndExpectIsOK() throws Exception {
        String userID = "1";
        User testUser = new User(userID);
        String expectedJsonUser = jsonUtil.toJson(testUser);
        MvcResult result = mockMvc.perform(
                get("/users/{userID}", userID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(expectedJsonUser, result.getResponse().getContentAsString());         //compare expected test User and actual.
    }

    @Test
    public void testGetNonExistantUserAndGetInternalServerError() throws Exception {
        String userID = "-1";
        mockMvc.perform(
                get("/users/{userID}", userID))
                .andExpect(status().isInternalServerError());
    }
}