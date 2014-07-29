package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.ApplicantInterview;
import com.softserveinc.ita.entity.InterviewQuestion;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
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
		ApplicantInterview applicantInterviewOne = new ApplicantInterview();
        applicantInterviewOne.setId("1");
		applicantInterviewOne.setApplicantId("TestApplicantId");
		applicantInterviewOne.setFinalComment("TestFinalComment");
		applicantInterviewOne
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
		applicantInterviewOne
				.setQuestions(new ArrayList<InterviewQuestion>(Arrays
                        .asList(oneQuestion, twoQuestion)));
		List<InterviewQuestion> questions = new ArrayList<>();
		questions.add(oneQuestion);
		questions.add(twoQuestion);
		applicantInterviewOne.setQuestions(questions);
		ApplicantInterview actual = applicantInterviewDAO.addInterview(applicantInterviewOne);
		assertEquals(applicantInterviewOne, actual);
		assertNotNull(actual.getId());

	}

    @Test
    public void testGetAllApplicantInterview() {
        ApplicantInterview applicantInterviewOne = new ApplicantInterview();
        applicantInterviewOne.setId("1");
        applicantInterviewOne.setApplicantId("TestApplicantId");
        applicantInterviewOne.setFinalComment("TestFinalComment");
        applicantInterviewOne
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
        applicantInterviewOne
                .setQuestions(new ArrayList<InterviewQuestion>(Arrays
                        .asList(oneQuestion, twoQuestion)));
        List<InterviewQuestion> questions = new ArrayList<>();
        questions.add(oneQuestion);
        questions.add(twoQuestion);
        applicantInterviewOne.setQuestions(questions);

        ApplicantInterview applicantInterviewTwo = new ApplicantInterview();
        applicantInterviewTwo.setId("2");
        applicantInterviewTwo.setApplicantId("TestApplicantId");
        applicantInterviewTwo.setFinalComment("TestFinalComment");
        applicantInterviewTwo
                .setUsersId(new ArrayList<>(Arrays
                        .asList("TestUserIdOne", "TestUserIdTwo")));
        InterviewQuestion oneQuestion2 = new InterviewQuestion();
        oneQuestion2.setQuestion("TestQuestion");
        oneQuestion2.setComment("TestComment");
        oneQuestion2.setMark(1);
        oneQuestion2.setWeight(4);
        InterviewQuestion twoQuestion2 = new InterviewQuestion();
        twoQuestion2.setQuestion("TestQuestion");
        twoQuestion2.setComment("TestComment");
        twoQuestion2.setMark(1);
        twoQuestion2.setWeight(4);
        applicantInterviewTwo
                .setQuestions(new ArrayList<InterviewQuestion>(Arrays
                        .asList(oneQuestion2, twoQuestion2)));
        List<InterviewQuestion> questions2 = new ArrayList<>();
        questions2.add(oneQuestion2);
        questions2.add(twoQuestion2);
        applicantInterviewTwo.setQuestions(questions2);

        Session session = sessionFactory.getCurrentSession();
        session.save(applicantInterviewOne);
        session.save(applicantInterviewTwo);
        List<ApplicantInterview> allInterview = applicantInterviewDAO.getAllInterview();
        assertEquals(2, allInterview.size());
    }

    @Test
    public void testGetInterviewById() {
        ApplicantInterview applicantInterviewOne = new ApplicantInterview();
        applicantInterviewOne.setId("1");
        applicantInterviewOne.setApplicantId("TestApplicantId");
        applicantInterviewOne.setFinalComment("TestFinalComment");
        applicantInterviewOne
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
        applicantInterviewOne
                .setQuestions(new ArrayList<InterviewQuestion>(Arrays
                        .asList(oneQuestion, twoQuestion)));
        List<InterviewQuestion> questions = new ArrayList<>();
        questions.add(oneQuestion);
        questions.add(twoQuestion);
        applicantInterviewOne.setQuestions(questions);
        String id =(String) sessionFactory.getCurrentSession().save(applicantInterviewOne);
        ApplicantInterview actual = applicantInterviewDAO.getInterviewById(id);
        assertEquals(applicantInterviewOne, actual);
        assertEquals(id, actual.getId());
    }

    @Test
    public void testUpdateInterview() {
        ApplicantInterview applicantInterviewOne = new ApplicantInterview();
        applicantInterviewOne.setId("1");
        applicantInterviewOne.setApplicantId("TestApplicantId");
        applicantInterviewOne.setFinalComment("TestFinalComment");
        applicantInterviewOne
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
        applicantInterviewOne
                .setQuestions(new ArrayList<InterviewQuestion>(Arrays
                        .asList(oneQuestion, twoQuestion)));
        List<InterviewQuestion> questions = new ArrayList<>();
        questions.add(oneQuestion);
        questions.add(twoQuestion);
        applicantInterviewOne.setQuestions(questions);

        Session session = sessionFactory.getCurrentSession();
        String applicantId = (String) session.save(applicantInterviewOne);
        applicantInterviewOne.setApplicantId("ExpectedApplicantId");
        ApplicantInterview actual = applicantInterviewDAO.updateInterview(applicantInterviewOne);
        assertEquals(applicantInterviewOne, actual);
        assertEquals(applicantId, actual.getId());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testDeleteInterview() {
        ApplicantInterview applicantInterviewOne = new ApplicantInterview();
        applicantInterviewOne.setId("1");
        applicantInterviewOne.setApplicantId("TestApplicantId");
        applicantInterviewOne.setFinalComment("TestFinalComment");
        applicantInterviewOne
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
        applicantInterviewOne
                .setQuestions(new ArrayList<InterviewQuestion>(Arrays
                        .asList(oneQuestion, twoQuestion)));
        List<InterviewQuestion> questions = new ArrayList<>();
        questions.add(oneQuestion);
        questions.add(twoQuestion);
        applicantInterviewOne.setQuestions(questions);

        Session session = sessionFactory.getCurrentSession();
        String id = (String) session.save(applicantInterviewOne);
        applicantInterviewDAO.deleteInterviewById(id);
        session.load(ApplicantInterview.class, id);
    }

}