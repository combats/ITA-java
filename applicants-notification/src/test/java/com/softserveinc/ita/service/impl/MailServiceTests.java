package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.service.MailServiceBaseTests;
import com.softserveinc.ita.utils.JsonUtil;
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
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class MailServiceTests extends MailServiceBaseTests {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    public static final String SENDER_EMAIL = "javasendertest@gmail.com";
    private Applicant applicant1;
    private Applicant applicant2;
    private Applicant applicant3;
    private Appointment appointment1;
    private Appointment appointment2;
    private Appointment appointment3;
    private Group group1;
    private Group group2;
    private User responsibleHr1;
    private User responsibleHr2;
    private Wiser wiser;

    {
        applicant1 = new Applicant("id1", "Andrey", "Pupkin", "pupkinandrey@gmail.com");
        applicant2 = new Applicant("id2", "Bogdan", "Ruchkin", "ruchkinbogdan@gmail.com");
        applicant3 = new Applicant("id3", "Vadim", "Nowkin", "nowkinvadim@gmail.com");
        List<String> users = new ArrayList<>();
        users.add("testUserId");
        group1 = new Group("id1", new Course("Java Script", "pen-net.png"), "street1", 111111111);
        Map<String, ApplicantBenchmark> groupApplicants1 = new HashMap<>();
        ApplicantBenchmark applicantBenchmarkPassed = new ApplicantBenchmark();
        applicantBenchmarkPassed.setStatus(Applicant.Status.PASSED);
        ApplicantBenchmark applicantBenchmarkNotPassed = new ApplicantBenchmark();
        applicantBenchmarkNotPassed.setStatus(Applicant.Status.NOT_PASSED);
        groupApplicants1.put(applicant1.getId(), applicantBenchmarkPassed);
        groupApplicants1.put(applicant2.getId(), applicantBenchmarkNotPassed);
        group1.setApplicants(groupApplicants1);
        responsibleHr1 = new User("Svetlana", "Ivanova");
        responsibleHr1.setPhone("777-777-777");
        responsibleHr1.setEmail("hr1@gmail.com");
        group2 = new Group("id2", new Course("C#", "pen-net.png"), "street23", 232322323);
        Map<String, ApplicantBenchmark> groupApplicants2 = new HashMap<>();
        ApplicantBenchmark applicantBenchmarkNot_Scheduled = new ApplicantBenchmark();
        applicantBenchmarkNot_Scheduled.setStatus(Applicant.Status.NOT_SCHEDULED);
        ApplicantBenchmark applicantBenchmarkScheduled = new ApplicantBenchmark();
        applicantBenchmarkScheduled.setStatus(Applicant.Status.SCHEDULED);
        ApplicantBenchmark applicantBenchmarkEmployed = new ApplicantBenchmark();
        applicantBenchmarkEmployed.setStatus(Applicant.Status.EMPLOYED);
        groupApplicants2.put(applicant2.getId(), applicantBenchmarkNot_Scheduled);
        groupApplicants2.put(applicant3.getId(), applicantBenchmarkScheduled);
        groupApplicants2.put(applicant1.getId(), applicantBenchmarkEmployed);
        group2.setApplicants(groupApplicants2);
        responsibleHr2 = new User("Bogdan", "Bogdanov");
        responsibleHr2.setPhone("555-555-555");
        responsibleHr2.setEmail("hr2@gmail.com");
        appointment1 = new Appointment(users, applicant1.getId(), 1401866602L + TOMORROW, group1.getGroupID());
        appointment1.setAppointmentId("appointmentId1");
        appointment2 = new Appointment(users, applicant2.getId(), 1401866603L + TOMORROW, group1.getGroupID());
        appointment2.setAppointmentId("appointmentId2");
        appointment3 = new Appointment(users, applicant3.getId(), 1401866604L + TOMORROW, group2.getGroupID());
        appointment3.setAppointmentId("appointmentId4");
        
    }

    @Autowired
    @InjectMocks
    private MailServiceImpl mailServiceImpl;

    @Autowired
    @Mock
    private HttpRequestExecutorRestImpl httpRequestExecutor;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JsonUtil jsonUtil;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wiser = new Wiser(500);
        wiser.setHostname("localhost");
        wiser.start();
    }

    @After
    public void tearDown(){
        wiser.stop();
    }

    @Test
    public void notifyPassedApplicant() throws Exception {
        when(httpRequestExecutor.getObjectByID(applicant1.getId(), Applicant.class)).thenReturn(applicant1);
        when(httpRequestExecutor.getObjectByID(group1.getGroupID(), Group.class)).thenReturn(group1);
        when(httpRequestExecutor.getObjectByID(responsibleHr1.getId(), User.class)).thenReturn(responsibleHr1);
        NotificationJSONInfo notificationInfo = new NotificationJSONInfo(applicant1.getId(), group1.getGroupID(),
                responsibleHr1.getId());
        String notificationJsonInfo = jsonUtil.toJson(notificationInfo);
        mailServiceImpl.notifyApplicant(notificationJsonInfo);
        Map<String, Object> letterModel = new HashMap<>();
        letterModel.put(MailServiceImpl.NAME, applicant1.getName());
        letterModel.put(MailServiceImpl.SURNAME, applicant1.getSurname());
        letterModel.put(MailServiceImpl.COURSE, group1.getCourse().getName());
        letterModel.put(MailServiceImpl.COURSE_ADDRESS, group1.getAddress());
        letterModel.put(MailServiceImpl.GROUP_START_TIME, MailServiceImpl.convertTimeToDate(group1.getStartTime()));
        letterModel.put(MailServiceImpl.HR_NAME, responsibleHr1.getName());
        letterModel.put(MailServiceImpl.HR_SURNAME, responsibleHr1.getSurname());
        letterModel.put(MailServiceImpl.HR_PHONE, responsibleHr1.getPhone());
        letterModel.put(MailServiceImpl.HR_EMAIL, responsibleHr1.getEmail());
        checkReceivedLetter(letterModel, applicant1, group1);
    }

    @Test
    public void notifyNotPassedApplicant() throws Exception {
        when(httpRequestExecutor.getObjectByID(applicant2.getId(), Applicant.class)).thenReturn(applicant2);
        when(httpRequestExecutor.getObjectByID(group1.getGroupID(), Group.class)).thenReturn(group1);
        when(httpRequestExecutor.getObjectByID(responsibleHr1.getId(), User.class)).thenReturn(responsibleHr1);
        NotificationJSONInfo notificationInfo = new NotificationJSONInfo(applicant2.getId(), group1.getGroupID(),
                responsibleHr1.getId());
        String notificationJsonInfo = jsonUtil.toJson(notificationInfo);
        mailServiceImpl.notifyApplicant(notificationJsonInfo);
        Map<String, Object> letterModel = new HashMap<>();
        letterModel.put(MailServiceImpl.NAME, applicant2.getName());
        letterModel.put(MailServiceImpl.SURNAME, applicant2.getSurname());
        checkReceivedLetter(letterModel, applicant2, group1);
    }


    @Test
    public void notifyScheduledApplicant() throws Exception {
        when(httpRequestExecutor.getObjectByID(applicant3.getId(), Applicant.class)).thenReturn(applicant3);
        when(httpRequestExecutor.getObjectByID(group2.getGroupID(), Group.class)).thenReturn(group2);
        HashMap<Class, String> groupAndApplicantIDs = new HashMap<>();
        groupAndApplicantIDs.put(Applicant.class, applicant3.getId());
        groupAndApplicantIDs.put(Group.class, group2.getGroupID());
        ArrayList<String> appointmetIdList = new ArrayList<>();
        appointmetIdList.add(appointment3.getAppointmentId());
        when(httpRequestExecutor.getListObjectsIdByPrams(Appointment.class,groupAndApplicantIDs))
                .thenReturn(appointmetIdList);
        when(httpRequestExecutor.getObjectByID(appointment3.getAppointmentId(),Appointment.class))
                .thenReturn(appointment3);
        when(httpRequestExecutor.getObjectByID(responsibleHr2.getId(), User.class)).thenReturn(responsibleHr2);
        NotificationJSONInfo notificationInfo = new NotificationJSONInfo(applicant3.getId(), group2.getGroupID(),
                responsibleHr2.getId());
        String notificationJsonInfo = jsonUtil.toJson(notificationInfo);
        mailServiceImpl.notifyApplicant(notificationJsonInfo);
        Map<String, Object> letterModel = new HashMap<>();
        letterModel.put(MailServiceImpl.NAME, applicant3.getName());
        letterModel.put(MailServiceImpl.SURNAME, applicant3.getSurname());
        letterModel.put(MailServiceImpl.COURSE_ADDRESS, group2.getAddress());
        letterModel.put(MailServiceImpl.TIME, MailServiceImpl.convertTimeToDate(appointment3.getStartTime()));
        letterModel.put(MailServiceImpl.HR_NAME, responsibleHr2.getName());
        letterModel.put(MailServiceImpl.HR_SURNAME, responsibleHr2.getSurname());
        letterModel.put(MailServiceImpl.HR_PHONE, responsibleHr2.getPhone());
        letterModel.put(MailServiceImpl.HR_EMAIL, responsibleHr2.getEmail());
        checkReceivedLetter(letterModel, applicant3, group2);
    }

    @Test
    public void notifyNotScheduledApplicant() throws Exception {
        when(httpRequestExecutor.getObjectByID(applicant2.getId(), Applicant.class)).thenReturn(applicant2);
        when(httpRequestExecutor.getObjectByID(group2.getGroupID(), Group.class)).thenReturn(group2);
        when(httpRequestExecutor.getObjectByID(responsibleHr2.getId(), User.class)).thenReturn(responsibleHr2);
        NotificationJSONInfo notificationInfo = new NotificationJSONInfo(applicant2.getId(), group2.getGroupID(),
                responsibleHr2.getId());
        String notificationJsonInfo = jsonUtil.toJson(notificationInfo);
        mailServiceImpl.notifyApplicant(notificationJsonInfo);
        Map<String, Object> letterModel = new HashMap<>();
        letterModel.put(MailServiceImpl.NAME, applicant2.getName());
        letterModel.put(MailServiceImpl.SURNAME, applicant2.getSurname());
        letterModel.put(MailServiceImpl.COURSE, group2.getCourse().getName());
        letterModel.put(MailServiceImpl.GROUP_NAME, group2.getGroupName());
        letterModel.put(MailServiceImpl.HR_NAME, responsibleHr2.getName());
        letterModel.put(MailServiceImpl.HR_SURNAME, responsibleHr2.getSurname());
        letterModel.put(MailServiceImpl.HR_PHONE, responsibleHr2.getPhone());
        letterModel.put(MailServiceImpl.HR_EMAIL, responsibleHr2.getEmail());
        checkReceivedLetter(letterModel, applicant2, group2);
    }

    @Test
    public void notifyEmployedApplicant() throws Exception {
        when(httpRequestExecutor.getObjectByID(applicant1.getId(), Applicant.class)).thenReturn(applicant1);
        when(httpRequestExecutor.getObjectByID(group2.getGroupID(), Group.class)).thenReturn(group2);
        when(httpRequestExecutor.getObjectByID(responsibleHr2.getId(), User.class)).thenReturn(responsibleHr2);
        NotificationJSONInfo notificationInfo = new NotificationJSONInfo(applicant1.getId(), group2.getGroupID(),
                responsibleHr2.getId());
        String notificationJsonInfo = jsonUtil.toJson(notificationInfo);
        mailServiceImpl.notifyApplicant(notificationJsonInfo);
        Map<String, Object> letterModel = new HashMap<>();
        letterModel.put(MailServiceImpl.NAME, applicant1.getName());
        letterModel.put(MailServiceImpl.SURNAME, applicant1.getSurname());
        letterModel.put(MailServiceImpl.HR_NAME, responsibleHr2.getName());
        letterModel.put(MailServiceImpl.HR_SURNAME, responsibleHr2.getSurname());
        letterModel.put(MailServiceImpl.HR_PHONE, responsibleHr2.getPhone());
        letterModel.put(MailServiceImpl.HR_EMAIL, responsibleHr2.getEmail());
        letterModel.put(MailServiceImpl.APPLICANT_EMAIL, applicant1.getEmail());
        checkReceivedLetter(letterModel, applicant1, group2);
    }

    private void checkReceivedLetter(Map<String, Object> letterModel, Applicant applicant, Group group)
            throws MessagingException, IOException {
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                group.getApplicants().get(applicant.getId()).getStatus().getTemplateRef(), MailServiceImpl.ENCODING, letterModel);
        assertTrue(wiser.getMessages().size() == 1);
        WiserMessage wiserMessage = wiser.getMessages().get(0);
        assertEquals(wiserMessage.getEnvelopeReceiver(), applicant.getEmail());
        assertEquals(wiserMessage.getEnvelopeSender(), SENDER_EMAIL);
        assertEquals(wiserMessage.getMimeMessage().getSubject(), group.getApplicants().get(applicant.getId()).getStatus().
                getSubject());
        Object obj = wiserMessage.getMimeMessage().getContent();
        assertTrue(obj instanceof MimeMultipart);
        MimeMultipart multi = (MimeMultipart) obj;
        BodyPart bp = multi.getBodyPart(0);
        Object innerContent = bp.getContent();
        MimeMultipart innerMulti = (MimeMultipart) innerContent;
        assertEquals(emailText, innerMulti.getBodyPart(0).getContent());
    }
}