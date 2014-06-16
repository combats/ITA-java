package com.softserveinc.ita.mvc;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private JsonUtil jsonUtil;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testPostNewAppointmentAndExpectIsOk() throws Exception {
        List<String> applicants = new ArrayList<>();
        applicants.add("testApplicantId");
        List<String> users = new ArrayList<>();
        users.add("testUserId");

		Appointment appointment = new Appointment(users, applicants, System.currentTimeMillis() + TOMORROW);
		String appointmentJson = jsonUtil.toJson(appointment);

		mockMvc.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON).content(appointmentJson))
                .andExpect(status().isOk());
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
}