package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;

import java.util.List;

public interface AppointmentService {
    String addAppointment(Appointment appointment);

    List<Appointment> getAppointmentByApplicantId(String applicantId) throws AppointmentNotFoundException;

    void removeAppointmentById(String appointmentId);

    List<Appointment> getAppointmentsByDate(long date);
    Appointment getAppointmentByAppointmentId(String appointmentId) throws AppointmentNotFoundException;

    void updateAppointment(Appointment appointment);

    String getAppointmentIdByGroupIdAndApplicantId(String groupId, String applicantId);
}