package com.softserveinc.ita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainPageController {
	@RequestMapping(method = RequestMethod.GET)
	public String showMainPage() {
		return "main";
	}

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login/login");
        mav.addObject("error", "Wrong username/password combination!");
        return mav;
    }
}