package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserMockService;
import service.UserService;

/**
 * Created by Anton on 02.06.2014.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(method = RequestMethod.GET, value = "/{userID}")
    public ResponseEntity <User> getUserByID(@PathVariable String userID){
        if (userService.getUserByID(userID) == null)
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);      //any suggestions on more appropriate error code for not valid ID?
        else
            return new ResponseEntity<User>(userService.getUserByID(userID), HttpStatus.OK);
    }

}
