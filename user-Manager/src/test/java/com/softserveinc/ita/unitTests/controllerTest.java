package com.softserveinc.ita.unitTests;

import com.softserveinc.ita.BaseTest;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
public class ControllerTest extends BaseTest {
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
}
