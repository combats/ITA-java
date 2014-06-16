package com.softserveinc.ita.userMVC;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class UserMVCTest extends UserMVCBaseTest{
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
}
