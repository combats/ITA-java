package com.softserveinc.ita.controller;

import com.softserveinc.ita.DAO.ApplicantDAO;
import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("applicantServiceImpl")
    private ApplicantService applicantService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> getApplicantIDs() {
        List<String> response = applicantService.getApplicantIDList();
        return response == null || response.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity(response, HttpStatus.OK);
    }
}