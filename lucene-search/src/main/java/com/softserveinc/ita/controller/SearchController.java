package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.ApplicantSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ApplicantSearchService applicantSearchService;

    @RequestMapping(value = "/applicants/name", method = RequestMethod.GET)
    @ResponseBody
    public List<Applicant> getApplicantsByName(@RequestParam(value = "wildcard") String wildcard) {
        return applicantSearchService.getApplicantsByName(wildcard);
    }
    @RequestMapping(value = "/applicants/lastName", method = RequestMethod.GET)
    @ResponseBody
    public List<Applicant> getApplicantsByLastName(@RequestParam(value = "wildcard") String wildcard) {
        return applicantSearchService.getApplicantsByLastName(wildcard);
    }

    @RequestMapping( method = RequestMethod.GET)
    public String testPage() {
        return "/search/index.html";
    }
}