package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
import com.softserveinc.ita.exception.GroupException;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.GET)
      public String getGroups() {
        return "groupsList";
    }

    @RequestMapping(value = "{status}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ArrayList<Group> getGroupsByStatus(@PathVariable String status) {
        return groupService.getGroupsByStatus(status);
    }

    @RequestMapping(value ="/courses", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ArrayList<Course> getCourses(){
        return groupService.getCourses();
    }

    @RequestMapping(value = "/addGroup",method = RequestMethod.POST)
    public @ResponseBody Group addGroup(@RequestBody Group group){
        return groupService.createGroup(group);
    }

    @RequestMapping(value = "/allGroups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ArrayList<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

    @ExceptionHandler(GroupException.class)
    public @ResponseBody
    ExceptionJSONInfo handleApplicantException(GroupException exception, HttpServletResponse response){
        int responseStatus = exception.getClass().getAnnotation(ResponseStatus.class).value().value();
        String exceptionReason = exception.getClass().getAnnotation(ResponseStatus.class).reason();
        ExceptionJSONInfo exceptionInfo = new ExceptionJSONInfo();
        exceptionInfo.setReason(exceptionReason);
        try {
            response.sendError(responseStatus);
        } catch (IOException e) {
        }
        return exceptionInfo;
    }
}