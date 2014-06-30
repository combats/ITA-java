package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Group;
import com.softserveinc.ita.entity.exceptions.ExceptionJSONInfo;
import com.softserveinc.ita.exception.GroupException;
import com.softserveinc.ita.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;


    @RequestMapping(value = "/{status}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ArrayList<Group> getGroupsByStatus(@PathVariable String status) {
        return groupService.getGroupsByStatus(status);
    }

    @ExceptionHandler(GroupException.class)
    public @ResponseBody
    ExceptionJSONInfo handleApplicantException(GroupException exception, HttpServletResponse response){
        int responseStatus = exception.getClass().getAnnotation(ResponseStatus.class).value().value(); //get response status of the exception class
        String exceptionReason = exception.getClass().getAnnotation(ResponseStatus.class).reason();  // get reason of the exception class
        ExceptionJSONInfo exceptionInfo = new ExceptionJSONInfo();
        exceptionInfo.setReason(exceptionReason);
        try {
            response.sendError(responseStatus);
        }
        catch (Exception e){

        }
        return exceptionInfo;
    }
}

