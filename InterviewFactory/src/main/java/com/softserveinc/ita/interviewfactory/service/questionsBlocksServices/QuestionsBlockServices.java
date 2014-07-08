package com.softserveinc.ita.interviewfactory.service.questionsBlocksServices;

import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionsBlockNotFound;
import exceptions.WrongCriteriaException;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 07.07.14
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public interface QuestionsBlockServices {

    QuestionsBlock getQuestionsBlockByUserId(String userID, String appointmentId) throws QuestionsBlockNotFound, InterviewNotFoundException, HttpRequestException, WrongCriteriaException;
    QuestionsBlock getQuestionsBlockByQuestionsBlockId(String questionsBlockId) throws QuestionsBlockNotFound ;
    QuestionsBlock updateQuestionsBlock(QuestionsBlock newQuestionsBlock) throws QuestionsBlockNotFound;
    QuestionsBlock getStandardQuestionsBlockFromInterview(String interviewId) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException;

    QuestionsBlock getStandardQuestionsBlock();
    QuestionsBlock setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock);
}
