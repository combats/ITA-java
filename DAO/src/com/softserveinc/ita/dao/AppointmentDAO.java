package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Appointment;

import java.util.List;

public interface AppointmentDAO {

    Appointment getAppointmentByAppointmentID(int i);
    String putAppointment(Appointment appointment);
    Appointment getAppointmentByApplicantId(String applicantId);
    List<Appointment> getAppointmentsByDate(long date);

}
