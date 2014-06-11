package com.softserveinc.ita.mvc;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.MokDataProvider;
import com.softserveinc.ita.utils.JsonUtil;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private MokDataProvider<Appointment> mokDataProvider;

    private List<String> userIdList;
    private List<String> applicantIdList;

    @Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();


         userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");

       applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "1", "2");

        mokDataProvider = MokDataProvider.getDataProvider();


        DateTime pastTime =  DateTime.now().minusDays(30);


        for ( int dayCount =1; dayCount>60 ; dayCount ++ ){
            mokDataProvider.getDataList()
                    .add(new Appointment(userIdList,
                            applicantIdList,
                            pastTime.plusDays(dayCount).getMillis()));
        }
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
    public void testGetAppointmentByApplicantIdAndExpectIsOkWithJsonMediaType() throws Exception {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        List<String> applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "1", "2");

                 mockMvc.perform(
                get("/appointments/applicants/2")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testGetAppointmentsByDayAndExpectValidListObjects()throws Exception{

        DateTime  currentTime = DateTime.now();

        Appointment tooDayAppointment = new Appointment(userIdList,applicantIdList,currentTime.getMillis());

        mokDataProvider.getDataList().add(tooDayAppointment);

        LinkedList<Appointment> expectedAppointmentsList = new LinkedList<>();

        expectedAppointmentsList.add(tooDayAppointment);

           mockMvc.perform(get("/appointments/date/" + currentTime.getMillis()))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(content().string(jsonUtil.toJson(expectedAppointmentsList)));

    }

    @Test
    public void testGetAppointmentsByDayAndExpectEmptyListObjects()throws Exception{

        DateTime futureTime = DateTime.now().plusYears(5);

        LinkedList<Appointment> expectedAppointmentsList = new LinkedList<>();

        mockMvc.perform(get("/appointments/date/" + futureTime.getMillis()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonUtil.toJson(expectedAppointmentsList)));

    }


    @Test
    public void testGetAppointmentsByDayAndExpectBadReset()throws Exception{

        mockMvc.perform(get("/appointments/date/" + "nonexistentPath" ))
                .andExpect(status().isBadRequest());

    }
}
