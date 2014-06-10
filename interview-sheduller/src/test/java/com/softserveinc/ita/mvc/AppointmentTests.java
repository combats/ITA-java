package com.softserveinc.ita.mvc;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class AppointmentTests extends BaseMVCTest {
	public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private MockMvc mockMvc;

	@Autowired
	private JsonUtil jsonUtil;

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	protected WebApplicationContext wac;

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
                .andExpect(content().string("3"))
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
    public void testEditAppointment() throws Exception {
        List<String> applicants = new ArrayList<>();
        applicants.add("someApplicant");
        List<String> users = new ArrayList<>();
        users.add("someUser");

        Appointment appointment = new Appointment(users, applicants, 1501812387);
        String appointmentJson = jsonUtil.toJson(appointment);

        mockMvc.perform(
                put("/appointments/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson)
        )
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(appointmentJson))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testEditAppointmentAndGetSameAppointmentAsAnswer() throws Exception {
        List<String> applicants = new ArrayList<>();
        applicants.add("testApplicantId");
        List<String> users = new ArrayList<>();
        users.add("testUserId");

        Appointment appointment = new Appointment(users, applicants, 1401866602 + TOMORROW);
        String AppointmentBeforeEdit = jsonUtil.toJson(appointment);

        MvcResult AppointmentAfterEdit = mockMvc.perform(
                put("/appointments/edit/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(AppointmentBeforeEdit)
        )
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isAccepted())
                .andReturn();

         assertTrue("Appointments are not equal", AppointmentAfterEdit.getResponse().getContentAsString().equals(AppointmentBeforeEdit));
    }

}

