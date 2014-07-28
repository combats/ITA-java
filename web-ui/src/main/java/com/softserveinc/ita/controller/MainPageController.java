package com.softserveinc.ita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class MainPageController {

	@RequestMapping(method = RequestMethod.GET)
	public String showMainPage() {
        return "main";
	}

    //Spring Security see this :
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and/or password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login/login");
        return model;
    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "forward:/login?logout";
    }
}