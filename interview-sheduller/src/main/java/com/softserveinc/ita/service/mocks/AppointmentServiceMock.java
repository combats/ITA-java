
package com.softserveinc.ita.service.mocks;


import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Question;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;
import com.softserveinc.ita.service.AppointmentService;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AppointmentServiceMock implements AppointmentService {


    private long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private LinkedList<Appointment> appointmentsList;

    //-------------VadimNaumenko mock for tests------------------------------------

    Appointment appointment1;
    Appointment appointment2;
    Appointment appointment3;

    private User user1 = new User("1", "IT Project Manager");

    private User user2 = new User("2", "Software Developer");
    private User user3 = new User("3", "HR Manager");

    private Applicant applicant1 = new Applicant("Gena", "1");

    private Applicant applicant2 = new Applicant("Gesha", "2");

    List<String> usersIdList = new ArrayList<String>(); {

        Question question1 = new Question("Have you ever were connected with quality assurance engineering?", 2);
        Question question2 = new Question("Have you ever were connected with database developing?", 3);
        Question question3 = new Question("Tell me something about JUnit testing.", 2);
        Question question4 = new Question("Your last book you read?", 3);
        Question question5 = new Question("Where did you study?", 2);
        Question question6 = new Question("Are you married?", 3);
        List<Question> questionsList1 = new ArrayList<>();
        Collections.addAll(questionsList1, question1, question2);
        user1.setId("1");
        user2.setId("2");
        user3.setId("3");
        user1.setQuestions(questionsList1);
        List<Question> questionsList2 = new ArrayList<>();
        Collections.addAll(questionsList2, question3, question4);
        user2.setQuestions(questionsList1);
        List<Question> questionsList3 = new ArrayList<>();
        Collections.addAll(questionsList3, question5, question6);
        user3.setQuestions(questionsList1);
        Collections.addAll(usersIdList, user1.getId(), user2.getId(), user3.getId());
    }

    List<Appointment> appointmentList = new ArrayList<>();{
        applicant1.setId("1");
        appointment1 = new Appointment(usersIdList, applicant1.getId(), startTime);
        appointment1.setAppointmentId("1");
        applicant2.setId("2");
        appointment2 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment2.setAppointmentId("2");
        appointment3 = new Appointment(usersIdList, applicant2.getId(), startTime + TOMORROW);
        appointment3.setAppointmentId("3");
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        appointmentList.add(appointment3);
    }
    //-------------VadimNaumenko mock from tests

    @Override
    public List<Appointment> getAppointmentByApplicantId(String applicantId) throws AppointmentNotFoundException {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointmentList){
            if (appointment.getApplicantId().equals(applicantId)) result.add(appointment);
        }
        if (result.isEmpty())throw new AppointmentNotFoundException("Wrong Id");
        return result;
    }

    @Override
    public void removeAppointmentById(String appointmentId) {

    }

    @Override
    public Appointment getAppointmentByAppointmentId(String appointmentId) throws AppointmentNotFoundException {
        for (Appointment appointment : appointmentList){
            if (appointment.getAppointmentId().equals(appointmentId)) return appointment;

        }

        throw new AppointmentNotFoundException("Wrong Id");
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

        for (Appointment appointment : appointmentList) {

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
    public void updateAppointment(Appointment appointment) {
        List<Appointment> appointments = new ArrayList<>();
        int TOMORROW = 24 * 60 * 60 * 1000;
        List<String> users = new ArrayList<>();
        users.add("testUserId");
        appointments.add(new Appointment(users, "testApplicantId", 1401866602L + TOMORROW));
        Appointment appointment1 = appointments.get(0);
        appointment1 = appointment;
        appointments.remove(0);
        appointments.add(appointment1);
        if (!appointment1.equals(appointments.get(0))) {
            throw new RuntimeException();
        }
    }
}

