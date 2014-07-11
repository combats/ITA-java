package com.softserveinc.ita.interviewfactory.service.questionsBlocksServices;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.InterviewNotFoundException;
import exceptions.QuestionsBlockNotFound;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 07.07.14
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
//public class QuestionsBlockServicesMock implements QuestionsBlockServices {
//
//    //-------------VadimNaumenko mock for tests------------------------------------
//    @Autowired
//    InterviewFactory interviewFactory;
//
//    @Autowired
//    InterviewService interviewService;
//
//    //Standard questions mock
//
//    private QuestionsBlock standardQuestionsBlock = new QuestionsBlock();{
//        QuestionInformation questionInformation1 = new QuestionInformation();
//        questionInformation1.setQuestion("What is your name?");
//        questionInformation1.setWeight(2);
//        questionInformation1.setId("0");
//        questionInformation1.setQuestionsBlockId("Hz, standart vopros");
//        QuestionInformation questionInformation2 = new QuestionInformation();
//        questionInformation2.setQuestion("How are you?");
//        questionInformation2.setWeight(3);
//        questionInformation2.setId("1");
//        questionInformation2.setQuestionsBlockId("Hz, standart vopros");
//
//        List<QuestionInformation> QuestionInformationList = new ArrayList<QuestionInformation>();{
//            QuestionInformationList.add(questionInformation1);
//            QuestionInformationList.add(questionInformation2);
//        }
//
//        standardQuestionsBlock.setQuestions(QuestionInformationList);
//        standardQuestionsBlock.setId("Hz, standart vopros");
//    }
//
//    private List<QuestionsBlock> questionsBlocks = new ArrayList<>();
//
//    @Override
//    public QuestionsBlock getQuestionsBlockByUserId(String userID, String appointmentId) throws QuestionsBlockNotFound, InterviewNotFoundException, HttpRequestException, WrongCriteriaException {
//
//        questionsBlocks = interviewService.getInterviewByAppointmentID(appointmentId).getQuestionsBlocks();
//
//        for (QuestionsBlock questionsBlock1 : questionsBlocks){
//            if (questionsBlock1.getUser() != null && questionsBlock1.getUser().getId().equals(userID))
//            return questionsBlock1;
//        }
//        throw new QuestionsBlockNotFound("Wrong userID or appointmentId");
//    }
//
//    @Override
//    public QuestionsBlock getQuestionsBlockByQuestionsBlockId(String questionsBlockId) throws QuestionsBlockNotFound {
//
//        for (QuestionsBlock questionsBlock1 : questionsBlocks){
//            if (questionsBlock1.getId().equals(questionsBlockId))
//                return questionsBlock1;
//        }
//        throw new QuestionsBlockNotFound("Wrong userID");
//    }
//
//    @Override
//    public QuestionsBlock updateQuestionsBlock(QuestionsBlock newQuestionsBlock) throws QuestionsBlockNotFound {
//
//        for (int i = 0; i < questionsBlocks.size(); i++){
//            if (questionsBlocks.get(i).getId().equals(newQuestionsBlock.getId()))
//                questionsBlocks.add(i, newQuestionsBlock);
//                return questionsBlocks.get(i);
//        }
//        throw new QuestionsBlockNotFound("Wrong Id");
//    }
//
//    @Override
//    public QuestionsBlock getStandardQuestionsBlock() {
//        return standardQuestionsBlock;
//    }
//
//    @Override
//    public QuestionsBlock setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock) {
//        this.standardQuestionsBlock = standardQuestionsBlock;
//        return getStandardQuestionsBlock();
//    }
//
//    @Override
//    public QuestionsBlock getStandardQuestionsBlockFromInterview(String appointmentId) throws InterviewNotFoundException, HttpRequestException, QuestionsBlockNotFound, WrongCriteriaException {
//        questionsBlocks = interviewService.getInterviewByAppointmentID(appointmentId).getQuestionsBlocks();
//
//        for (QuestionsBlock questionsBlock1 : questionsBlocks){
//            if(questionsBlock1.getUser() == null)
//                return questionsBlock1;
//        }
//        throw new QuestionsBlockNotFound("Wrong userID or appointmentId");
//    }
//}
