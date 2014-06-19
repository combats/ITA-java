package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.dao.exceptions.AppointmentNotFoundException;

/**
 * Created by mskryntc on 03.06.2014.
 */
public interface AppointmentService {
    Appointment getAppointmentByAppointmentID(int i);
    String putAppointment(Appointment appointment);
    Appointment getAppointmentByApplicantId(String applicantId);
    void removeAppointmentById(String appointmentId) throws AppointmentNotFoundException;
}
