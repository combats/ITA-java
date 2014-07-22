package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "{groupId}/applicants", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<Map<String, ApplicantBenchmark>> getApplicantsIDListByGroupId(@PathVariable String groupId) {
        Map<String, ApplicantBenchmark> applicants = groupService.getGroupById(groupId).getApplicants();
        if (applicants == null) {
            return new ResponseEntity<Map<String, ApplicantBenchmark>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Map<String, ApplicantBenchmark>>(applicants, HttpStatus.OK);
    }

    @RequestMapping(value = "{groupId}/applicants", params = {"status"}, method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<Map<String, ApplicantBenchmark>> getApplicantsIDListByGroupIdAndStatus(@PathVariable String groupId,
                                                                                          @RequestParam Applicant.Status status) {
        Map<String, ApplicantBenchmark> applicants = groupService.getGroupById(groupId).getApplicantsByStatus(status);
        System.out.println(status);
        if (applicants == null) {
            return new ResponseEntity<Map<String, ApplicantBenchmark>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Map<String, ApplicantBenchmark>>(applicants, HttpStatus.OK);
    }

    @RequestMapping(value = "{groupId}/applicants", method = RequestMethod.PUT, produces = "application/json")
    public
    @ResponseBody
    boolean changeApplicantsStatus(@PathVariable String groupId, @RequestBody Map<String, ApplicantBenchmark> applicants) {
        groupService.getGroupById(groupId).addOrUpdateApplicantIDListByStatus(applicants);
        return true;
    }


    @RequestMapping(value = "{groupId}/capacity", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<Integer> getGroupCapacityByGroupId(@PathVariable String groupId) {
        int capacity = groupService.getGroupById(groupId).getCapacity();
        return new ResponseEntity<Integer>(capacity, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{groupId}", method = RequestMethod.PUT, produces = "application/json",
//            consumes = "application/json")
//    public
//    @ResponseBody
//    ResponseEntity<Group> addApplicants(@PathVariable String groupId, @RequestBody List<Integer> applicants,
//                                        @RequestBody Applicant.Status status) {
//        Group requestedGroup = groupService.addApplicants(groupId, applicants, status);
//        if (requestedGroup == null) {
//            return new ResponseEntity<Group>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<Group>(requestedGroup, HttpStatus.OK);
//    }
}
