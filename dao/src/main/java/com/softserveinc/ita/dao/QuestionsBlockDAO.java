package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public interface QuestionsBlockDAO {

    QuestionsBlock getQuestionsBlockByInterviewIdAndUserId(String userID, String appointmentId);
    QuestionsBlock getQuestionsBlockFromInterviewByQuestionsBlockId(String questionsBlockId);
    String updateQuestionsBlock(QuestionsBlock newQuestionsBlock);
    QuestionsBlock getStandardQuestionsBlockFromInterview(String interviewId);
    QuestionsBlock getStandardQuestionsBlock();
    void setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock);
}
