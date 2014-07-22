package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.InterviewResult;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppointmentController {
    //    @Autowired
//    @Qualifier("validator")
//    private Validator appointmentValidator;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private JsonUtil jsonUtil;

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(appointmentValidator);
//    }

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Appointment getAppointmentByApplicantId(@PathVariable String applicantId) {
        return appointmentService.getAppointmentByApplicantId(applicantId);
    }

    @RequestMapping(value = "/{appointmentId}", method = RequestMethod.DELETE)
    public void removeAppointmentById(@PathVariable String appointmentId) {
        appointmentService.removeAppointmentById(appointmentId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Appointment addNewAppointment(@RequestBody Appointment appointment) {//@Valid Appointment
        appointment.setID("sfdqw");
        return appointment;
        //return appointmentService.addAppointment(appointment);
    }


    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Appointment editAppointment(@RequestBody Appointment appointment) {//@Valid Appointment
        return appointment;
        //return appointmentService.addAppointment(appointment);
    }


    @RequestMapping(value = "/{appointmentId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Appointment getAppointmentByAppointmentID(@PathVariable("appointmentId") String appointmentId) {
        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("1");
        return new Appointment(list, "1", 1404927516676L, 1800000);
//        return appointmentService.getAppointmentByAppointmentId(appointmentId);
    }

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Appointment> getAppointmentsByDate(@PathVariable long date) {

        return appointmentService.getAppointmentsByDate(date);
    }

    @RequestMapping(value = "/interview/{interviewID}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public InterviewResult getInterviewResultByID(@PathVariable String interviewID) {
        if (Integer.valueOf(interviewID) >= 5) {
            return new InterviewResult("Very good", Integer.parseInt(interviewID) * 10);
        }
        return null;
//        return appointmentService.getAppointmentsByDate(date);
    }

    @RequestMapping(value = "/groups/{groupId}/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getAppointmentIdByGroupIdAndApplicantId(@PathVariable String groupId, @PathVariable String applicantId) {
        //return appointmentService.getAppointmentIdByGroupIdAndApplicantId(groupId, applicantId);
        if (Integer.valueOf(applicantId) >= 3) {
            return applicantId.toString();
        }
        return "";
    }
}
