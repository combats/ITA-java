package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;
import com.softserveinc.ita.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Transactional
    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        return appointmentDAO.getAppointmentByApplicantId(applicantId);
    }

    @Transactional
    @Override
    public void removeAppointmentById(String appointmentId) throws AppointmentNotFoundException {
        appointmentDAO.removeAppointmentById(appointmentId);
    }

    @Transactional
    @Override
    public Appointment getAppointmentByAppointmentID(String appointmentId) {
        return appointmentDAO.getAppointmentByAppointmentID(appointmentId);
    }

    @Transactional
    @Override
    public void putAppointment(Appointment appointment) {
        appointmentDAO.putAppointment(appointment);
    }
}
