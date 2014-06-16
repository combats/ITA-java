package com.softserveinc.ita.controller;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

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

    public @ResponseBody String addNewAppointment(@RequestBody Appointment appointment) {

          return appointmentDAO.putAppointment(appointment);
       }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Appointment getAppointmentByAppointmentID(@PathVariable("id") int id) {

       return appointmentDAO.getAppointmentByAppointmentID(id);
    }
   
    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Appointment getAppointmentByApplicantId(@PathVariable String applicantId) {
        return appointmentService.getAppointmentByApplicantId(applicantId);
    }
}
