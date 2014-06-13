package com.softserveinc.ita.controller;


import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {

    @Autowired
    @Qualifier("applicantService")
    private ApplicantService applicantService ;

    @RequestMapping(value = "{applicantId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Applicant getAppointmentByApplicantId(@PathVariable String applicantId)
            throws ApplicantDoesNotExistException {
        Applicant searchedApplicant = applicantService.getApplicantById(applicantId);
        return searchedApplicant;
    }
}
