package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.service.mocks.AppointmentServiceMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

//    @Autowired
    private AppointmentDAO appointmentDAO;

    @Override
    public String putAppointment(Appointment appointment) {
        return appointmentDAO.putAppointment(appointment);
    }

    @Override
    public List<Appointment> getAppointmentByApplicantId(String applicantId) {
        return appointmentDAO.getAppointmentByApplicantId(applicantId);
    }

    @Override
    public void removeAppointmentById(String appointmentId) {
        appointmentDAO.removeAppointmentById(appointmentId);
    }

    @Override
    public Appointment getAppointmentByAppointmentId(String appointmentId) {
        return appointmentDAO.getAppointmentByAppointmentId(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsByDate(long date) {
        return null;
        //TODO: under construction
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentDAO.updateAppointment(appointment);
    }

    @Override
    public String getAppointmentIdByGroupIdAndApplicantId(String groupId, String applicantId) {
        return appointmentDAO.getAppointmentIdByGroupIdAndApplicantId(groupId,applicantId);
    }
}
