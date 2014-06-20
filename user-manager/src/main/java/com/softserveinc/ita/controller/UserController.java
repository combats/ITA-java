package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userID}")
    @ResponseBody
    public ResponseEntity<String> deleteUserByID(@PathVariable String userID) {
        try {
            userService.deleteUser(userID);
        } catch(UserIDNotFoundUserDaoMockException e){
            String jbadResponce = jsonUtil.toJson("Delete exception: there is no user with following userID: " + userID);
            return new ResponseEntity<>(jbadResponce, HttpStatus.BAD_REQUEST);
        }
        String jresponse = jsonUtil.toJson("Deleted successfully User with userID: " + userID);
        return new ResponseEntity<>(jresponse, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userID}")
    public @ResponseBody User getUserByID(@PathVariable String userID) throws InvalidUserIDException{
        return userService.getUserByID(userID);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/userId")
    public @ResponseBody ArrayList<String> getAllUsersID() {
        return userService.getAllUsersID();
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User>
    getAppointmentByApplicantId(@RequestBody User editedUser)
            throws UserDoesNotExistException, EmptyUserException {
        ResponseEntity responseEntity = new ResponseEntity(userService.editUser(editedUser), HttpStatus.OK);
        return responseEntity;
    }
}

