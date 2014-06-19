package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class AppointmentServiceMock implements AppointmentService {


    @Autowired
    AppointmentDAO dao;

    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        String applId = "3";
        Appointment appointmentOne = new Appointment(userIdList, applId, 1401951895035L);
        Appointment appointmentTwo = new Appointment(userIdList, applId, 1401952037427L);

        if (applicantId.equals("1")) {
            return appointmentOne;
        } else {
            return appointmentTwo;
        }
    }

    @Override
    public void removeAppointmentById(String appointmentId) throws AppointmentNotFoundException {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        String applId = "3";
        Appointment appointmentOne = new Appointment(userIdList, applId, 1401951895035L);
        Appointment appointmentTwo = new Appointment(userIdList, applId, 1401952037427L);

        Map<String, Appointment> appointments = new HashMap<>();
        appointments.put("1", appointmentOne);
        appointments.put("2", appointmentTwo);

        if (appointmentId.equals("1")) {
            Appointment remove = appointments.remove("1");
            if (remove == null) {
                throw new AppointmentNotFoundException();
            }
        } else {
            throw new AppointmentNotFoundException();
        }


    }

    @Override
    public Appointment getAppointmentByAppointmentID(String appointmentId) {
        return null;
    }

    @Override
    public void putAppointment(Appointment appointment) {
        //NOP
    }


}
