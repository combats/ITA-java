package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Applicant;
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
    List<Group> getGroupsByStatus(@PathVariable String status) {
        return groupService.getGroupsByStatus(status);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/applicants/{groupID}", produces = "application/json")
    public ResponseEntity<List<Applicant>> getApplicantsByGroupID(@PathVariable String groupID) {
        List<Applicant> resultBYGroupID = groupService.getApplicantsByGroupID(groupID);
        if(resultBYGroupID.isEmpty() || resultBYGroupID == null){
            return new ResponseEntity<>(resultBYGroupID, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultBYGroupID, HttpStatus.OK);
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