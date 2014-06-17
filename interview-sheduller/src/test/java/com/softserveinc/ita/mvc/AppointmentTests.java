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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
