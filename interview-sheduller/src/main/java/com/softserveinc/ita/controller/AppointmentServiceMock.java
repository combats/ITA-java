package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Appointment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mskryntc on 03.06.2014.
 */
public class AppointmentServiceMock implements AppointmentService {

    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        return new Appointment(Arrays.asList("1", "2"), Arrays.asList("1", "2"), System.currentTimeMillis() + 10000);
    }

    @Override
    public Appointment getAppointmentById(String appointmentId) {
        return null;
    }
}
