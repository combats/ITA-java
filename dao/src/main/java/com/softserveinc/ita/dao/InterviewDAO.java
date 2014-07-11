package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Interview;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
public interface InterviewDAO {

    Interview getInterviewByAppointmentId(String appointmentId);

    String putInterview(Interview interview);

    void removeInterviewByAppointmentId(String appointmentId);

    void updateInterview(Interview interview);

}
