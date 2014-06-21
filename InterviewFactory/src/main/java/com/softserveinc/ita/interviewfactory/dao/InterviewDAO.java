package com.softserveinc.ita.interviewfactory.dao;


import com.softserveinc.ita.entity.Interview;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public interface InterviewDAO {

    List<Interview> getInterviewByApplicantID(String ID) throws Exception;

    Interview putInterview(Interview interview);
}
