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

    @SuppressWarnings("unchecked")
    @Override
    public List<ApplicantInterview> getAllInterview() {
        return (List<ApplicantInterview>) sessionFactory.getCurrentSession()
                .createCriteria(ApplicantInterview.class).list();
    }

    @Override
    public ApplicantInterview getInterviewById(String id) {
        return (ApplicantInterview) sessionFactory.getCurrentSession().get(ApplicantInterview.class, id);
    }

    @Override
    public ApplicantInterview updateInterview(ApplicantInterview applicantInterview) {
        Session session = sessionFactory.getCurrentSession();
        session.update(applicantInterview);
        return (ApplicantInterview) session.get(ApplicantInterview.class, applicantInterview.getId());
    }

    @Override
    public ApplicantInterview deleteInterviewById(String id) {
        Session session = sessionFactory.getCurrentSession();
        ApplicantInterview interviewOnDelete = (ApplicantInterview) session.get(ApplicantInterview.class, id);
        session.delete(interviewOnDelete);
        return interviewOnDelete;
    }
}
