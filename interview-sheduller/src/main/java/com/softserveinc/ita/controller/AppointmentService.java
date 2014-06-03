package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Appointment;

/**
 * Created by mskryntc on 03.06.2014.
 */
public interface AppointmentService {

    public Appointment getAppointmentByApplicantId(String applicantId);

    public Appointment getAppointmentById(String appointmentId);
}
