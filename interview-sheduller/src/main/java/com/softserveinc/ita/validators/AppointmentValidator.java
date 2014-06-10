package com.softserveinc.ita.validators;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.service.HttpRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class AppointmentValidator implements Validator {
    @Autowired
    HttpRestService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return Appointment.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Appointment appointment = (Appointment) o;

        validateApplicants(errors, appointment);
        validateUsers(errors, appointment);
    }

    private void validateUsers(Errors errors, Appointment appointment) {
        List<String> userIdList = appointment.getUserIdList();
        for(String userId : userIdList){
            if(!service.userExists(userId)){
                //throw error
                //TODO: think of proper error code to return
                errors.reject("400","There is no user with id " + userId);
            }
        }
    }

    private void validateApplicants(Errors errors, Appointment appointment) {
        List<String> applicantIdList = appointment.getApplicantIdList();
        for(String applicantId: applicantIdList){
            if(!service.applicantExists(applicantId)){
                //throw error
                errors.reject("400","There is no applicant with id " + applicantId);
            }
        }
    }
}