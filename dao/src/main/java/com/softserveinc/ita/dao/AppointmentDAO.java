package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Appointment;

import java.util.List;

public interface AppointmentDAO {

    Appointment getAppointmentByAppointmentID(String appointmentId);

    String putAppointment(Appointment appointment);

    List<Appointment> getAppointmentByApplicantId(String applicantId);

    void removeAppointmentById(String appointmentId);

    void updateAppointment(Appointment appointment);
}
