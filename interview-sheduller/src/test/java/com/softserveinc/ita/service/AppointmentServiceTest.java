package com.softserveinc.ita.service;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
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
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        List<String> applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "1", "2");
        expectedOne = new Appointment(userIdList, applicantIdList, 1401951895035L);
        expectedTwo = new Appointment(userIdList, applicantIdList, 1401952037427L);
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentEqualExpectedOne() {
        Appointment actual = service.getAppointmentByApplicantId("1");
        assertEquals(expectedOne, actual);
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentNonEqualExpectedOne() {
        Appointment actual = service.getAppointmentByApplicantId("2");
        assertNotSame(expectedOne, actual);
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentEqualExpectedTwo() {
        Appointment actual = service.getAppointmentByApplicantId("4");
        assertEquals(expectedTwo, actual);
    }

    @Test(expected = AppointmentNotFoundException.class)
    public void testRemoveAppointmentByWrongIdAndExpectException() throws AppointmentNotFoundException {
        service.removeAppointmentById("2");
    }

    @Test
    public void testRemoveAppointmentByIdAndExpectOk() throws AppointmentNotFoundException {
        service.removeAppointmentById("1");
    }

}
