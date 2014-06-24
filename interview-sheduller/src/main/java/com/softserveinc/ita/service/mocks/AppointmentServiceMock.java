package com.softserveinc.ita.service.mocks;


import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;

import java.util.*;

public class AppointmentServiceMock implements AppointmentService {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private LinkedList<Appointment> appointmentsList;

    {
        String applicantId = "testApplicantId";
        String appointmentId = "testAppointmentId";

        List<String> users = new ArrayList<>();
        users.add("testUserId");
        Appointment appointment1 = new Appointment(users, applicantId, 1401866602L + TOMORROW);
        appointment1.setAppointmentId(appointmentId);

        List<String> users2 = new ArrayList<>();
        Appointment appointment2 = new Appointment(users, applicantId, 1401866603L + TOMORROW);
        appointment2.setAppointmentId(appointmentId);

        List<String> users3 = new ArrayList<>();
        Appointment appointment3 = new Appointment(users, applicantId, 1401866604L + TOMORROW);
        appointment3.setAppointmentId(appointmentId);

        appointmentsList = new LinkedList<>();
        Collections.addAll(appointmentsList, appointment1, appointment2, appointment3);
    }


    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        if (applicantId.equals("testApplicantId")) {
            return appointmentsList.get(0);
        } else {
            return appointmentsList.get(2);
        }
    }

    @Override
    public void removeAppointmentById(String appointmentId) {

    }

    @Override
    public Appointment getAppointmentByAppointmentId(String appointmentId) {
        if (appointmentId.equals("testAppointmentId")) {
            return appointmentsList.get(0);
        } else {
            return appointmentsList.get(2);
        }
    }

    @Override
    public String addAppointment(Appointment appointment) {
        return "testAppointmentId";
    }


}
