package com.softserveinc.ita.mvc;

import com.softserveinc.ita.BaseMVCTest;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.validators.AppointmentValidator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AppointmentValidatorTest extends BaseMVCTest{

    @Autowired
    private Validator validator;
    //supports method
    @Test
         public void testSupportsAppointmentClass(){
        //Arrange
        Appointment appointment = new Appointment();
        //Act
        boolean result = validator.supports(appointment.getClass());
        //Assert
        assertTrue(result);
    }
    @Test
    public void testDoesNotSupportAnyOtherButAppointmentClass(){
        //Act
        boolean result = validator.supports(Object.class);
        //Assert
        assertFalse(result);
    }

    //validate method
    @Test
    public void testValidatesAppointmentAndGivesErrors(){
        //Arrange
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "some_unexisting_applicant_id1", "some_unexisting_applicant_id2");
        List<String> applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "some_unexisting_applicant_id1", "some_unexisting_applicant_id2");
        Appointment appointment = new Appointment(userIdList, applicantIdList, 1401951895035L);

        Errors errors = new BindException(appointment, "appointment");
        //Act
        validator.validate(appointment, errors);
        //Assert
        assertTrue(errors.getErrorCount() > 0);
    }

}
