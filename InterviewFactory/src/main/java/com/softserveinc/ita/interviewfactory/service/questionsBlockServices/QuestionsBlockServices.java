package com.softserveinc.ita.interviewfactory.service.questionsBlockServices;

import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 07.07.14
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public interface QuestionsBlockServices {

    QuestionsBlock getQuestionsBlockFromInterviewByUserId(String userID, String appointmentId);
    QuestionsBlock getQuestionsBlockByQuestionsBlockId(String questionsBlockId);
    void addQuestionsBlock(QuestionsBlock questionsBlock) throws WrongCriteriaException, HttpRequestException;
    String updateQuestionsBlock(QuestionsBlock newQuestionsBlock, String userId);
    QuestionsBlock getStandardQuestionsBlockFromInterview(String interviewId);
    QuestionsBlock getStandardQuestionsBlock();
    void setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock);
}
