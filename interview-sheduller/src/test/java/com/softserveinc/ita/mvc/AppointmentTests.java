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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
		applicants.add("testApplicantId");
		List<String> users = new ArrayList<>();
		users.add("testUserId");

		Appointment appointment = new Appointment(users, applicants, System.currentTimeMillis() + TOMORROW);
		String appointmentJson = jsonUtil.toJson(appointment);

		mockMvc.perform(
				post("/appointments")
				.content(appointmentJson)
		)
				.andExpect(status().isOk());
	}

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void StartTimeAlreadyPassedException() throws DateException{
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
    public void WrongDurationTimeException() throws DateException{
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
    public void TooLongDurationTimeException() throws DateException{
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
}
