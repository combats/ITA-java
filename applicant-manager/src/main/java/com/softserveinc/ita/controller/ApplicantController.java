package com.softserveinc.ita.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.softserveinc.ita.service.ApplicantService;
import com.softserveinc.ita.entity.Applicant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Applicant> editApplicant(@RequestBody Applicant applicant) {
        Applicant editedApplicant = applicantService.editApplicant(applicant);
        return new ResponseEntity<Applicant>(editedApplicant, HttpStatus.OK);
    }

    /**
     *  This method has been implemented for testing purpose
     **/
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Applicant> addNewApplicant(@RequestBody Applicant applicant) {
        Applicant newApplicant = applicantService.addNewApplicant(applicant);
        return new ResponseEntity<Applicant>(newApplicant, HttpStatus.OK);
    }
}