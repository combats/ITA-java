package com.softserveinc.ita.service.mocks;


import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;

import java.util.*;

public class AppointmentServiceMock implements AppointmentService {


//    @Autowired
//    AppointmentDAO dao;

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
    public void removeAppointmentById(String appointmentId) {
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
        }
    }

    @Override
    public Appointment getAppointmentByAppointmentID(String appointmentId) {
        return null;
    }

    @Override
    public String putAppointment(Appointment appointment) {
        return "";
    }


}
