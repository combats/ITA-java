package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;

/**
 * Created by mskryntc on 03.06.2014.
 */
public interface AppointmentService {

    Appointment getAppointmentByApplicantId(String applicantId);
}
