package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.interview.ApplicantInterview;

import java.util.List;

public interface ApplicantInterviewDAO {

	ApplicantInterview addInterview(ApplicantInterview applicantInterview);
	List<ApplicantInterview> getAllInterview();
	ApplicantInterview getInterviewById(String id);
	ApplicantInterview updateInterview(ApplicantInterview applicantInterview);
	ApplicantInterview deleteInterviewById(String id);

}
