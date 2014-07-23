package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AppointmentDAOHibernate implements AppointmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Appointment getAppointmentByAppointmentId(String appointmentId) {
        return (Appointment) sessionFactory.getCurrentSession().get(Appointment.class, appointmentId);
    }

    @Override
    public String putAppointment(Appointment appointment) {
        return (String) sessionFactory.getCurrentSession().save(appointment);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Appointment> getAppointmentByApplicantId(String applicantId) {
        return (List<Appointment>) sessionFactory.getCurrentSession().createCriteria(Appointment.class)
                .add(Restrictions.like("applicantId", applicantId)).list();
    }

    @Override
    public void removeAppointmentById(String appointmentId) {
        Appointment appointment = (Appointment) sessionFactory.getCurrentSession().load(Appointment.class, appointmentId);
        if (appointment != null) {
            sessionFactory.getCurrentSession().delete(appointment);
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        sessionFactory.getCurrentSession().update(appointment);
    }

    @Override
    public String getAppointmentIdByGroupIdAndApplicantId(String groupId, String applicantId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Appointment.class)
                .setProjection(Projections.projectionList().add(Projections.property("GroupId"), groupId).add(Projections.property("ApplicantId"), applicantId));
        return (String) criteria.setProjection(Projections.property("id")).uniqueResult();
    }

    /*@SuppressWarnings("unchecked")*/
    @Override
    public List<Appointment> getAppointmentsByDate(long startOfDay, long endOfDay) {
        Session session = sessionFactory.getCurrentSession();
    /*     BetweenExpression expression= "startTime", startOfDay, endOfDay*/
        Criteria criteria = session.createCriteria(Appointment.class).add(Restrictions.between("startTime", startOfDay, endOfDay));
               List<Appointment> appointments =  (List<Appointment>)criteria.list();
        return appointments;
    }
}
/*Restrictions.between("startTime", startOfDay, endOfDay) */