package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;

import java.util.List;

/**
 * Created by mskryntc on 03.06.2014.
 */
public interface AppointmentService {

    Appointment getAppointmentByApplicantId(String applicantId);



    List<Appointment> getAppointmentsByDay(long date);
}
