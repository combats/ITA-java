package com.softserveinc.ita.interviewfactory.service.questionInformationServices;

import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionNotFoundException;
import exceptions.QuestionsBlockNotFound;
import exceptions.WrongCriteriaException;


/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 07.07.14
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public interface QuestionsInformationServices {

    QuestionInformation postQuestionInformation(QuestionInformation questionInformation);
    void deleteQuestionInformationById(String questionsInformationId);
    QuestionInformation editQuestionInformation(QuestionInformation questionInformation) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException, QuestionNotFoundException ;
    QuestionInformation getQuestionInformationById(String questionsInformationId) throws QuestionNotFoundException, InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException;

}
