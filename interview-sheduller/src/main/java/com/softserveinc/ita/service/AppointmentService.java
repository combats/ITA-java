package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 17.06.14
 * Time: 1:02
 * To change this template use File | Settings | File Templates.
 */
public interface AppointmentService {

    Appointment editAppointmentById(int id, Appointment appointment) throws AppointmentNotFoundException;

}

