package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.exception.GroupNotFoundException;
import com.softserveinc.ita.service.ApplicantService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {
    private List<Applicant> resultList;
    private List<Applicant> resultBYGroupID;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private ApplicantService applicantService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getApplicants() {
        resultList = applicantService.getApplicants();
        String result = jsonUtil.toJson(resultList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/groups/{groupID}")
    @ResponseBody
    public ResponseEntity<String> getApplicantsByGroupID(@PathVariable String groupID) {
        try {
            resultBYGroupID = applicantService.getApplicantsInAGroup(groupID);
        } catch (GroupNotFoundException e) {
            String badRequest = jsonUtil.toJson("group not found");
            return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        String response = jsonUtil.toJson(resultBYGroupID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "{applicantId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Applicant getAppointmentByApplicantId(@PathVariable String applicantId)
            throws ApplicantDoesNotExistException {
        Applicant searchedApplicant = applicantService.getApplicantById(applicantId);
        return searchedApplicant;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Applicant> addNewApplicant(@RequestBody Applicant applicant) {
        Applicant newApplicant = applicantService.addNewApplicant(applicant);
        return new ResponseEntity<Applicant>(newApplicant, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Applicant> editApplicant(@RequestBody Applicant applicant) {
        Applicant editedApplicant = applicantService.editApplicant(applicant);
        return new ResponseEntity<Applicant>(editedApplicant, HttpStatus.OK);
    }

}
