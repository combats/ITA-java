package com.softserveinc.ita.controller;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentDAO appointmentDAO;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public
    @ResponseBody
    String addNewAppointment(@RequestBody Appointment appointment) {

        return appointmentService.putAppointment(appointment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Appointment getAppointmentByAppointmentID(@PathVariable("id") int id) {

        return appointmentService.getAppointmentByAppointmentID(id);
    }

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Appointment getAppointmentByApplicantId(@PathVariable String applicantId) {
        return appointmentService.getAppointmentByApplicantId(applicantId);
    }

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Appointment> getAppointmentsByDate(@PathVariable long date) {

        return appointmentService.getAppointmentsByDate(date);
    }
}
