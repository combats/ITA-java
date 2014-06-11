package com.softserveinc.ita.mvc.mvc;


import com.softserveinc.ita.mvc.BaseMVCTest;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class UserTests extends BaseMVCTest{
    private String userID;
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
    public void testDeleteUserByIDAndExpectedIsOk() throws Exception {
        userID = new String("123");
        mockMvc.perform(delete("/users" + "/" + userID).content(userID).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
    }

    @Test
    public void testDleteUserByIDAndExpectedDletedSuccessfully() throws Exception {
        userID = new String("123");
        String responce = mockMvc.perform(delete("/users" + "/" + userID).content(userID).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
        assertEquals("Deleted successfully", responce);
    }

    @Test
    public void testDeleteUserByIdAndExpectedDeleteException() throws Exception {
        userID = new String("124");
        String responce = mockMvc.perform(delete("/users" + "/" + userID).content(userID).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        assertEquals("Delete exception: there is no user with (" + userID + ") userID", responce);
    }
}
