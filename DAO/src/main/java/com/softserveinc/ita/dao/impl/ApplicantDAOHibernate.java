package com.softserveinc.ita.dao.impl;


import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
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
        return sessionFactory.getCurrentSession().createCriteria(Applicant.class).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Applicant> getApplicantsInAGroup(String groupID) {
        return sessionFactory.getCurrentSession().createCriteria(Applicant.class).add(Restrictions.eq("GroupId", groupID)).list();
    }

    @Override
    public Applicant getApplicantById(String applicantId) {
        return (Applicant)sessionFactory.getCurrentSession().load(Applicant.class, applicantId);
    }

    @Override
    public Applicant addNewApplicant(Applicant applicant) {
        return (Applicant)sessionFactory.getCurrentSession().save(applicant);
    }

    @Override
    public Applicant editApplicant(Applicant applicant) {
        sessionFactory.getCurrentSession().update(applicant);
        return null;
    }
}
