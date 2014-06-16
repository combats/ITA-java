package com.softserveinc.ita.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.softserveinc.ita.entity.Appointment;

public class AppointmentDAOImpl implements AppointmentDAO {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private LinkedList<Appointment> appointmentsList;

    public AppointmentDAOImpl(){}

    {List<String> applicants = new ArrayList<>();
        applicants.add("testApplicantId");
        List<String> users = new ArrayList<>();
        users.add("testUserId");

        List<String> applicants2 = new ArrayList<>();
        applicants2.add("testApplicantIdWRONG");
        List<String> users2 = new ArrayList<>();
        users2.add("testUserId");

        List<String> applicants3 = new ArrayList<>();
        applicants3.add("testApplicantId");
        List<String> users3 = new ArrayList<>();
        users3.add("testUserId");

        appointmentsList = new LinkedList<>();
        appointmentsList.add(new Appointment(users, applicants, 1401866602 + TOMORROW));
        appointmentsList.add(new Appointment(users2, applicants2, 1401866603 + TOMORROW));
        appointmentsList.add(new Appointment(users3, applicants3, 1401866604 + TOMORROW));
    }

    public Appointment getAppointmentByAppointmentID(int ID){

        return appointmentsList.get(ID);
    }

    public String putAppointment(Appointment appointment){

        appointmentsList.add(appointment);
        return String.valueOf(appointmentsList.size() - 1);
    }

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