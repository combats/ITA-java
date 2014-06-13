package com.softserveinc.ita.dao;

import com.softserveinc.ita.dao.exceptions.AppointmentNotFoundException;
import com.softserveinc.ita.entity.Appointment;

   public interface AppointmentDAO {

    public Appointment getAppointmentByAppointmentID(int i);
    public String putAppointment(Appointment appointment);
    Appointment getAppointmentByApplicantId(String applicantId);
    void removeAppointmentById(String appointmentId) throws AppointmentNotFoundException;
}
