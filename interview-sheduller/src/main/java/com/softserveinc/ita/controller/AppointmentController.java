package com.softserveinc.ita.controller;

import javax.validation.Valid;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.AppointmentNotFoundException;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.utils.JsonUtil;
import com.softserveinc.ita.validators.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    @Qualifier("validator")
    private Validator appointmentValidator;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private JsonUtil jsonUtil;
    @Autowired
    private AppointmentDAO appointmentDAO;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(appointmentValidator);
    }
    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Appointment getAppointmentByApplicantId(@PathVariable String applicantId) {
        return appointmentService.getAppointmentByApplicantId(applicantId);
    }

    @RequestMapping(value = "/{appointmentId}", method = RequestMethod.DELETE)
    public void removeAppointmentById(@PathVariable String appointmentId) throws AppointmentNotFoundException {
        appointmentService.removeAppointmentById(appointmentId);
    }
 	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public @ResponseBody String addNewAppointment(@RequestBody @Valid Appointment appointment) {

          return appointmentDAO.putAppointment(appointment);
       }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Appointment getAppointmentByAppointmentID(@PathVariable("id") int id) {

       return appointmentDAO.getAppointmentByAppointmentID(id);
    }
}
