package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mskryntc on 03.06.2014.
 */
public class AppointmentServiceMock implements AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        return appointmentDAO.getAppointmentByApplicantId(applicantId);
    }

    @Override
    public Appointment getAppointmentByAppointmentID(int ID) {
        return appointmentDAO.getAppointmentByAppointmentID(ID);
    }

    @Override
    public String putAppointment(Appointment appointment) {
        return appointmentDAO.putAppointment(appointment);
    }
}
