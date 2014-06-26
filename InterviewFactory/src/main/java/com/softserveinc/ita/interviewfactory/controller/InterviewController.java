package com.softserveinc.ita.interviewfactory.controller;


import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;
import com.softserveinc.ita.exceptions.InvalidUserIDException;

import com.softserveinc.ita.interviewfactory.service.InterviewService;
import com.softserveinc.ita.interviewfactory.service.InterviewServiceMock;
import com.softserveinc.ita.utils.JsonUtil;
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

    private InterviewService interviewService = new InterviewServiceMock();

    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(method = RequestMethod.POST, consumes = "interview/json", produces = "interview/json")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public @ResponseBody
    Interview addNewInterview(@RequestBody String appointmentID, InterviewType type) throws WrongCriteriaException, InvalidUserIDException, ApppoinmentNotFoundException {
        return interviewService.putInterview(appointmentID, type);
    }

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "interview/json")
    @ResponseBody
    public List<Interview> getInterviewByApplicantId(@PathVariable("applicantId") String applicantId) throws InterviewNotFoundException, ApppoinmentNotFoundException {
        return interviewService.getInterviewByApplicantID(applicantId);
    }

    @RequestMapping(value = "/appointments/{appointmentId}", method = RequestMethod.GET, produces = "interview/json")
    @ResponseBody
    public List<Interview> getInterviewByAppointmentId(@PathVariable("appointmentId") String appointmentId) throws InterviewNotFoundException, ApppoinmentNotFoundException {
        return interviewService.getInterviewByAppointmentID(appointmentId);
    }

    @RequestMapping(value = "/{interviewId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)

    @ResponseBody
    public void removeInterviewById(@PathVariable("interviewId") String interviewId) throws InterviewNotFoundException{
        interviewService.removeInterviewById(interviewId);
    }

    @RequestMapping(value = "/{interviewId}", method = RequestMethod.GET, produces = "interview/json")
    @ResponseBody
    public Interview getInterviewByInterviewId(@PathVariable("interviewId") String interviewId) throws InterviewNotFoundException {
        return interviewService.getInterviewByInterviewID(interviewId);
    }

}

