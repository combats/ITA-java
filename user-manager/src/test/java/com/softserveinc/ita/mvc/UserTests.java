package com.softserveinc.ita.mvc;

import com.softserveinc.ita.controller.UserController;
import com.softserveinc.ita.entity.User;

import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
import com.softserveinc.ita.exception.InvalidUserIDException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.exception.UserException;
import com.softserveinc.ita.utils.JsonUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    public void testDeleteUserByIDAndExpectedIsOK() throws Exception {
        String userID = new String("121");
        mockMvc.perform(delete("/users" + "/" + userID))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserByIDAndExpectedDeletedSuccessfully() throws Exception {
        String userID = new String("122");

//        String goodResponse = jsonUtil.toJson(userID);
        mockMvc.perform(delete("/users" + "/" + userID))
               .andExpect(content().string(userID));
    }

    @Test
    public void testDeleteUserByIdAndExpectedDeleteException() throws Exception {
        String userID = new String("124");
        mockMvc.perform(delete("/users" + "/" + userID))
                .andExpect(status().isNotFound());
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
    public void testGetNonExistentUserAndGetInternalServerError() throws Exception {
        String userID = "-1";
        mockMvc.perform(
                get("/users/{userID}", userID))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetAllUsersIDAndExpectIsOK() throws Exception {
        mockMvc.perform(get("/users/userId")).
                andExpect(status().isOk());
    }

    @Test
    public void testEditUserWithEmptyFieldAndExpectBadRequest() throws Exception {
        User user = new User("id1");
        String jsonUser = jsonUtil.toJson(user);
        mockMvc.perform(
                put("/users").
                        content(jsonUser).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testEditUserWithNotExistingIdAndExpectNotFound() throws Exception {
        User user = new User("id4","Andrey",34);
        String jsonUser = jsonUtil.toJson(user);
        mockMvc.perform(
                put("/users").
                        content(jsonUser).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testEditUserWithExistingIdAndExpectIsoK() throws Exception {
        User user = new User("id3","Andrey",34);
        String jsonUser = jsonUtil.toJson(user);
        mockMvc.perform(
                put("/users").
                        content(jsonUser).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testEditUserWithExistingIdAndExpectEqualsWithResult() throws Exception{
        User user = new User("id3","Andrey",34);
        String jsonUser = jsonUtil.toJson(user);
        mockMvc.perform(
                put("/users").
                        content(jsonUser).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(content().string(jsonUser));
    }
    @Test
    public void testPostNewUserAndExpectIsCreated() throws Exception {
        User testUser = new User("3");
        String jsonUser = jsonUtil.toJson(testUser);
        mockMvc.perform(post("/users")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPostNewUserWithDuplicatedIDAndExpectInternalServerError() throws Exception {
        User testUser = new User("1");
        String jsonUser = jsonUtil.toJson(testUser);
        mockMvc.perform(post("/users")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetUserListAndExpectIsOk() throws Exception {
        mockMvc.perform(
                get("/users")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserListAndExpectJson() throws Exception {
        mockMvc.perform(
                get("/users"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetUserListAndExpectPreDefinedList() throws Exception {
        List<User> sampleUserList = new ArrayList<>();
        Collections.addAll(sampleUserList, new User("id3"), new User("idY"), new User("id09z"));
        String expectedResult = jsonUtil.toJson(sampleUserList);
        mockMvc.perform(
                get("/users"))
                .andExpect(content().string(expectedResult));
    }
    @Test
    public void testGetUserByIDAndExpectReasonWasConvertedToJson() throws Exception{
        mockMvc.perform(
                get("/users/-1").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonUtil.toJson(new ExceptionJSONInfo("Invalid user ID"))));
    }
}