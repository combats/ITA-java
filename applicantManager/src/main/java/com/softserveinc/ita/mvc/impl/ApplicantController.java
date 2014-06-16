package com.softserveinc.ita.mvc.impl;

import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/applicants/applicantID")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantServiceImpl;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> getApplicantIDs() {
        List<String> response = applicantServiceImpl.getApplicantIDList();
        if (response == null || response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
}