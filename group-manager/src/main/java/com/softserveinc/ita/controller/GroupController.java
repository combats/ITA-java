package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/capacity/{groupID}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    int getGroupCapacity(@PathVariable String groupID) {
        return groupService.getGroupCapacity(groupID);
    }

    @RequestMapping(value = "{groupID}/applicants", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Map<String, ApplicantBenchmark> getApplicantsByGroupIdAndStatus(@PathVariable String groupID,
                                                                    @RequestParam Applicant.Status status) {
        return groupService.getApplicantsByGroupIdAndStatus(groupID, status);
    }

    @RequestMapping(value = "{groupID}/applicants", method = RequestMethod.PUT, produces = "application/json")
    public
    @ResponseBody
    Group updateApplicantsInGroup(@PathVariable String groupID,
                                  @RequestBody Map<String, ApplicantBenchmark> applicants) {
        return groupService.updateApplicantsInGroup(groupID, applicants);
    }
}