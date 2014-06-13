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

        validateUsers(appointment, errors);
        validateApplicants(appointment, errors);
    }

    private void validateUsers(Appointment appointment, Errors errors) {
        List<String> userIdList = appointment.getUserIdList();
        for(String userId : userIdList){
            if(!service.userExists(userId)){
                errors.reject("400","There is no user with id " + userId);
            }
        }
    }

    private void validateApplicants(Appointment appointment, Errors errors) {
        List<String> applicantIdList = appointment.getApplicantIdList();
        for(String applicantId: applicantIdList){
            if(!service.applicantExists(applicantId)){
                errors.reject("400","There is no applicant with id " + applicantId);
            }
        }
    }
}