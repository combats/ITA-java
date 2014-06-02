package com.softserveinc.ita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addNewAppointment() {
		return "{}";
	}
}
