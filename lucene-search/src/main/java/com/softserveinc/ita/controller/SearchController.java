package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.ApplicantSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ApplicantSearchService applicantSearchService;

    @RequestMapping(value = "/applicants/name/{wildcard}", method = RequestMethod.GET)
    @ResponseBody
    public List<Applicant> getApplicantsByName(@PathVariable String wildcard) {
        return applicantSearchService.getApplicantsByName(wildcard);
    }
    @RequestMapping(value = "/applicants/lastName/{wildcard}", method = RequestMethod.GET)
    @ResponseBody
    public List<Applicant> getApplicantsByLastName(@PathVariable String wildcard) {
        return applicantSearchService.getApplicantsByLastName(wildcard);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showMainPage() {
        return "index";
    }
}