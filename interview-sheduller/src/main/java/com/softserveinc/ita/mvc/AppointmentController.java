package com.softserveinc.ita.mvc;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private JsonUtil jsonUtil;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addNewAppointment() {
		return "{}";
	}

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Appointment getAppointmentByApplicantId(@PathVariable String applicantId) {
        return appointmentService.getAppointmentByApplicantId(applicantId);
    }
}
