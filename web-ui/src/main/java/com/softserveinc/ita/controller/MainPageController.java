package com.softserveinc.ita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainPageController {
	@RequestMapping(method = RequestMethod.GET)
	public String showMainPage() {
		return "main";
	}
    @RequestMapping(value = "interview", method = RequestMethod.GET)
    public String showPage() {
        return "interview/pages/app";
    }

    @RequestMapping(value = "ui/users", method = RequestMethod.GET)
    public String showUsers() {
        return "users";
    }
}