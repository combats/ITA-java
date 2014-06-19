package com.softserveinc.ita.dao;

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
        sessionFactory.getCurrentSession().save(appointment);
        return "{}";
    }

    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        return (Appointment)sessionFactory.getCurrentSession().createCriteria(Appointment.class)
                .add(Restrictions.like("ApplicantId", applicantId));
    }

    @Override
    public void removeAppointmentById(String appointmentId) {
        Appointment appointment = (Appointment)sessionFactory.getCurrentSession().load(Appointment.class, appointmentId);
        if (appointment != null) {
            sessionFactory.getCurrentSession().delete(appointment);
        }
    }
}
