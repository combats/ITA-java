package com.softserveinc.ita.mvc;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.exceptions.DateException;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.junit.Assert.*;

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
	public void testPostNewAppointmentAndExpectIsAccepted() throws Exception {
		List<String> applicants = new ArrayList<>();
		applicants.add("testApplicantId");
		List<String> users = new ArrayList<>();
		users.add("testUserId");

		Appointment appointment = new Appointment(users, applicants, System.currentTimeMillis() + TOMORROW);
		String appointmentJson = jsonUtil.toJson(appointment);

		mockMvc.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON).content(appointmentJson))
                .andExpect(status().isAccepted());
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

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void StartTimeAlreadyPassedException() throws DateException {
        thrown.expect(DateException.class);
        thrown.expectMessage("Start time has already passed");

        List<String> applicants = new ArrayList();
        applicants.add("testApplicantId");
        List<String> users = new ArrayList();
        users.add("testUserId");
        Appointment app = new Appointment(users, applicants,Long.valueOf(11), Long.valueOf(2));
        app.dateValidation(app.getStartTime(), app.getDurationTime());
    }

    @Test
    public void WrongDurationTimeException() throws DateException {
        thrown.expect(DateException.class);
        thrown.expectMessage("Wrong duration time");

        List<String> applicants = new ArrayList();
        applicants.add("testApplicantId");
        List<String> users = new ArrayList();
        users.add("testUserId");
        Appointment app = new Appointment(users, applicants,Long.valueOf(11), Long.valueOf(-2));
        app.dateValidation(app.getStartTime(), app.getDurationTime());
    }

    @Test
    public void TooLongDurationTimeException() throws DateException {
        thrown.expect(DateException.class);
        thrown.expectMessage("Too long duration time");

        List<String> applicants = new ArrayList();
        applicants.add("testApplicantId");
        List<String> users = new ArrayList();
        users.add("testUserId");
        long currentDate=new Date().getTime() + 1;
        long bigDurationTime = 1000 * 60 * 60 * 12 + 1;
        Appointment app = new Appointment(users, applicants, currentDate, bigDurationTime);
        app.dateValidation(app.getStartTime(), app.getDurationTime());
    }
    @Test
    public void testGetAppointmentByApplicantIdAndExpectIsOkWithFirstAppointmentFromList() throws Exception {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        List<String> applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "1", "2");
        Appointment appointment = new Appointment(userIdList, applicantIdList, 1401951895035L);
        String appointmentJson = jsonUtil.toJson(appointment);

        mockMvc.perform(
                get("/appointments/applicants/1")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(appointmentJson));
    }

    @Test
    public void testPostAppointmentAndExpectErrorDueToNonexistentUserAndApplicant() throws Exception {
        List<String> applicants = new ArrayList<>();
        applicants.add("some_unexisting_applicant_id");
        List<String> users = new ArrayList<>();
        users.add("some_unexisting_user_id");

        Appointment appointment = new Appointment(users, applicants, System.currentTimeMillis() + TOMORROW);
        String appointmentJson = jsonUtil.toJson(appointment);

        mockMvc.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON).content(appointmentJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetAppointmentByApplicantIdAndExpectIsOkWithJsonMediaType() throws Exception {
        mockMvc.perform(
                get("/appointments/applicants/2")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRemoveAppointmentByIdAndExpectIsOk() throws Exception {
        String appointmentId = "1";
        mockMvc.perform(
                delete("/appointments/{appointmentId}", appointmentId)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoveAppointmentByNonExistIdAndExpectInternalServerError() throws Exception {
        String appointmentId = "2";
        mockMvc.perform(
                delete("/appointments/{appointmentId}", appointmentId)
        )
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testPostNewAppointmentAndExpectIsOk() throws Exception {

        List<String> applicants = new ArrayList<>();
        applicants.add("testApplicantId");
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
        applicants.add("testApplicantId");
        List<String> users = new ArrayList<>();
        users.add("testUserId");

        Appointment appointment = new Appointment(users, applicants, 555);

        String appointmentJson = jsonUtil.toJson(appointment);
        String exptectedIdJson = jsonUtil.toJson(4);

        MvcResult ExpectingObject = mockMvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson)
        )
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isAccepted())
                .andReturn();

        String ExpectID = ExpectingObject.getResponse().getContentAsString();

        assertEquals("Return Appointment ID in JSON in response to Post appointment request", exptectedIdJson, ExpectID);
    }
}