package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Appointment;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao-test-context.xml"})
public class AppointmentDAOTests extends BaseDAOTest {

    @Autowired
    private AppointmentDAO appointmentDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testGetAppointmentByAppointmentID() {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2", "3");
        Appointment expected = new Appointment(userIdList, "1", 100L);
        String appointmentId = (String) sessionFactory.getCurrentSession().save(expected);
        Appointment actual = appointmentDAO.getAppointmentByAppointmentID(appointmentId);
        assertThat(expected, equalTo(actual));
    }

    @Test
    public void testPutAppointment() {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2", "3");
        Appointment expected = new Appointment(userIdList, "1", 100L);
        String appointmentId = appointmentDAO.putAppointment(expected);
        Appointment actual = (Appointment) sessionFactory.getCurrentSession().load(Appointment.class, appointmentId);
        assertThat(expected, equalTo(actual));
    }

    @Test
    public void testGetAppointmentByApplicantId() {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2", "3");
        Appointment expected = new Appointment(userIdList, "1", 100L);
        sessionFactory.getCurrentSession().save(expected);
        List<Appointment> actual = appointmentDAO.getAppointmentByApplicantId("1");
        assertThat(1, equalTo(actual.size()));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testRemoveAppointmentById() {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2", "3");
        Appointment expected = new Appointment(userIdList, "1", 100L);
        Session session = sessionFactory.getCurrentSession();
        String appointmentId = (String) session.save(expected);
        appointmentDAO.removeAppointmentById(appointmentId);
        session.load(Appointment.class, appointmentId);
    }

    @Test
    public void testUpdateAppointment() {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2", "3");
        Appointment expected = new Appointment(userIdList, "1", 100L);
        Session session = sessionFactory.getCurrentSession();
        String appointmentId = (String) session.save(expected);
        expected.setActualStartTime(1000L);
        appointmentDAO.updateAppointment(expected);
        Appointment actual = (Appointment)session.load(Appointment.class, appointmentId);
        assertEquals(expected, actual);
    }
}
