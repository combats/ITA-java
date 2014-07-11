package com.softserveinc.ita.interviewfactory.service.questionInformationServices;

import com.softserveinc.ita.dao.QuestionInformationDAO;
import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.interviewfactory.service.questionsBlocksServices.QuestionsBlockServices;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsInformationServiceImpl implements QuestionsInformationServices {

    @Autowired
    private QuestionInformationDAO questionInformationDAO;

    @Autowired
    private QuestionsBlockServices questionsBlockService;

    @Override
    public QuestionInformation getQuestionInformationById(String questionsInformationId) {
        return questionInformationDAO.getQuestionInformationById(questionsInformationId);
    }

    @Override
    public String addQuestionInformation(QuestionInformation questionInformation, String userId) {
        questionInformation.setQuestionsBlock(questionsBlockService.getQuestionsBlockFromInterviewByUserId(userId, questionInformation.getInterviewId()));
        return questionInformationDAO.addQuestionInformation(questionInformation);
    }

    @Override
    public String updateQuestionInformation(QuestionInformation questionInformation) {
        return questionInformationDAO.updateQuestionInformation(questionInformation);
    }

    @Override
    public void deleteQuestionInformationById(String questionsInformationId) {
        questionInformationDAO.deleteQuestionInformationById(questionsInformationId);
    }

}
