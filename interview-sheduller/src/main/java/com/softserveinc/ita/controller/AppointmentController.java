package com.softserveinc.ita.controller;

import javax.validation.Valid;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.utils.JsonUtil;
import com.softserveinc.ita.validators.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(appointmentValidator);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Appointment addNewAppointment(@RequestBody @Valid Appointment appointment) {
        return appointment;
	}

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Appointment getAppointmentByApplicantId(@PathVariable String applicantId) {
        return appointmentService.getAppointmentByApplicantId(applicantId);
    }
}