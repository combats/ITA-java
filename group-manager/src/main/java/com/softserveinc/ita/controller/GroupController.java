package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.ApplicantBenchmark;
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
import java.util.Map;

@Controller
@RequestMapping("/")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "{groupId}/applicants", params = {"status"}, method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<Map<String, ApplicantBenchmark>> getApplicantsIDListByGroupIdAndStatus(@PathVariable String groupId,
                                                                                          @RequestParam Applicant.Status status) {
        Map<String, ApplicantBenchmark> applicants = groupService.getApplicantsByStatus(groupId, status);
        if (applicants == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(applicants, HttpStatus.OK);
    }

    @RequestMapping(value = "{groupId}/applicants", method = RequestMethod.PUT, produces = "application/json")
    public
    @ResponseBody
    boolean changeApplicantsStatus(@PathVariable String groupId, @RequestBody Map<String, ApplicantBenchmark> applicants) {
        groupService.addOrUpdateApplicantIDListByStatus(groupId, applicants);
        return true;
    }

    @RequestMapping(value = "{groupId}/capacity", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<Integer> getGroupCapacityByGroupId(@PathVariable String groupId) {
        int capacity = groupService.getGroupCapacity(groupId);
        return new ResponseEntity<Integer>(capacity, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{status}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Group> getGroupsByStatus(@PathVariable Group.Status status) {
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
    public @ResponseBody List<Course> getCourses(){
        return groupService.getCourses();
    }

    @RequestMapping(value = "/addGroup",method = RequestMethod.POST)
    public @ResponseBody Group addGroup(@RequestBody Group group){
        return groupService.createGroup(group);
    }

    @RequestMapping(value = "/allGroups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }
}