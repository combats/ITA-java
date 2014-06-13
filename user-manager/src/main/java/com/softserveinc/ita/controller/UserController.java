package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.service.UserService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> postNewUser(@RequestBody User user, UriComponentsBuilder builder) throws UserAlreadyExistsException {
        User createdUser = userService.postNewUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/users/{userID}").buildAndExpand(createdUser.getUserID().toString()).toUri());
        return new ResponseEntity<User>(createdUser, headers, HttpStatus.CREATED);
    }
}
