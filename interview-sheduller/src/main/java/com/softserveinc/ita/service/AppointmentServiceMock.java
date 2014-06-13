package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.dao.exceptions.AppointmentNotFoundException;
import com.softserveinc.ita.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;

public class AppointmentServiceMock implements AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    public Appointment getAppointmentByAppointmentID(int ID) {
        return appointmentDAO.getAppointmentByAppointmentID(ID);
    }

    public Appointment getAppointmentByApplicantId(String applicantId) {
        return appointmentDAO.getAppointmentByApplicantId(applicantId);
    }

    public String putAppointment(Appointment appointment) {
        return appointmentDAO.putAppointment(appointment);
    }

    public void removeAppointmentById(String appointmentId) throws AppointmentNotFoundException{
        appointmentDAO.removeAppointmentById(appointmentId);
    }


}
