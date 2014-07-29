package com.softserveinc.ita.interview.mock;

import com.softserveinc.ita.dao.ApplicantInterviewDAO;
import com.softserveinc.ita.entity.ApplicantInterview;
import com.softserveinc.ita.entity.InterviewQuestion;

import java.util.ArrayList;
import java.util.List;


public class ApplicantInterviewDAOMockImpl implements ApplicantInterviewDAO {




	@Override
	public ApplicantInterview addInterview(ApplicantInterview applicantInterview) {

		applicantInterview.setId("TestApplicantInterviewID");
		return applicantInterview;

	}

	@Override
	public List<ApplicantInterview> getAllInterview() {
		List<String> usersId1 = new ArrayList<>();
		usersId1.add("testUserId1");
		usersId1.add("testUserId2");
		List<String> usersId2 = new ArrayList<>();
		usersId2.add("testUserId3");
		usersId2.add("testUserId4");
		List<InterviewQuestion> interviewQuestions1 = new ArrayList<>();
		InterviewQuestion interviewQuestion1 = new InterviewQuestion("Test Question1", "test comment1", 5, 3);
		InterviewQuestion interviewQuestion2 = new InterviewQuestion("Test Question2", "test comment2", 8, 4);
		interviewQuestions1.add(interviewQuestion1);
		interviewQuestions1.add(interviewQuestion2);
		List<InterviewQuestion> interviewQuestions2 = new ArrayList<>();
		InterviewQuestion interviewQuestion3 = new InterviewQuestion("Test Question3", "test comment3", 2, 1);
		InterviewQuestion interviewQuestion4 = new InterviewQuestion("Test Question4", "test comment4", 9, 3);
		interviewQuestions2.add(interviewQuestion3);
		interviewQuestions2.add(interviewQuestion4);

		ApplicantInterview applicantInterview1 = new ApplicantInterview("testApplicantId1", usersId1, interviewQuestions1, "test Final Comment1");
		applicantInterview1.setId("testId");
		ApplicantInterview applicantInterview2 = new ApplicantInterview("testApplicantId2", usersId2, interviewQuestions2, "test Final Comment2");
		applicantInterview2.setId("testId2");

		List<ApplicantInterview> applicantInterviews = new ArrayList<>();
		applicantInterviews.add(applicantInterview1);
		applicantInterviews.add(applicantInterview2);
		return applicantInterviews;
	}

	@Override
	public ApplicantInterview getInterviewById(String id) {

		ApplicantInterview applicantInterview =new ApplicantInterview();
		applicantInterview.setId(id);
		return applicantInterview;
	}

	@Override
	public ApplicantInterview updateInterview(ApplicantInterview applicantInterview) {
		return applicantInterview;
	}

	@Override
	public ApplicantInterview deleteInterviewById(String id) {
		ApplicantInterview applicantInterview = new ApplicantInterview();
		applicantInterview.setId(id);
		return applicantInterview;
	}
}
