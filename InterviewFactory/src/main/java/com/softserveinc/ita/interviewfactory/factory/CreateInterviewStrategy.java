package com.softserveinc.ita.interviewfactory.factory;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionsBlockNotFound;
import exceptions.WrongCriteriaException;


/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 26.06.14
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public interface CreateInterviewStrategy {

    public Interview create(String interviewId) throws HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException, InterviewNotFoundException;
}
