package com.softserveinc.ita.mvc.impl;

import com.softserveinc.ita.mvc.MvcGroupBaseTest;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class MvcGroupTest extends MvcGroupBaseTest {

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
                get("/groups/status")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGroupsAndExpectJsonType() throws Exception {
        mockMvc.perform(
                get("/groups/status")
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
