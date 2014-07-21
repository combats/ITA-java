package com.softserveinc.ita.mvc.impl;

import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
import com.softserveinc.ita.mvc.MvcGroupBaseTest;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class MvcGroupTests extends MvcGroupBaseTest {

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
    public void testGetGroupsByStatusAndExpectIsOk() throws Exception {
        mockMvc.perform(
                get("/groups/BOARDING")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGroupsAndExpectIsOk() throws Exception {
        mockMvc.perform(
                get("/groups")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("groupsList"));
    }

    @Test
    public void testGetGroupsAndExpectJsonType() throws Exception {
        mockMvc.perform(
                get("/groups/BOARDING")
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCoursesAndExpectIsOk() throws Exception {
        mockMvc.perform(
                get("/groups/courses")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCoursesAndExpectJsonType() throws Exception {
        mockMvc.perform(
                get("/groups/courses")
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testPostGroupAndExpectJsonType() throws Exception {
        Group group = new Group("3");
        group.setCourse(new Course("Java"));
        group.setGroupStatus(Group.Status.IN_PROCESS);
        String jsonGroup = jsonUtil.toJson(group);
        mockMvc.perform(post("/groups/addGroup")
                .content(jsonGroup)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllGroupsAndExpectIsOk() throws Exception {
        mockMvc.perform(
                get("/groups/allGroups")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllGroupsAndExpectJsonType() throws Exception {
        mockMvc.perform(
                get("/groups/allGroups")
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
