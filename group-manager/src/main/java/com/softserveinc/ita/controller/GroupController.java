package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.exception.impl.GroupDoesntExistException;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class GroupController {
    @Autowired
    private GroupService groupService;
 
    @RequestMapping(value = "{status}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ArrayList<Group> getGroupsByStatus(@PathVariable Group.Status status) {
        return groupService.getGroupsByStatus(status);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicants/{groupID}", produces = "application/json")
    public ResponseEntity<List<Applicant>> getApplicantsByGroupID(@PathVariable String groupID) throws GroupDoesntExistException {
        List<Applicant> resultBYGroupID = groupService.getApplicantsByGroupID(groupID);
        if(resultBYGroupID.isEmpty() || resultBYGroupID == null){
            return new ResponseEntity<>(resultBYGroupID, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultBYGroupID, HttpStatus.OK);
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
}