package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment getAppointmentByApplicantId(String applicantId);
    Appointment getAppointmentByAppointmentID(int ID);
    String putAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByDate(long date);
}
