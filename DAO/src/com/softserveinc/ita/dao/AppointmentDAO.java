package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Appointment;

   public interface AppointmentDAO {

    public Appointment getAppointmentByAppointmentID(int i);
    public String putAppointment(Appointment appointment);
}
