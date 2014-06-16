package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;
import java.util.*;

public class AppointmentServiceMock implements AppointmentService {

    @Override
    public Appointment editAppointmentById(int id, Appointment appointment) throws AppointmentNotFoundException {

        final int TOMORROW = 24 * 60 * 60 * 1000;

        Date date = new Date();

        List<String> userIdList1 = new ArrayList<>();
        List<String> applicantIdList1 = new ArrayList<>();
        Collections.addAll(userIdList1, "First_user", "Second_user", "Third_user");
        Collections.addAll(applicantIdList1, "First_applicant");

        List<String> userIdList2 = new ArrayList<>();
        List<String> applicantIdList2 = new ArrayList<>();
        Collections.addAll(userIdList1, "First_user", "Second_user", "Third_user");
        Collections.addAll(applicantIdList1, "Seconf_applicant");

        Appointment appointment_one = new Appointment(userIdList1, applicantIdList1, date.getTime());
        Appointment appointment_two = new Appointment(userIdList2, applicantIdList2, date.getTime()+ TOMORROW);

        LinkedList<Appointment> appointmentsList = new LinkedList<>();
        appointmentsList.add(appointment_one);
        appointmentsList.add(appointment_two);

        if (id > appointmentsList.size()) throw new AppointmentNotFoundException();

        appointmentsList.add(id, appointment);
        return appointmentsList.get(id);
    }

}
