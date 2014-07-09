package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
import com.softserveinc.ita.exception.ApplicantDoesNotExistException;
import com.softserveinc.ita.exception.ApplicantException;
import com.softserveinc.ita.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Applicant>> getApplicants() {
        List<Applicant> resultList = applicantService.getApplicants();
        if(resultList.isEmpty() || resultList == null){
            return new ResponseEntity<>(resultList, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/groups/{groupID}")
    public ResponseEntity<List<Applicant>> getApplicantsByGroupID(@PathVariable String groupID) {
        List<Applicant> resultBYGroupID = applicantService.getApplicantsByGroupID(groupID);
        if(resultBYGroupID.isEmpty() || resultBYGroupID == null){
            return new ResponseEntity<>(resultBYGroupID, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultBYGroupID, HttpStatus.OK);
    }

    @RequestMapping(value = "{applicantId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Applicant getApplicantById(@PathVariable String applicantId)
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

    @RequestMapping(value = "/applicantID", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> getApplicantIDs() {
        List<String> response = applicantService.getApplicantIDList();
        if (response == null || response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ExceptionHandler(ApplicantException.class)
    public @ResponseBody ExceptionJSONInfo handleApplicantException(ApplicantException exception, HttpServletResponse response){
        int responseStatus = exception.getClass().getAnnotation(ResponseStatus.class).value().value(); //get response status of the exception class
        String exceptionReason = exception.getClass().getAnnotation(ResponseStatus.class).reason();  // get reason of the exception class
        ExceptionJSONInfo exceptionInfo = new ExceptionJSONInfo();
        exceptionInfo.setReason(exceptionReason);
        try {
            response.sendError(responseStatus);   //send http status code
        }
        catch (Exception e){

        }
        return exceptionInfo;
    }

}
