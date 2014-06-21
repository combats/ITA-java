package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Appointment;

   public interface AppointmentDAO {

    Appointment getAppointmentByAppointmentID(String i);
    String putAppointment(Appointment appointment);
    Appointment getAppointmentByApplicantId(String applicantId);
    Appointment editAppointmentById(int id, Appointment appointment);
}
