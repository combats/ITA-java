package com.softserveinc.ita.controller;

import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentDAO appointmentDAO;
    @Autowired
    JsonUtil jsonUtil;

	@RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)

    public void addNewAppointment(String appointment) {

        System.out.println(appointment + "In Controller");


         }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Appointment getAppointmentByAppointmentID(@PathVariable("id") int id) {

       return appointmentDAO.getAppointmentByAppointmentID(id);
    }
}
