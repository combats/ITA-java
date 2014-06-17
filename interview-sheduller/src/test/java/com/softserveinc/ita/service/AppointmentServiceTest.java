package com.softserveinc.ita.service;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AppointmentServiceTest extends BaseMVCTest {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testGetAppointmentsByDateAndExpectValidListObject() throws Exception {
        long currentTime = System.currentTimeMillis();

        List<String> applicantIdList = new ArrayList<>();
        applicantIdList.add("testApplicantId");
        List<String> userIdList = new ArrayList<>();
        userIdList.add("testUserId");


        Appointment todayFirstAppointment = new Appointment(userIdList, applicantIdList, currentTime);
        Appointment todaySecondAppointment = new Appointment(userIdList, applicantIdList, currentTime);


        appointmentDAO.putAppointment(todayFirstAppointment);
        appointmentDAO.putAppointment(todaySecondAppointment);

        LinkedList<Appointment> expectedAppointmentsList = new LinkedList<>();

        expectedAppointmentsList.add(todayFirstAppointment);
        expectedAppointmentsList.add(todaySecondAppointment);

        List<Appointment> actualAppointmentList = appointmentService.getAppointmentsByDate(currentTime);
        assertEquals(actualAppointmentList, expectedAppointmentsList);
    }
}