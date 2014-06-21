package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;

import java.util.List;

public interface AppointmentService {
    String addAppointment(Appointment appointment);
    Appointment getAppointmentByApplicantId(String applicantId);
    void removeAppointmentById(String appointmentId);
    Appointment getAppointmentByAppointmentId(String appointmentId) throws ApppoinmentNotFoundException;
    List<String> getUsersListByAppointmentId(String appointmentId) throws ApppoinmentNotFoundException;
}