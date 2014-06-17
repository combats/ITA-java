package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.exception.GroupNotFoundException;
import com.softserveinc.ita.service.ApplicantService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {
    private static final String initValue = "ddd";
    private List<Applicant> result;
    private String response;
    private String badRequest;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private ApplicantService applicantService;

    public ApplicantController() {
        result = new ArrayList<>();
        response = initValue;
        badRequest = initValue;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/groups/{groupID}")
    @ResponseBody
    public ResponseEntity<String> getApplicantsByGroupID(@PathVariable String groupID) {
        try {
            result = applicantService.getApplicantsInAGroup(groupID);
            response = jsonUtil.toJson(result);
        } catch (GroupNotFoundException e) {
            badRequest = jsonUtil.toJson("group not found");
            return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
