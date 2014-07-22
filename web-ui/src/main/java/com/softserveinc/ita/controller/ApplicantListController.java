package com.softserveinc.ita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ApplicantListController {

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String goToPage(@PathVariable String page) {
        return page;
    }
}
