package com.softserveinc.ita.service.mocks;


import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;
import com.softserveinc.ita.service.AppointmentService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AppointmentServiceMock implements AppointmentService {


    private long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private LinkedList<Appointment> appointmentsList;

    //-------------VadimNaumenko mock for tests------------------------------------

//
//    @Autowired
//    private User user1;
//
//    @Autowired
//    private User user2;
//
//    @Autowired
//    private User user3;
//
//    @Autowired
//    private Applicant applicant1;
//
//    @Autowired
//    private Applicant applicant2;
    //  @Autowired
    //  private User user2;

    //  @Autowired
    //  private User user3;

    Appointment appointment1;
    Appointment appointment2;

    private User user1 = new User("1", "IT Project Manager");
    private User user2 = new User("2", "Software Developer");
    private User user3 = new User("3", "HR Manager");

    private Applicant applicant1 = new Applicant("1", "Gena");
    private Applicant applicant2 = new Applicant("2", "Gesha");

    List<String> usersIdList = new ArrayList<String>(); {

        Question question1 = new Question("Have you ever were connected with quality assurance engineering?", 2);
        Question question2 = new Question("Have you ever were connected with database developing?", 3);
        Question question3 = new Question("Tell me something about JUnit testing.", 2);
        Question question4 = new Question("Your last book you read?", 3);
        Question question5 = new Question("Where did you study?", 2);
        Question question6 = new Question("Are you married?", 3);
        List<Question> questionsList1 = new ArrayList<>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setQuestion(questionsList1);
        List<Question> questionsList2 = new ArrayList<>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestion(questionsList1);
        List<Question> questionsList3 = new ArrayList<>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestion(questionsList1);
        Collections.addAll(usersIdList, user1.getUserID(), user2.getUserID(), user3.getUserID());
    }

    List<Appointment> appointmentList = new ArrayList<>();{
    appointment1 = new Appointment(usersIdList, applicant1.getId(), startTime);
        appointment1.setAppointmentId("1");
    appointment2 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
    }
    //-------------VadimNaumenko mock from tests

    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        if (applicantId.equals("1")) {
            return appointmentList.get(0);
        } else if (applicantId.equals("2")){
            return appointmentList.get(1);
        }
        else return null;
    }

    @Override
    public void removeAppointmentById(String appointmentId) {

    }

    @Override
    public Appointment getAppointmentByAppointmentId(String appointmentId) throws ApppoinmentNotFoundException{
        for (Appointment appointment : appointmentList){
            if (appointment.getAppointmentId().equals(appointmentId)) return appointment;

        }

        throw new ApppoinmentNotFoundException("Wrong Id");
    }

    @Override
    public String addAppointment(Appointment appointment) {
        return "testAppointmentId";
    }

    @Override
    public List<String> getUsersListByAppointmentId(String appointmentId) throws ApppoinmentNotFoundException {
        Appointment appointment = getAppointmentByAppointmentId(appointmentId);
        return appointment.getUserIdList();
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

    @Override
    public Appointment editAppointmentById(String appointmentId, Appointment appointment) throws ApppoinmentNotFoundException{
        for (int i=0; i < appointmentList.size(); i++){
            if (appointmentList.get(i).getAppointmentId().equals(appointmentId)){
                appointmentList.add(i, appointment);
            return appointmentList.get(i);
            }

        }
        throw new ApppoinmentNotFoundException("Wrong Id");
    }
}
