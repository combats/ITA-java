package com.softserveinc.ita.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.softserveinc.ita.entity.Appointment;

public class AppointmentDAOImpl implements AppointmentDAO {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private LinkedList<Appointment> appointmentsList;

    public AppointmentDAOImpl(){
                 }

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
        return String.valueOf(appointmentsList.size());
    }
}