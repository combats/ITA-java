package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.service.MailService;
import com.softserveinc.ita.service.MailServiceBaseTests;
import org.apache.velocity.app.VelocityEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class MailServiceTests extends MailServiceBaseTests {

    public final String attachmentName = "";
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

    private Wiser wiser;


    {
        applicant1 = new Applicant("id1", "Andrey", "Pupkin", "pupkinandrey@gmail.com", Applicant.Status.PASSED);
        applicant2 = new Applicant("id2", "Bogdan", "Ruchkin", "ruchkinbogdan@gmail.com", Applicant.Status.NOT_PASSED);
        applicant3 = new Applicant("id3", "Vadim", "Nowkin", "nowkinvadim@gmail.com", Applicant.Status.SCHEDULED);

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

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private VelocityEngine velocityEngine;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wiser = new Wiser(500);
        wiser.setHostname("localhost");

    }

    @Test
    public void notifyPassedApplicant() throws Exception {
        wiser.start();
        when(httpRequestExecutor.getObjectByID("appointmentId1", Appointment.class)).thenReturn(appointment1);
        when(httpRequestExecutor.getObjectByID(appointment1.getApplicantId(), Applicant.class)).thenReturn(applicant1);
        when(httpRequestExecutor.getObjectByID(appointment1.getGroupId(), Group.class)).thenReturn(appointmentGroup1);
        when(httpRequestExecutor.getObjectByID(appointment1.getOwnerId(), User.class)).thenReturn(responsibleUser1);
        mailService.notifyApplicant("appointmentId1");
        Map<String, Object> letterModel = new HashMap<>();
        letterModel.put(MailService.NAME, applicant1.getName());
        letterModel.put(MailService.SURNAME, applicant1.getSurname());
        letterModel.put(MailService.COURSE, appointmentGroup1.getCourse().getName());
        letterModel.put(MailService.COURSE_ADDRESS, appointmentGroup1.getAddress());
        letterModel.put(MailService.GROUP_START_TIME, MailService.convertTimeToDate(appointmentGroup1.getStartTime()));
        letterModel.put(MailService.HR_NAME, responsibleUser1.getName());
        letterModel.put(MailService.HR_SURNAME, responsibleUser1.getSurname());
        letterModel.put(MailService.HR_PHONE, responsibleUser1.getPhone());
        letterModel.put(MailService.HR_EMAIL, responsibleUser1.getEmail());
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                applicant1.getStatus().getTemplateRef(), "UTF-8", letterModel);
        assertTrue(wiser.getMessages().size() == 1);
        WiserMessage wiserMessage = wiser.getMessages().get(0);
        assertEquals(wiserMessage.getEnvelopeReceiver(),applicant1.getEmail());
        assertEquals(wiserMessage.getEnvelopeSender(),"javasendertest@gmail.com");
        assertEquals(wiserMessage.getMimeMessage().getSubject(), applicant1.getStatus().getSubject());
        Object obj = wiserMessage.getMimeMessage().getContent();
        assertTrue(obj instanceof MimeMultipart);
        MimeMultipart multi = (MimeMultipart) obj;
        BodyPart bp = multi.getBodyPart(0);
        Object innerContent = bp.getContent();
        MimeMultipart innerMulti = (MimeMultipart) innerContent;
        assertEquals(emailText, innerMulti.getBodyPart(0).getContent());
        wiser.stop();
    }

    @Test
    public void notifyNotPassedApplicant() throws Exception {
        wiser.start();
        when(httpRequestExecutor.getObjectByID("appointmentId2", Appointment.class)).thenReturn(appointment2);
        when(httpRequestExecutor.getObjectByID(appointment2.getApplicantId(), Applicant.class)).thenReturn(applicant2);
        when(httpRequestExecutor.getObjectByID(appointment2.getGroupId(), Group.class)).thenReturn(appointmentGroup23);
        when(httpRequestExecutor.getObjectByID(appointment2.getOwnerId(), User.class)).thenReturn(responsibleUser23);
        mailService.notifyApplicant("appointmentId2");
        Map<String, Object> letterModel = new HashMap<>();
        letterModel.put(MailService.NAME, applicant2.getName());
        letterModel.put(MailService.SURNAME, applicant2.getSurname());
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                applicant2.getStatus().getTemplateRef(), "UTF-8", letterModel);
        assertTrue(wiser.getMessages().size() == 1);
        WiserMessage wiserMessage = wiser.getMessages().get(0);
        assertEquals(wiserMessage.getEnvelopeReceiver(),applicant2.getEmail());
        assertEquals(wiserMessage.getEnvelopeSender(),"javasendertest@gmail.com");
        assertEquals(wiserMessage.getMimeMessage().getSubject(), applicant2.getStatus().getSubject());
        Object obj = wiserMessage.getMimeMessage().getContent();
        assertTrue(obj instanceof MimeMultipart);
        MimeMultipart multi = (MimeMultipart) obj;
        BodyPart bp = multi.getBodyPart(0);
        Object innerContent = bp.getContent();
        MimeMultipart innerMulti = (MimeMultipart) innerContent;
        assertEquals(emailText, innerMulti.getBodyPart(0).getContent());
        wiser.stop();
    }

    @Test
    public void notifySchedulingApplicant() throws Exception {
        wiser.start();
        when(httpRequestExecutor.getObjectByID("appointmentId3", Appointment.class)).thenReturn(appointment3);
        when(httpRequestExecutor.getObjectByID(appointment3.getApplicantId(), Applicant.class)).thenReturn(applicant3);
        when(httpRequestExecutor.getObjectByID(appointment3.getGroupId(), Group.class)).thenReturn(appointmentGroup23);
        when(httpRequestExecutor.getObjectByID(appointment3.getOwnerId(), User.class)).thenReturn(responsibleUser23);
        mailService.notifyApplicant("appointmentId3");
        Map<String, Object> letterModel = new HashMap<>();
        letterModel.put(MailService.NAME, applicant3.getName());
        letterModel.put(MailService.SURNAME, applicant3.getSurname());
        letterModel.put(MailService.TIME, MailService.convertTimeToDate(appointment3.getStartTime()));
        letterModel.put(MailService.HR_NAME, responsibleUser23.getName());
        letterModel.put(MailService.HR_SURNAME, responsibleUser23.getSurname());
        letterModel.put(MailService.HR_PHONE, responsibleUser23.getPhone());
        letterModel.put(MailService.HR_EMAIL, responsibleUser23.getEmail());
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                applicant3.getStatus().getTemplateRef(), "UTF-8", letterModel);
        assertTrue(wiser.getMessages().size() == 1);
        WiserMessage wiserMessage = wiser.getMessages().get(0);
        assertEquals(wiserMessage.getEnvelopeReceiver(),applicant3.getEmail());
        assertEquals(wiserMessage.getEnvelopeSender(),"javasendertest@gmail.com");
        assertEquals(wiserMessage.getMimeMessage().getSubject(), applicant3.getStatus().getSubject());
        Object obj = wiserMessage.getMimeMessage().getContent();
        assertTrue(obj instanceof MimeMultipart);
        MimeMultipart multi = (MimeMultipart) obj;
        BodyPart bp = multi.getBodyPart(0);
        Object innerContent = bp.getContent();
        MimeMultipart innerMulti = (MimeMultipart) innerContent;
        assertEquals(emailText, innerMulti.getBodyPart(0).getContent());
        wiser.stop();
    }

    @After
    public void doAfter(){

    }


}
