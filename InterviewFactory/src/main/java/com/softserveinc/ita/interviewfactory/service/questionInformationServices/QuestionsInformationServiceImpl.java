package com.softserveinc.ita.interviewfactory.service.questionInformationServices;

import com.softserveinc.ita.dao.QuestionInformationDAO;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.interviewfactory.service.questionsBlocksServices.QuestionsBlockServices;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsInformationServiceImpl implements QuestionsInformationServices {

    @Autowired
    QuestionInformationDAO questionInformationDAO;

    @Autowired
    QuestionsBlockServices questionsBlockService;

    @Autowired
    InterviewService interviewService;

    @Override
    public QuestionInformation getQuestionInformationById(String questionsInformationId) {
        return questionInformationDAO.getQuestionInformationById(questionsInformationId);
    }

    @Override
    public void addQuestionInformation(QuestionInformation questionInformation, String userId) throws WrongCriteriaException, HttpRequestException {
        String interviewId = questionInformation.getInterviewId();
        interviewService.getInterviewByAppointmentID(interviewId); //если интервью не существует, тут оно будет создано

        QuestionsBlock questionsBlock = questionsBlockService.getQuestionsBlockFromInterviewByUserId(userId, interviewId);
        Set<QuestionInformation> questionInformationSet = questionsBlock.getQuestions();
        questionInformationSet.add(questionInformation);
        questionsBlock.setQuestions(questionInformationSet);
        questionsBlockService.updateQuestionsBlock(questionsBlock, userId);

    }

    @Override
    public String updateQuestionInformation(QuestionInformation questionInformation) {
        return questionInformationDAO.updateQuestionInformation(questionInformation);
    }

    @Override
    public void deleteQuestionInformationById(String questionInformationId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
