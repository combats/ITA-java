package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantInterviewDAO;
import com.softserveinc.ita.entity.ApplicantInterview;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class ApplicantInterviewDAOHibernate implements ApplicantInterviewDAO {

	@Autowired
	private SessionFactory sessionFactory;

    @Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ApplicantInterview addInterview(ApplicantInterview applicantInterview) {
		Session currentSession = sessionFactory.getCurrentSession();
		Serializable id = currentSession.save(applicantInterview);
		return (ApplicantInterview) currentSession.get(ApplicantInterview.class, id);
	}

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @SuppressWarnings("unchecked")
    @Override
    public List<ApplicantInterview> getAllInterview() {
        return (List<ApplicantInterview>) sessionFactory.getCurrentSession()
                .createCriteria(ApplicantInterview.class).list();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public ApplicantInterview getInterviewById(String id) {
        return (ApplicantInterview) sessionFactory.getCurrentSession().get(ApplicantInterview.class, id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public ApplicantInterview updateInterview(ApplicantInterview applicantInterview) {
        Session session = sessionFactory.getCurrentSession();
        session.update(applicantInterview);
        return (ApplicantInterview) session.get(ApplicantInterview.class, applicantInterview.getId());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public ApplicantInterview deleteInterviewById(String id) {
        Session session = sessionFactory.getCurrentSession();
        ApplicantInterview interviewOnDelete = (ApplicantInterview) session.get(ApplicantInterview.class, id);
        session.delete(interviewOnDelete);
        return interviewOnDelete;
    }
}
