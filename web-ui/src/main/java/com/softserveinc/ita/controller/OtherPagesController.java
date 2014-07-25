package com.softserveinc.ita.controller;

import com.softserveinc.ita.service.ViewResolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui")
public class OtherPagesController {

    @Autowired
    private ViewResolverService viewResolverServiceImpl;

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

    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String showGroup(@PathVariable String id) {
        return viewResolverServiceImpl.choosePageByGroupId(id, System.currentTimeMillis());
    }
}