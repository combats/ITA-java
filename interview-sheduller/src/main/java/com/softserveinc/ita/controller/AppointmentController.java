package com.softserveinc.ita.controller;


import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.AppointmentDAOMock;
import com.softserveinc.ita.entity.AppointmentDAOMockImpl;
import com.softserveinc.ita.utils.JsonUtil;
import com.softserveinc.ita.utils.impl.JsonUtilGsonImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    AppointmentDAOMock appointmentDAOMock;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addNewAppointment() {
        return "{}";
	}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getAppointmentByAppointmentID(@PathVariable("id") int id) {

        System.out.println("PathVariable is" + id);

       return jsonUtil.toJson(appointmentDAOMock.getAppointmentByAppointmentID(id));
    }
}
