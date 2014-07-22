package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant.Status;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
import com.softserveinc.ita.exception.GroupException;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
                                                                                          @RequestParam Status status) {
        Map<String, ApplicantBenchmark> applicants = groupService.getApplicantsByStatus(groupId, status);
        if (applicants == null) {
            return new ResponseEntity<Map<String, ApplicantBenchmark>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Map<String, ApplicantBenchmark>>(applicants, HttpStatus.OK);
    }

    @RequestMapping(value = "{groupId}/applicants", method = RequestMethod.PUT, produces = "application/json")
    public
    @ResponseBody
    void changeApplicantsStatus(@PathVariable String groupId, @RequestBody Map<String, ApplicantBenchmark> applicants) {
        groupService.addOrUpdateApplicantIDListByStatus(groupId, applicants);
    }

    @RequestMapping(value = "{groupId}/capacity", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<Integer> getGroupCapacityByGroupId(@PathVariable String groupId) {
        int capacity = groupService.getGroupCapacity(groupId);
        return new ResponseEntity<Integer>(capacity, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGroups() {
        return "groupsList";
    }

    @RequestMapping(value = "{status}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Group> getGroupsByStatus(@PathVariable String status) {
        return groupService.getGroupsByStatus(status);
    }
}