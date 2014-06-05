package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Appointment;

/**
 * Created by vshkotc on 03.06.2014.
 */
   public interface AppointmentDAO {

    public Appointment getAppointmentByAppointmentID(int i);
    public void putAppointment(Appointment appointment);
}
