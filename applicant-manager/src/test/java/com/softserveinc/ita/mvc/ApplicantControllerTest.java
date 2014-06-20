package com.softserveinc.ita.mvc;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    List<Applicant> standart;
    private MockMvc mockMvc;
    private String groupID;
    private String dbJson;
    private List<Applicant> dbStandart;

    public ApplicantControllerTest() {
        dbStandart = new ArrayList<>();
    }

    @Autowired
    private JsonUtil jsonUtil;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetApplicantsListAngExpectedIsOk() throws Exception {
        mockMvc.perform(get("/applicants")
               .content("get applicants list"))
               .andExpect(status()
               .isOk());

    }

    @Test
    public void testGetApplicantsListAngExpectedValid() throws Exception {
        standart = new ArrayList<>();

        standart.add(new Applicant("123"));
        standart.add(new Applicant("124"));
        standart.add(new Applicant("125"));

        String responce = mockMvc.perform(get("/applicants")
                .content("get applicants list"))
                .andExpect(status()
                        .isOk())
                .andReturn().getResponse().getContentAsString();

        String jsonstandart = jsonUtil.toJson(standart);

        assertEquals(jsonstandart, responce);
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

    @Test
    public void testGetApplicantAndExpectIsOk() throws Exception {
        mockMvc.perform(
                get("/applicants/id1")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testGetApplicantWithNotExistingIdAndExpectException() throws Exception{
        mockMvc.perform(
                get("/applicants/id4")
        )
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetApplicantAndExpectJsonType() throws Exception{
        mockMvc.perform(
                get("/applicants/id2")
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetApplicantWithExistingIdAndExpectCorrectApplicant() throws Exception{
        Applicant applicant = new Applicant("id2");
        String jsonApplicant = jsonUtil.toJson(applicant);
        mockMvc.perform(
                get("/applicants/id2")
        ).andExpect(content().string(jsonApplicant));
    }
}
