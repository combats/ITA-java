package com.softserveinc.ita.interviewfactory.factory;

import com.softserveinc.ita.entity.InterviewType;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 26.06.14
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public interface InterviewFactory {

    CreateInterviewStrategy getInterviewWithType(InterviewType type);
}
