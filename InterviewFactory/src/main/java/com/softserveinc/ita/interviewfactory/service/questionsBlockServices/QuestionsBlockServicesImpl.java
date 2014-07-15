package com.softserveinc.ita.interviewfactory.service.questionsBlockServices;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.interviewfactory.dao.questionsBlockDAO.QuestionsBlockDAO;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsBlockServicesImpl implements QuestionsBlockServices {


    @Autowired
    QuestionsBlockDAO questionsBlockDAO;

    @Qualifier("interviewService")
    @Autowired
    InterviewService interviewService;

    @Override
    public QuestionsBlock getQuestionsBlockFromInterviewByUserId(String userID, String appointmentId){
          return questionsBlockDAO.getQuestionsBlockByInterviewIdAndUserId(userID, appointmentId);
    }

    @Override
    public QuestionsBlock getQuestionsBlockByQuestionsBlockId(String questionsBlockId) {
        return questionsBlockDAO.getQuestionsBlockFromInterviewByQuestionsBlockId(questionsBlockId);
    }

    @Override
    public void addQuestionsBlock(QuestionsBlock questionsBlock) throws WrongCriteriaException, HttpRequestException {
        String interviewId = questionsBlock.getInterviewId();
        Interview interview = interviewService.getInterviewByAppointmentID(interviewId);
        interview.getQuestionsBlocks().add(questionsBlock);
        interviewService.updateInterview(interview);
    }

    @Override
    public String updateQuestionsBlock(QuestionsBlock newQuestionsBlock, String userId) {
        newQuestionsBlock.setUserId(userId);
        return questionsBlockDAO.updateQuestionsBlock(newQuestionsBlock);
    }

    @Override
    public QuestionsBlock getStandardQuestionsBlockFromInterview(String interviewId){
        return questionsBlockDAO.getStandardQuestionsBlockFromInterview(interviewId);
    }

    @Override
    public QuestionsBlock getStandardQuestionsBlock() {
        return questionsBlockDAO.getStandardQuestionsBlock();
    }

    @Override
    public void setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock) {
        questionsBlockDAO.setStandardQuestionsBlock(standardQuestionsBlock);
    }
}
