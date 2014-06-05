package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mskryntc on 03.06.2014.
 */
public class AppointmentServiceMock implements AppointmentService {

    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        List<String> applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "1", "2");
        Appointment appointmentOne = new Appointment(userIdList, applicantIdList, 1401951895035L);
        Appointment appointmentTwo = new Appointment(userIdList, applicantIdList, 1401952037427L);

        if (applicantId.equals("1")) {
            return appointmentOne;
        } else {
            return appointmentTwo;
        }
    }
}
