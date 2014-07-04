package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;

import java.util.List;



public interface AppointmentService {
    String addAppointment(Appointment appointment);
    Appointment getAppointmentByApplicantId(String applicantId) throws AppointmentNotFoundException;
    void removeAppointmentById(String appointmentId);



    List<Appointment> getAppointmentsByDate(long date);
    Appointment getAppointmentByAppointmentId(String appointmentId) throws AppointmentNotFoundException;

    Appointment editAppointmentById(String id, Appointment appointment) throws AppointmentNotFoundException;
}