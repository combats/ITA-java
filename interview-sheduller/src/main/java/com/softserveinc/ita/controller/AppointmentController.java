package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Appointment> getAppointmentByApplicantId(@PathVariable String applicantId) {
        return appointmentService.getAppointmentByApplicantId(applicantId);
    }

    @RequestMapping(value = "/{appointmentId}", method = RequestMethod.DELETE)
    public void removeAppointmentById(@PathVariable String appointmentId) {
        appointmentService.removeAppointmentById(appointmentId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public
    @ResponseBody
    String addNewAppointment(@RequestBody @Valid Appointment appointment) {

        return appointmentService.addAppointment(appointment);
    }

    @RequestMapping(value = "/{appointmentId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Appointment getAppointmentByAppointmentID(@PathVariable("appointmentId") String appointmentId) {

        return appointmentService.getAppointmentByAppointmentId(appointmentId);
    }

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Appointment> getAppointmentsByDate(@PathVariable long date) {

        return appointmentService.getAppointmentsByDate(date);
    }
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAppointment(@RequestBody @Valid Appointment appointment) {
        appointmentService.updateAppointment(appointment);
    }

    @RequestMapping(value = "/groups/{groupId}/applicants/{applicantId}", method = RequestMethod.GET)
    @ResponseBody
    public String getAppointmentIdByGroupIdAndApplicantId(@PathVariable String groupId, @PathVariable String applicantId) {
        return appointmentService.getAppointmentIdByGroupIdAndApplicantId(groupId, applicantId);
    }
}
