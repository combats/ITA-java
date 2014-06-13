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

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

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

