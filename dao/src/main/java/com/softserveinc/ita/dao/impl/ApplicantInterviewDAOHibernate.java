package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantInterviewDAO;
import com.softserveinc.ita.entity.interview.ApplicantInterview;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class ApplicantInterviewDAOHibernate implements ApplicantInterviewDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ApplicantInterview addInterview(ApplicantInterview applicantInterview) {
		Session currentSession = sessionFactory.getCurrentSession();
		Serializable id = currentSession.save(applicantInterview);
		return (ApplicantInterview) currentSession.get(ApplicantInterview.class, id);
	}

	@Override
	public List<ApplicantInterview> getAllInterview() {
		return null;
	}

	@Override
	public ApplicantInterview getInterviewById(String id) {
		return null;
	}

	@Override
	public ApplicantInterview updateInterview(ApplicantInterview applicantInterview) {
		return null;
	}

	@Override
	public ApplicantInterview deleteInterviewById(String id) {
		return null;
	}
}
