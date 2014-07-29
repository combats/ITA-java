package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.interview.ApplicantInterview;
import com.softserveinc.ita.entity.interview.InterviewQuestion;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class ApplicantInterviewDAOTests extends BaseDAOTest {

    @Autowired
    private ApplicantInterviewDAO applicantInterviewDAO;
    @Autowired
    private SessionFactory sessionFactory;

	@Test
	public void testAddNewApplicantInterview() {
		ApplicantInterview applicantInterview = new ApplicantInterview();
		applicantInterview.setApplicantId("TestApplicantId");
		applicantInterview.setFinalComment("TestFinalComment");
		applicantInterview
				.setUsersId(new ArrayList<>(Arrays
						.asList("TestUserIdOne", "TestUserIdTwo")));
		InterviewQuestion oneQuestion = new InterviewQuestion();
		oneQuestion.setQuestion("TestQuestion");
		oneQuestion.setComment("TestComment");
		oneQuestion.setMark(1);
		oneQuestion.setWeight(4);
		InterviewQuestion twoQuestion = new InterviewQuestion();
		twoQuestion.setQuestion("TestQuestion");
		twoQuestion.setComment("TestComment");
		twoQuestion.setMark(1);
		twoQuestion.setWeight(4);
		applicantInterview
				.setQuestions(new ArrayList<InterviewQuestion>(Arrays
						.asList(oneQuestion, twoQuestion)));
		List<InterviewQuestion> questions = new ArrayList<>();
		questions.add(oneQuestion);
		questions.add(twoQuestion);
		applicantInterview.setQuestions(questions);
		ApplicantInterview actual = applicantInterviewDAO.addInterview(applicantInterview);
		assertEquals(applicantInterview, actual);
		assertNotNull(actual.getId());

	}



}