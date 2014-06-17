package com.softserveinc.ita.mvc;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ApplicantControllerTest extends BaseMVCTest {
    private MockMvc mockMvc;
    private String groupID;
    private String dbJson;
    private List<Applicant> dbStandart;

    @Autowired
    private JsonUtil jsonUtil;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WebApplicationContext wac;

    public ApplicantControllerTest() {
        dbStandart = new ArrayList<>();
    }

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
     public void testGetAllApplicantsByGroupId() throws Exception {
        groupID = "1";

        mockMvc.perform(get("/applicants/groups/" + groupID))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllApplicantsByGroupIdAndExpectedValidList() throws Exception {
        dbStandart.add(new Applicant("123"));
        dbStandart.add(new Applicant("124"));
        dbStandart.add(new Applicant("125"));

        dbJson = jsonUtil.toJson(dbStandart);

        groupID = "1";

        mockMvc.perform(get("/applicants/groups/" + groupID))
                .andExpect(content().string(dbJson));
    }

    @Test
    public void testGetAllApplicantsByGroupIdAndExceptionExpected() throws Exception {
        groupID = "2";

        String response = jsonUtil.toJson("group not found");

        mockMvc.perform(get("/applicants/groups/" + groupID))
                .andExpect(content().string(response));
    }
}
