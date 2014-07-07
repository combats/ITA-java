package com.softserveinc.ita.service.mocks;


import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.service.AppointmentService;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AppointmentServiceMock implements AppointmentService {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private LinkedList<Appointment> appointmentsList;

    {
        long currentTime = System.currentTimeMillis();

        String applicantId = "testApplicantId";
        String appointmentId = "testAppointmentId";

        List<String> users = new ArrayList<>();
        users.add("testUserId");

        Group appointmentGroup1 = new Group(new Course("Java Script", "pen-net.png"), "street1", 111111111);
        User responsibleUser1 = new User("id1", "Svetlana", "Ivanova", 24, "sveta@gmail.com", "555-11-22");
        Appointment appointment1 = new Appointment(users, applicantId, 1401866602L + TOMORROW, responsibleUser1,
                appointmentGroup1);
        appointment1.setAppointmentId(appointmentId);

        Group appointmentGroup23 = new Group(new Course("C#", "pen-net.png"), "street23", 232322323);
        User responsibleUser2 = new User("id2", "Bogdan", "Bogdanov", 25, "bogdanov@gmail.com", "555-11-33");
        List<String> users2 = new ArrayList<>();
        Appointment appointment2 = new Appointment(users, applicantId, 1401866603L + TOMORROW, responsibleUser2,
                appointmentGroup23);
        appointment2.setAppointmentId(appointmentId);

        List<String> users3 = new ArrayList<>();
        Appointment appointment3 = new Appointment(users, applicantId, 1401866604L + TOMORROW, responsibleUser2,
                appointmentGroup23);
        appointment3.setAppointmentId(appointmentId);


        Appointment todayFirstAppointment = new Appointment(users, applicantId, currentTime);
        todayFirstAppointment.setAppointmentId(appointmentId);
        Appointment todaySecondAppointment = new Appointment(users, applicantId, currentTime);
        todaySecondAppointment.setAppointmentId(appointmentId);

        appointmentsList = new LinkedList<>();
        Collections.addAll(appointmentsList, appointment1, appointment2, appointment3, todayFirstAppointment
                , todaySecondAppointment);
    }


    @Override
    public List<Appointment> getAppointmentByApplicantId(String applicantId) {
        List<Appointment> result = new ArrayList<>();
        if (applicantId.equals("testApplicantId")) {
            result.add(appointmentsList.get(0));
        } else {
            result.add(appointmentsList.get(2));
        }
        return result;
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

    @Override
    public List<Appointment> getAppointmentsByDate(long date) {
        DateTime requirementDate = new DateTime(date);
        List<Appointment> resultList = new LinkedList<>();

        //if  required date less  than 1970 year then return empty result
        if (requirementDate.getMillis() < new DateTime(0).getMillis()) {
            return resultList;
        }

        for (Appointment appointment : appointmentsList) {

            DateTime appointmentDate = new DateTime(appointment.getStartTime());

            if (requirementDate.getYear() == appointmentDate.getYear() &&
                    requirementDate.getMonthOfYear() == appointmentDate.getMonthOfYear() &&
                    requirementDate.getDayOfMonth() == appointmentDate.getDayOfMonth()) {
                resultList.add(appointment);
            }
        }

        return resultList;

    }
}
