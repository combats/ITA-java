package com.softserveinc.ita.interviewfactory.controller;


import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.interviewfactory.dao.InterviewDAO;
import com.softserveinc.ita.interviewfactory.dao.InterviewDAOImpl;
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


    private InterviewDAO interviewDAO = new InterviewDAOImpl();

    private InterviewService interviewService = new InterviewServiceMock();

    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(method = RequestMethod.POST, consumes = "interview/json", produces = "interview/json")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public @ResponseBody
    Interview addNewInterview(@RequestBody String appointmentID, InterviewType type)
            throws Exception {
        return interviewService.putInterview(appointmentID, type);
    }

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "interview/json")
    @ResponseBody
    public List<Interview> getInterviewByApplicantId(@PathVariable("applicantId") String applicantId) throws Exception {
        return interviewService.getInterviewByApplicantID(applicantId);
    }

}

