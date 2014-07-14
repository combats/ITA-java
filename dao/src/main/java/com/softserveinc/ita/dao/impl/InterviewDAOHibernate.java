package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.InterviewDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Interview;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class InterviewDAOHibernate implements InterviewDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Interview getInterviewByAppointmentId(String appointmentId) {
        return (Interview) sessionFactory.getCurrentSession().get(Interview.class, appointmentId);
    }

    @Override
    public String putInterview(Interview interview) {
        return (String) sessionFactory.getCurrentSession().save(interview);
    }

    @Override
    public void removeInterviewByAppointmentId(String appointmentId) {
        Interview interview = (Interview) sessionFactory.getCurrentSession().load(Interview.class, appointmentId);
        if (interview != null) {
            sessionFactory.getCurrentSession().delete(interview);
        }
    }

    @Override
    public void updateInterview(Interview interview) {
        sessionFactory.getCurrentSession().update(interview);
    }

}
