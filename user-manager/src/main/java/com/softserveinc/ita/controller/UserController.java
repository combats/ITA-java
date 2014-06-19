package com.softserveinc.ita.controller;

import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import com.softserveinc.ita.service.UserService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private UserService userService;

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
}
