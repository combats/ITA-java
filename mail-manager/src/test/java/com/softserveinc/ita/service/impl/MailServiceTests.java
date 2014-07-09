package com.softserveinc.ita.service.impl;

import com.dumbster.smtp.SimpleSmtpServer;
import com.icegreen.greenmail.util.GreenMail;
import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.service.MailService;
import com.softserveinc.ita.service.MailServiceBaseTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class MailServiceTests extends MailServiceBaseTests {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    private Applicant applicant1;
    private Applicant applicant2;
    private Applicant applicant3;

    private Appointment appointment1;
    private Appointment appointment2;
    private Appointment appointment3;

    private Group appointmentGroup1;
    private Group appointmentGroup23;

    private User responsibleUser1;
    private User responsibleUser23;


    private SimpleSmtpServer server;


    {
        applicant1 = new Applicant("id1", "Andrey", "Pupkin", "pupkinandrey@gmail.com", Applicant.Status.PASSED);
        applicant2 = new Applicant("id2", "Bogdan", "Ruchkin", "ruchkinbogdan@gmail.com", Applicant.Status.NOT_PASSED);
        applicant3 = new Applicant("id3", "Vadim", "Nowkin", "nowkinvadim@gmail.com", Applicant.Status.NOT_SCHEDULED);

        List<String> users = new ArrayList<>();
        users.add("testUserId");

        appointmentGroup1 = new Group("id1", new Course("Java Script", "pen-net.png"), "street1", 111111111);
        responsibleUser1 = new User("id1", "Svetlana", "Ivanova", 24, "sveta@gmail.com", "555-11-22");

        appointment1 = new Appointment(users, applicant1.getId(), 1401866602L + TOMORROW, responsibleUser1.getId(),
                appointmentGroup1.getGroupID());
        appointment1.setAppointmentId("appointmentId1");

        appointmentGroup23 = new Group("id2", new Course("C#", "pen-net.png"), "street23", 232322323);

        responsibleUser23 = new User("id2", "Bogdan", "Bogdanov", 25, "bogdanov@gmail.com", "555-11-33");
        List<String> users2 = new ArrayList<>();
        appointment2 = new Appointment(users, applicant2.getId(), 1401866603L + TOMORROW, responsibleUser23.getId(),
                appointmentGroup23.getGroupID());

        appointment2.setAppointmentId("appointmentId2");
        List<String> users3 = new ArrayList<>();
        appointment3 = new Appointment(users, applicant3.getId(), 1401866604L + TOMORROW, responsibleUser23.getId(),
                appointmentGroup23.getGroupID());
        appointment3.setAppointmentId("appointmentId3");
    }

    @Autowired
    @InjectMocks
    private MailService mailService;

    @Autowired
    @Mock
    private HttpRequestExecutorRestImpl httpRequestExecutor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        server = SimpleSmtpServer.start();
    }

    @Test
    public void mailTest() throws Exception {
        GreenMail greenMail = new GreenMail(); //uses test ports by default
        greenMail.start();
        when(httpRequestExecutor.getObjectByID("appointmentId1", Appointment.class)).thenReturn(appointment1);
        when(httpRequestExecutor.getObjectByID(appointment1.getApplicantId(), Applicant.class)).thenReturn(applicant1);
        when(httpRequestExecutor.getObjectByID(appointment1.getGroupId(), Group.class)).thenReturn(appointmentGroup1);
        when(httpRequestExecutor.getObjectByID(appointment1.getOwnerId(), User.class)).thenReturn(responsibleUser1);
        mailService.notifyApplicant("appointmentId1");

        System.out.println("Received");
        System.out.println(greenMail.getReceivedMessages().length);
        assertTrue(greenMail.getReceivedMessages().length == 1);
        assertEquals(httpRequestExecutor.getObjectByID("appointmentId1", Appointment.class), appointment1);
        assertEquals(httpRequestExecutor.getObjectByID(appointment1.getApplicantId(), Applicant.class), applicant1);
        assertEquals(httpRequestExecutor.getObjectByID(appointment1.getGroupId(), Group.class), appointmentGroup1);
        assertEquals(httpRequestExecutor.getObjectByID(appointment1.getOwnerId(), User.class), responsibleUser1);
    }

    @After
    public void doAfter(){
        server.stop();
    }


}
