package com.softserveinc.ita.interview.mvc;

import com.softserveinc.ita.dao.ApplicantInterviewDAO;
import com.softserveinc.ita.entity.ApplicantInterview;
import com.softserveinc.ita.entity.InterviewQuestion;
import com.softserveinc.ita.interview.BaseMVCTest;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class InterviewControllerTests extends BaseMVCTest {
	private MockMvc mockMvc;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	private JsonUtil jsonUtil;

	@Autowired
	private ApplicantInterviewDAO applicantInterviewDAO;

	private String applicantId = "";
	private List<String> usersId = new ArrayList<>();
	private List<InterviewQuestion> questions = new ArrayList<>();
	private String finalComment = "";


	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testInterviewControllerShouldReturnIsCreated() throws Exception {
		ApplicantInterview applicantInterview = new ApplicantInterview(applicantId, usersId, questions, finalComment);
		String content = jsonUtil.toJson(applicantInterview);
		mockMvc.perform(
				post("/").content(content).contentType(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isCreated());
	}


	@Test
	public void testInterviewControllerShouldProduseValidJson() throws Exception {
		ApplicantInterview applicantInterview = new ApplicantInterview(applicantId, usersId, questions, finalComment);
		String content = jsonUtil.toJson(applicantInterview);
		mockMvc.perform(
				post("/").content(content).contentType(MediaType.APPLICATION_JSON)
		)
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testInterviewControllerShouldProduceValidBody() throws Exception {
		ApplicantInterview interview = new ApplicantInterview(applicantId, usersId, questions, finalComment);
		String content = jsonUtil.toJson(interview);
		MvcResult result = mockMvc.perform(
				post("/").content(content).contentType(MediaType.APPLICATION_JSON)
		).andReturn();
		ApplicantInterview actualInterview = jsonUtil.fromJson(result.getResponse().getContentAsString(), ApplicantInterview.class);
		assertEquals(interview, actualInterview);
		assertNotNull(actualInterview.getId());
	}


	@Test
	public void testInterviewControllerGetAllApplicantsInterviewShouldReturnValidValue() throws Exception {
		List<ApplicantInterview> allInterview = applicantInterviewDAO.getAllInterview();

		MvcResult result = mockMvc.perform(
				get("/")
		)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		String expectedJson = jsonUtil.toJson(allInterview);
		assertEquals(expectedJson, result.getResponse().getContentAsString());
	}


	@Test
	public void testIDInterviewControllerGetByIDApplicantsInterviewShouldReturnValidValue() throws Exception {
		ApplicantInterview applicantInterview = applicantInterviewDAO.getInterviewById("testID");

		MvcResult result = mockMvc.perform(
				get("/testID")
		)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		String responseSrt = result.getResponse().getContentAsString();
		ApplicantInterview actual = jsonUtil.fromJson(responseSrt, ApplicantInterview.class);
		assertEquals(applicantInterview,actual);
		assertEquals("testID",actual.getId());
	}

	@Test
	public void testInterviewControllerUpdateByIDApplicantsInterviewShouldReturnValidValue() throws Exception {
		ApplicantInterview applicantInterview = new ApplicantInterview();
		applicantInterview.setId("testId");
		MvcResult result = mockMvc.perform(
				put("/")
				.content(jsonUtil.toJson(applicantInterview))
				.contentType(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		ApplicantInterview actual = jsonUtil.fromJson(result.getResponse().getContentAsString(), ApplicantInterview.class);
		assertEquals(applicantInterview, actual);

	}

	@Test
	public void testInterviewControllerDeleteByIDApplicantsInterviewShouldReturnValidValue() throws Exception {
		ApplicantInterview applicantInterview = new ApplicantInterview();
		applicantInterview.setId("testId");
		MvcResult result = mockMvc.perform(
				delete("/testId")
		)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		ApplicantInterview actual = jsonUtil.fromJson(result.getResponse().getContentAsString(), ApplicantInterview.class);
		assertEquals(applicantInterview, actual);

	}

}
