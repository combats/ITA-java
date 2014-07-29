package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantInterviewDAO;
import com.softserveinc.ita.entity.interview.ApplicantInterview;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

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
}
