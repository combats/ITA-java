package com.softserveinc.ita.dao;

import java.util.*;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.User;

public class AppointmentDAOImpl implements AppointmentDAO {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private LinkedList<Appointment> appointmentsList;
    Date date = new Date();

    public AppointmentDAOImpl()

    {

        List<String> userIdList1 = new ArrayList<String>();
        String applicant1 = "1";
        String user1 = "1";
        String user2 = "2";
        String user3 = "3";
        Collections.addAll(userIdList1, user1, user2, user3);


        List<String> userIdList2 = new ArrayList<String>();
        String applicant2 = "2";
        Collections.addAll(userIdList2, user1, user2, user3);

        List<String> userIdList3 = new ArrayList<String>();
        String applicant3 = "3";
        Collections.addAll(userIdList3, user1, user2, user3);

        Appointment appointment_one = new Appointment(userIdList1, applicant1, date.getTime());
        Appointment appointment_two = new Appointment(userIdList2, applicant2, date.getTime()+ TOMORROW);
        Appointment appointment_three = new Appointment(userIdList3, applicant3, date.getTime()+ TOMORROW + TOMORROW);

        appointmentsList = new LinkedList<Appointment>();
        appointmentsList.add(appointment_one);
        appointmentsList.add(appointment_two);
        appointmentsList.add(appointment_three);

    }

    public Appointment getAppointmentByAppointmentID(String ID){

        return appointmentsList.get(Integer.parseInt(ID));
    }

    public String putAppointment(Appointment appointment){

        appointmentsList.add(appointment);
        return String.valueOf(appointmentsList.size() - 1);
    }

     public Appointment getAppointmentByApplicantId(String applicantId) {

        if (applicantId.equals("1")) {
            return appointmentsList.get(1);
        } else {
            return appointmentsList.get(2);
        }
    }

    public Appointment editAppointmentById(int id, Appointment appointment)  {

        for (int i = 0; i < appointmentsList.size(); i++){
            if (appointmentsList.get(i).getAppointmentId().equals(id))
                appointmentsList.add(i, appointment);
        }
        return appointmentsList.get(id);
    }
}