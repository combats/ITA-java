package com.softserveinc.ita.service;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AppointmentServiceTest extends BaseMVCTest {

    private static long startTime = 1403308782454L;
    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    @Autowired
    AppointmentService service;
    private static Appointment expectedOne = null;
    private static Appointment expectedTwo = null;

    @BeforeClass
    public static void setUpOnce() {

        User user1 = new User("1", "IT Project Manager");
        User user2 = new User("2", "Software Developer");
        User user3 = new User("3", "HR Manager");

        Applicant applicant1 = new Applicant("1", "Gena");
        Applicant applicant2 = new Applicant("2", "Gesha");

        List<String> usersIdList = new ArrayList<String>(); {
            Collections.addAll(usersIdList, user1.getId(), user2.getId(), user3.getId());
        }

        expectedOne = new Appointment(usersIdList, applicant1.getApplicantID(), startTime);
        expectedOne.setAppointmentId("1");

        expectedTwo = new Appointment(usersIdList, applicant2.getApplicantID(), startTime + TOMORROW);
        expectedTwo.setAppointmentId("2");
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentEqualExpectedOne() throws AppointmentNotFoundException {
        Appointment actual = service.getAppointmentByApplicantId("1");
        assertEquals(expectedOne, actual);
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentNonEqualExpectedOne() throws AppointmentNotFoundException {
        Appointment actual = service.getAppointmentByApplicantId("1");
        assertNotSame(expectedOne, actual);
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectAppointmentEqualExpectedTwo() throws AppointmentNotFoundException {
        Appointment actual = service.getAppointmentByApplicantId("2");
        assertEquals(expectedTwo, actual);
    }


    @Test
    public void testGetAppointmentByDateAndExpectAppointmentWithValidDate() {
        final int SAME_VALUE_BY_COMPARE = 1;

        List<Appointment> actualList = service.getAppointmentsByDate(1403308782454L);
        if (actualList.isEmpty()) {
            fail();
        }
        Appointment actual = actualList.get(0);
        DateTime toDay = DateTime.now();
        DateTime actualDate = new DateTime(actual.getStartTime());
        DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();

        assertEquals(dateTimeComparator.compare(toDay, actualDate), SAME_VALUE_BY_COMPARE);

    }
}
