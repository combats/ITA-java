package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.Appointment;
import org.joda.time.DateTime;

import java.util.*;

/**
 * Created by mskryntc on 03.06.2014.
 */
public class AppointmentServiceMock implements AppointmentService {

    @Override
    public Appointment getAppointmentByApplicantId(String applicantId) {
        List<String> userIdList = new ArrayList<>();
        Collections.addAll(userIdList, "1", "2");
        List<String> applicantIdList = new ArrayList<>();
        Collections.addAll(applicantIdList, "1", "2");
        Appointment appointmentOne = new Appointment(userIdList, applicantIdList, 1401951895035L);
        Appointment appointmentTwo = new Appointment(userIdList, applicantIdList, 1401952037427L);

        if (applicantId.equals("1")) {
            return appointmentOne;
        } else {
            return appointmentTwo;
        }
    }

    @Override
    public List<Appointment> getAppointmentsByDay(long date) {

        DateTime requirementDate = new DateTime(date);
        List<Appointment> resultList = new LinkedList<>();

        if(requirementDate.getMillis() < new DateTime(0).getMillis()){
             return resultList;
        }

        MokDataProvider<Appointment> dataProvider =  MokDataProvider.getDataProvider();



         Iterator<Appointment> dataIterator = dataProvider.getDataList().iterator();
      while (dataIterator.hasNext()){
           Appointment appointment  =  dataIterator.next();
          DateTime appDate = new DateTime(appointment.getStartTime());
          if(requirementDate.getYear() == appDate.getYear() &&
                  requirementDate.getMonthOfYear() == appDate.getMonthOfYear() &&
                  requirementDate.getDayOfMonth() == appDate.getDayOfMonth()){
              resultList.add(appointment);
          }

      }

        return resultList;
    }
}
