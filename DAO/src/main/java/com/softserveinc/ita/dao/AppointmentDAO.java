package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Appointment;

public interface AppointmentDAO {

    Appointment getAppointmentByAppointmentID(String appointmentId);

    String putAppointment(Appointment appointment);

    Appointment getAppointmentByApplicantId(String applicantId);

    void removeAppointmentById(String appointmentId);
}
