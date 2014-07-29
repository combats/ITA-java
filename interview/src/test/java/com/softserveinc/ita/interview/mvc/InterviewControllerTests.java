package com.softserveinc.ita.interview.mvc;

import com.softserveinc.ita.entity.interview.ApplicantInterview;
import com.softserveinc.ita.entity.interview.InterviewQuestion;
import com.softserveinc.ita.interview.BaseMVCTest;
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
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

//	@Test
//	public void testInterviewControllerShouldProduceValidBody() throws Exception {
//		ApplicantInterview interview = new ApplicantInterview(applicantId, usersId, questions, finalComment);
//		String content = jsonUtil.toJson(interview);
//		MvcResult result = mockMvc.perform(
//				post("/").content(content).contentType(MediaType.APPLICATION_JSON)
//		).andReturn();
//		ApplicantInterview actualInterview = jsonUtil.fromJson(result.getResponse().getContentAsString(), ApplicantInterview.class);
//		assertEquals(interview, actualInterview);
//		assertNotNull(actualInterview.getId());
//	}



}
