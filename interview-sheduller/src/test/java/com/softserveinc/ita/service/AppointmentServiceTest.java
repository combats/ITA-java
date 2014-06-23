package com.softserveinc.ita.service;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Appointment;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class AppointmentServiceTest extends BaseMVCTest {

    @Autowired
    AppointmentService service;
    private static Appointment expectedOne = null;
    private static Appointment expectedTwo = null;

    @BeforeClass
    public static void setUpOnce() {
        int TOMORROW = 24 * 60 * 60 * 1000;
        String appointmentId = "testAppointmentId";

        List<String> users = new ArrayList<>();
        users.add("testUserId");

        expectedOne = new Appointment(users, "testApplicantId", 1401866602L + TOMORROW);
        expectedOne.setAppointmentId(appointmentId);

        expectedTwo = new Appointment(users, "testApplicantId", 1401866604L + TOMORROW);
        expectedTwo.setAppointmentId(appointmentId);
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentEqualExpectedOne() {
        Appointment actual = service.getAppointmentByApplicantId("testApplicantId");
        assertEquals(expectedOne, actual);
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentNonEqualExpectedOne() {
        Appointment actual = service.getAppointmentByApplicantId("testApplicantId");
        assertNotSame(expectedOne, actual);
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentEqualExpectedTwo() {
        Appointment actual = service.getAppointmentByApplicantId("testApplicantId2");
        assertEquals(expectedTwo, actual);
    }
}
