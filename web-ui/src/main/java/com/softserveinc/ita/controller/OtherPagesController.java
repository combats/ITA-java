package com.softserveinc.ita.controller;

import com.softserveinc.ita.GroupStatusCalculator;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.service.impl.HttpRequestExecutorRestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui")
public class OtherPagesController {

    @Autowired
    private HttpRequestExecutorRestImpl httpRequestExecutor;

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
        try {
            Group group = httpRequestExecutor.getObjectByID(id, Group.class);
            Group.Status groupStatus = GroupStatusCalculator.calculateGroupStatus(group.getStartBoardingTime(),
                    group.getStartTime(), group.getEndTime(),System.currentTimeMillis());
            switch (groupStatus) {
                case PLANNED:
                    return "planned";
                case BOARDING:
                    return "boarding";
                case IN_PROCESS:
                    return "inprogress";
                case FINISHED:
                    return "404";
            }
        } catch (HttpRequestException e) {
            e.printStackTrace();
        }
        return null;
    }
}