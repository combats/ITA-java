package com.softserveinc.ita.security;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import junit.framework.Assert;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

    public class SecurityTests extends BaseSecurityTest {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    Appointment appointment;
    @Autowired
    JsonUtil jsonUtil;

     private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }

    @Test
    public void accessDenied() throws Exception {
        this.mockMvc.perform(
                get("/appointments/1")
        )
                .andExpect(status().isUnauthorized());
    }

     @Test
        public void userAuthenticateAccepted() throws Exception {
         final String username = "admin";

         List<String> applicants = new ArrayList<>();
         applicants.add("testApplicantId");
         List<String> users = new ArrayList<>();
         users.add("testUserId");
         Appointment appointment = new Appointment(users, applicants, 1401866602 + TOMORROW);
         String appointmentJson = jsonUtil.toJson(appointment);

         mockMvc.perform(
                 post("/j_spring_security_check").param("j_username", username).param("j_password", "admin")
         )
                 .andExpect(content().string(appointmentJson))
                 .andExpect(content().contentType("application/json"))
                 .andExpect(status().isOk());
     }
}
