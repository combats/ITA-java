package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "groups";
    }

    @Autowired
    private GroupService groupService;


    @RequestMapping(value = "/groups/{status}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ArrayList<Group> getGroupsByStatus(@PathVariable String status) {
        return groupService.getGroupsByStatus(status);
    }


    @RequestMapping(value = "/groups/list/{groupId}", method = RequestMethod.GET)
    public String redirect(@PathVariable String groupId) {
        return "userList";
    }

    @RequestMapping(value = "/groups/create", method = RequestMethod.GET)
    public String redirect() {
        return "createGroup";
    }
}

