package com.softserveinc.ita.interviewfactory.controller;


import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.interviewfactory.service.mainServices.InterviewService;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public List<Interview> getInterviewByApplicantId(@PathVariable("applicantId") String applicantId) throws InterviewNotFoundException, HttpRequestException {
        return interviewService.getInterviewByApplicantID(applicantId);
    }

    @RequestMapping(value = "/appointments/{appointmentId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public Interview getInterviewByAppointmentId(@PathVariable("appointmentId") String appointmentId)
            throws InterviewNotFoundException, WrongCriteriaException, HttpRequestException {
        return interviewService.getInterviewByAppointmentID(appointmentId);
    }

    @RequestMapping(value = "/{interviewId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void removeInterviewById(@PathVariable("interviewId") String interviewId) throws InterviewNotFoundException, HttpRequestException {
        interviewService.removeInterviewById(interviewId);
    }

    @RequestMapping(value = "/{interviewId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public Interview getInterviewByInterviewId(@PathVariable("interviewId") String interviewId) throws InterviewNotFoundException, HttpRequestException {
        return interviewService.getInterviewByInterviewID(interviewId);
    }

}

