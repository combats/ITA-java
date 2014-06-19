package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;

public interface AppointmentService {

    Appointment getAppointmentByApplicantId(String applicantId);

    void removeAppointmentById(String appointmentId) throws AppointmentNotFoundException;

    Appointment getAppointmentByAppointmentID(String appointmentId);

    void putAppointment(Appointment appointment);
}
