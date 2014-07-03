package com.softserveinc.ita.interviewfactory.service.interviewingServices;

import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;
import com.softserveinc.ita.exceptions.InvalidUserIDException;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionsBlockNotFound;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 02.07.14
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public interface InterviewingService {

    QuestionsBlock getQuestionsBlockByUserId(String userID, String interviewID) throws InvalidUserIDException, ApppoinmentNotFoundException, QuestionsBlockNotFound, InterviewNotFoundException;
    QuestionsBlock getQuestionsBlockByQuestionsBlockId(String questionsBlockId, String interviewID) throws InvalidUserIDException, ApppoinmentNotFoundException, QuestionsBlockNotFound, InterviewNotFoundException;
    QuestionsBlock updateQuestionsBlock(String interviewID, QuestionsBlock newQuestionsBlock) throws InvalidUserIDException, ApppoinmentNotFoundException, QuestionsBlockNotFound, InterviewNotFoundException;
    QuestionInformation answerForUserQuestion(String interviewID, String questionsBlockID, QuestionInformation questionInformation) throws InvalidUserIDException, ApppoinmentNotFoundException, QuestionsBlockNotFound, InterviewNotFoundException;

    QuestionsBlock getStandardQuestionsBlockFromInterview(String interviewID);
    QuestionsBlock answerForStandardQuestion(String interviewID, String questionsBlockID, QuestionInformation questionInformation);

    QuestionsBlock getStandardQuestionsBlock();
    QuestionsBlock setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock);

    int getTotalPointsByInterviewID(String interviewID);
}
