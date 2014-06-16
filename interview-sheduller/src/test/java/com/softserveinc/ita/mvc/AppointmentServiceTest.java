package com.softserveinc.ita.mvc;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.service.AppointmentServiceMock;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class AppointmentServiceTest extends BaseMVCTest {

    AppointmentService service = new AppointmentServiceMock();

    private static Appointment appointment_one;
    private static Appointment appointment_two;
    private static Appointment appointmentForEdit;

    @BeforeClass
    public static void setUpOnce() {
        final int TOMORROW = 24 * 60 * 60 * 1000;
        Date date = new Date();
        List<String> userIdList1 = new ArrayList<>();
        List<String> applicantIdList1 = new ArrayList<>();
        Collections.addAll(userIdList1, "First_user", "Second_user", "Third_user");
        Collections.addAll(applicantIdList1, "First_applicant");

        List<String> userIdList2 = new ArrayList<>();
        List<String> applicantIdList2 = new ArrayList<>();
        Collections.addAll(userIdList1, "First_user", "Second_user", "Third_user");
        Collections.addAll(applicantIdList1, "Second_applicant");

        appointment_one = new Appointment(userIdList1, applicantIdList1, date.getTime());
        appointment_two = new Appointment(userIdList2, applicantIdList2, date.getTime()+ TOMORROW);
        appointmentForEdit = new Appointment(userIdList2, applicantIdList2, date.getTime()+ TOMORROW);

        LinkedList<Appointment> appointmentsList = new LinkedList<>();
        appointmentsList.add(appointment_one);
        appointmentsList.add(appointment_two);
    }

    @Test
    public void editFirstAppointmentByIdAndExpectOk() throws AppointmentNotFoundException {
        Appointment ourTestAppointment = service.editAppointmentById(1, appointmentForEdit);
        assertEquals(appointmentForEdit, ourTestAppointment);
    }

    @Test
    public void editSecondAppointmentByIdAndExpectOk() throws AppointmentNotFoundException{
        Appointment ourTestAppointment = service.editAppointmentById(2, appointmentForEdit);
        assertEquals(appointmentForEdit, ourTestAppointment);
    }

    @Test(expected = AppointmentNotFoundException.class)
    public void editThirdAppointmentByIdAndExpectException() throws AppointmentNotFoundException {
        service.editAppointmentById(3, appointmentForEdit);
    }

}
