package com.softserveinc.ita.mvc;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.utils.JsonUtil;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class AppointmentTests extends BaseMVCTest {
    private static final int TOMORROW = 24 * 60 * 60 * 1000;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private AppointmentDAO appointmentDAO;
    @Autowired
    private JsonUtil jsonUtil;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testPostNewAppointmentAndExpectIsOk() throws Exception {

        List<String> applicants = new ArrayList<>();
        applicants.add("My test Appointment");
        List<String> users = new ArrayList<>();
        users.add("testUserId");

        Appointment appointment = new Appointment(users, applicants, 555);
        String appointmentJson = jsonUtil.toJson(appointment);

        mockMvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson)
        )
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isAccepted());

    }

    @Test
    public void testPostNewAppointmentAndGetAppointmentID() throws Exception {

        List<String> applicants = new ArrayList<>();
        applicants.add("My test Appointment");
        List<String> users = new ArrayList<>();
        users.add("testUserId");

        Appointment appointment = new Appointment(users, applicants, 555);
        String appointmentJson = jsonUtil.toJson(appointment);

        mockMvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson)
        )
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("4"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testGetAppointmentByIDAndExpectIsOk() throws Exception {

        List<String> applicants2 = new ArrayList<>();
        applicants2.add("My test Appointment");
        List<String> users2 = new ArrayList<>();
        users2.add("testUserId");

        Appointment appointment2 = new Appointment(users2, applicants2, 555);
        String appointmentJson2 = jsonUtil.toJson(appointment2);

        MvcResult TestAppointmentID = mockMvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson2)
        )
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isAccepted())
                .andReturn();

        String AppointmentID = TestAppointmentID.getResponse().getContentAsString();

        mockMvc.perform(
                get("/appointments/" + AppointmentID)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(appointmentJson2))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAppointmentByIDAndExpectNotAppropriateAppointment() throws Exception {
        List<String> applicants = new ArrayList<>();
        applicants.add("testApplicantId");
        List<String> users = new ArrayList<>();
        users.add("testUserId");

        Appointment appointment = new Appointment(users, applicants, 1401866602 + TOMORROW);
        String appointmentJson = jsonUtil.toJson(appointment);

        MvcResult objectTest = mockMvc.perform(
                get("/appointments/2/")
        )
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertFalse("Appointment 2 not appropriate for this request", objectTest.toString().equals(appointmentJson));
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectIsOkWithFirstAppointmentFromList() throws Exception {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        List<String> applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "1", "2");
        Appointment appointment = new Appointment(userIdList, applicantIdList, 1401951895035L);
        String appointmentJson = jsonUtil.toJson(appointment);

        ResultActions expect = mockMvc.perform(
                get("/appointments/applicants/1")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(appointmentJson));
    }

    @Test
    public void testGetAppointmentByApplicantIdAndExpectIsOkWithJsonMediaType() throws Exception {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        List<String> applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "1", "2");
        Appointment appointment = new Appointment(userIdList, applicantIdList, 1401952037427L);

        ResultActions expect = mockMvc.perform(
                get("/appointments/applicants/2")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAppointmentsByDateAndExpectValidListObjects() throws Exception {

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


        mockMvc.perform(get("/appointments/date/" + currentTime))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonUtil.toJson(expectedAppointmentsList)));

    }

    @Test
    public void testGetAppointmentsByDayAndExpectEmptyListObjects() throws Exception {

        DateTime futureTime = DateTime.now().plusYears(5);

        LinkedList<Appointment> expectedAppointmentsList = new LinkedList<>();

        mockMvc.perform(get("/appointments/date/" + futureTime.getMillis()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonUtil.toJson(expectedAppointmentsList)));

    }


    @Test
    public void testGetAppointmentsByDateAndExpectStatusCodeBadRequest() throws Exception {

        mockMvc.perform(get("/appointments/date/" + "nonexistent_URL"))
                .andExpect(status().isBadRequest());

    }


}
