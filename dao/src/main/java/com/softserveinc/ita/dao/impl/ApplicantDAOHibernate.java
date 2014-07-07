package com.softserveinc.ita.dao.impl;


import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicantDAOHibernate implements ApplicantDAO {

    @Autowired
    SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Applicant> getApplicants() {
        List<Applicant> allApplicants = (List<Applicant>) sessionFactory.getCurrentSession().createCriteria(Applicant.class).list();
        return allApplicants;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Applicant> getApplicantsInAGroup(String groupId) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Applicant>) session.createCriteria(Applicant.class)
                .add(Restrictions.eq("groupId", groupId)).list();
    }

    @Override
    public Applicant getApplicantById(String applicantId) {
        return (Applicant) sessionFactory.getCurrentSession().load(Applicant.class, applicantId);
    }

    @Override
    public Applicant addNewApplicant(Applicant applicant) {
        Session session = sessionFactory.getCurrentSession();
        String applicantId = (String) session.save(applicant);
        return (Applicant) session.load(Applicant.class, applicantId);
    }

    @Override
    public Applicant editApplicant(Applicant applicant) {
        String applicantId = applicant.getId();
        Session session = sessionFactory.getCurrentSession();
        session.update(applicant);
        return (Applicant) session.load(Applicant.class, applicantId);
    }
}
