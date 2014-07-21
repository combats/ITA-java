package com.softserveinc.ita.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.http.HttpSession;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.utils.JsonUtil;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class SecurityTests extends BaseSecurityTest {

    public static final int TOMORROW = 24 * 60 * 60 * 1000;
    private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private JsonUtil jsonUtil;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }

    @Test
    public void requiresAuthentication() throws Exception {
        mockMvc.perform(
                get("appointments/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void accessGranted() throws Exception {

        String applicantId = "testApplicantId";
        String appointmentId = "testAppointmentId";

        List<String> users = new ArrayList<>();
        users.add("testUserId");
        Appointment appointment = new Appointment(users, applicantId, 1401866602L + TOMORROW);
        appointment.setAppointmentId(appointmentId);
        String appointmentJson = jsonUtil.toJson(appointment);

        this.mockMvc.perform(get("/appointments/applicants/testApplicantId").with(SecurityRequestPostProcessors.userDetailsService("admin")))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(appointmentJson));
    }

    @Test
    public void accessDenied() throws Exception {
        this.mockMvc.perform(get("/").with(SecurityRequestPostProcessors.user("user").roles("DENIED")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void userAuthenticates() throws Exception {
        final String username = "admin";
        mockMvc.perform(post("/j_spring_security_check").param("j_username", username).param("j_password", "admin"))
                .andExpect(redirectedUrl("/"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                        Assert.assertEquals(securityContext.getAuthentication().getName(), username);
                    }
                });
    }

    @Test
    public void userAuthenticateFails() throws Exception {
        final String username = "user";
        mockMvc.perform(post("/j_spring_security_check").param("j_username", username).param("j_password", "invalid"))
                .andExpect(redirectedUrl("/spring_security_login?login_error"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                        Assert.assertNull(securityContext);
                    }
                });
    }

}