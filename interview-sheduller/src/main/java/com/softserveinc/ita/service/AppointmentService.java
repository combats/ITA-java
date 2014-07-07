package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    String addAppointment(Appointment appointment);

    List<Appointment> getAppointmentByApplicantId(String applicantId);

    void removeAppointmentById(String appointmentId);

    Appointment getAppointmentByAppointmentId(String appointmentId);

    List<Appointment> getAppointmentsByDate(long date);

    void updateAppointment(Appointment appointment);
}