package com.softserveinc.ita.interviewfactory.controller;


import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.interviewfactory.service.mainServices.InterviewService;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionsBlockNotFound;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public List<Interview> getInterviewByApplicantId(@PathVariable("applicantId") String applicantId) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException {
        return interviewService.getInterviewByApplicantID(applicantId);
    }

    @RequestMapping(value = "/{interviewId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public Interview getInterviewByAppointmentId(@PathVariable("interviewId") String interviewId)
            throws InterviewNotFoundException, WrongCriteriaException, HttpRequestException, QuestionsBlockNotFound {
        return interviewService.getInterviewByAppointmentID(interviewId);
    }

    @RequestMapping(value = "/{interviewId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void removeInterviewByAppointmentId(@PathVariable("interviewId") String interviewId) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException {
        interviewService.removeInterviewByAppointmentId(interviewId);
    }

}

