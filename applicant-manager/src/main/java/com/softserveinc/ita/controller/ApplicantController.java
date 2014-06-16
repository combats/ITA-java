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

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Applicant> addNewApplicant(@RequestBody Applicant applicant) {
        Applicant newApplicant = applicantService.addNewApplicant(applicant);
        return new ResponseEntity<Applicant>(newApplicant, HttpStatus.OK);
    }

    /**
     *  This method has been implemented for testing purpose and should be replaced or merged with method
     *  developed by student responsible for getApplicantById() method
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/{applicantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Applicant> getApplicantById(@PathVariable String applicantId) {
        Applicant applicant = applicantService.getApplicantById(applicantId);
        return applicant == null ?  new ResponseEntity<Applicant>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<Applicant>(applicant, HttpStatus.OK);
    }
}