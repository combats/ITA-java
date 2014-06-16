package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.service.UserService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

    private final String SUCCESSFUL_EDIT_MESSAGE = "User was successfully edited";

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User>
    getAppointmentByApplicantId(@RequestBody User editedUser)
            throws UserDoesNotExistException, EmptyUserException {
        ResponseEntity responseEntity = new ResponseEntity(userService.editUser(editedUser), HttpStatus.OK);
        return responseEntity;
    }
}
