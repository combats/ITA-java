package com.softserveinc.ita.mvc;

import com.softserveinc.ita.entity.User;
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
    public void testDeleteUserByIDAndExpectedIsNoContent() throws Exception {
        String userID = new String("121");
        mockMvc.perform(delete("/users" + "/" + userID))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteUserByIDAndExpectedDeletedSuccessfully() throws Exception {
        String userID = new String("122");

        String goodResponse = jsonUtil.toJson("Deleted successfully User with userID: " + userID);

        mockMvc.perform(delete("/users" + "/" + userID))
                .andExpect(content().string(goodResponse));
    }

    @Test
    public void testDeleteUserByIdAndExpectedDeleteException() throws Exception {
        String userID = new String("124");
        mockMvc.perform(delete("/users" + "/" + userID))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteUserByIdDeleteExceptionMassageVerification() throws Exception {
        String userID = new String("124");

        String badResponse = jsonUtil.toJson("Delete exception: there is no user with following userID: " + userID);

        mockMvc.perform(delete("/users" + "/" + userID))
                .andExpect(content().string(badResponse));
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

}