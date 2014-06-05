package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;

/**
 * Created by mskryntc on 03.06.2014.
 */
public interface AppointmentService {

    Appointment getAppointmentByApplicantId(String applicantId);

    void removeAppointmentById(String appointmentId) throws AppointmentNotFoundException;
}
