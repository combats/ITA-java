package com.softserveinc.ita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class MainPageController {

    public static final String J_USERNAME = "j_username";
    public static final String J_PASSWORD = "j_password";
    public static final String FRONTPAGE = "main";

//    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        String user = httpServletRequest.getParameter(J_USERNAME);
//        String password = httpServletRequest.getParameter(J_PASSWORD);
//        return new ModelAndView("login/login");
//    }

	@RequestMapping(method = RequestMethod.GET)
	public String showMainPage() {
        return "main";
	}

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView postLoginPage(@RequestParam(value = "error", required = false) Integer height,
                                      @RequestParam(value = "width", required = false) Integer width) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/login");
        mav.addObject("error", "Wrong username/password combination!");
        return mav;
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/login");
        mav.addObject("error", "Wrong username/password combination!");
        return mav;
    }
}