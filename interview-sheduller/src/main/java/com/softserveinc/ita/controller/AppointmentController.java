package com.softserveinc.ita.controller;


import com.softserveinc.ita.dao.AppointmentDAO;
import com.softserveinc.ita.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentDAO appointmentDAO;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public @ResponseBody String addNewAppointment(@RequestBody Appointment appointment) {

          return appointmentDAO.putAppointment(appointment);
       }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Appointment getAppointmentByAppointmentID(@PathVariable("id") int id) {

       return appointmentDAO.getAppointmentByAppointmentID(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public @ResponseBody Appointment editAppointmentById(@PathVariable("id") int id, @RequestBody Appointment appointment) {

        appointmentDAO.editAppointmentById(id, appointment);
        return appointment;
    }
}
