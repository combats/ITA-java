package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.service.ApplicantService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {
    private List<Applicant> resultList;

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
}
