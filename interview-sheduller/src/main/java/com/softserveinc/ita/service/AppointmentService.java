package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.dao.exceptions.AppointmentNotFoundException;

public interface AppointmentService {
    Appointment getAppointmentByAppointmentID(int i);
    String putAppointment(Appointment appointment);
    Appointment getAppointmentByApplicantId(String applicantId);
    void removeAppointmentById(String appointmentId) throws AppointmentNotFoundException;

    Appointment getAppointmentByAppointmentID(String appointmentId);

    void putAppointment(Appointment appointment);
}
