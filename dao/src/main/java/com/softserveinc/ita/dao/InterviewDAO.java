package com.softserveinc.ita.dao;

import com.softserveinc.ita.dao.exceptions.InterviewNotFoundException;
import com.softserveinc.ita.entity.Interview;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 18.06.14
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public interface InterviewDAO {

    List<Interview> getInterviewByApplicantID(String ID) throws InterviewNotFoundException;

    Interview putInterview(Interview interview);
}
