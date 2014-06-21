package com.softserveinc.ita.interviewfactory;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;
import com.softserveinc.ita.exceptions.InvalidUserIDException;


/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public interface InterviewCreator {

    public void createInterview() throws InvalidUserIDException, ApppoinmentNotFoundException;

    String getAppointmentId();

}
