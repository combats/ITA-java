package com.softserveinc.ita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui")
public class OtherPagesController {

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String getGroups() {
        return "groups";
    }

    @RequestMapping(value = "/interview", method = RequestMethod.GET)
    public String showPage() {
        return "interview/pages/app";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers() {
        return "users";
    }
}