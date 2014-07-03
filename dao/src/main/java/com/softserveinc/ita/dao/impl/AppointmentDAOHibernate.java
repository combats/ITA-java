package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AppointmentDAOHibernate implements AppointmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Appointment getAppointmentByAppointmentID(String appointmentId) {
        return (Appointment)sessionFactory.getCurrentSession().load(Appointment.class, appointmentId);
    }

    @Override
    public String putAppointment(Appointment appointment) {
        return (String) sessionFactory.getCurrentSession().save(appointment);
    }

    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        return (Appointment)sessionFactory.getCurrentSession().createCriteria(Appointment.class)
                .add(Restrictions.like("applicantId", applicantId)).uniqueResult();
    }

    @Override
    public void removeAppointmentById(String appointmentId) {
        Appointment appointment = (Appointment)sessionFactory.getCurrentSession().load(Appointment.class, appointmentId);
        if (appointment != null) {
            sessionFactory.getCurrentSession().delete(appointment);
        }
    }
}
