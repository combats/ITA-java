package com.softserveinc.ita.interviewfactory.dao.questionsBlockDAO;

import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 15.07.14
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsBlockDAOMock implements QuestionsBlockDAO {
    @Override
    public QuestionsBlock getQuestionsBlockByInterviewIdAndUserId(String userID, String appointmentId) {

        Set<QuestionsBlock> allQuestionsBlocks = new HashSet<>();
        QuestionsBlock questionsBlock = new QuestionsBlock();
        Set<QuestionInformation> questionInformationsList = new HashSet<>();
        QuestionInformation questionInformation = new QuestionInformation();
        questionInformation.setQuestion("Question body");
        questionInformation.setAnswer("answer");
        questionInformation.setComment("normas");
        questionInformation.setMark(2);
        questionInformation.setWeight(1);
        questionInformation.setInterviewId("1");
        questionInformationsList.add(questionInformation);
        questionsBlock.setQuestions(questionInformationsList);
        questionsBlock.setInterviewId("1");
        questionsBlock.setUserId("1");
        questionsBlock.setId("1");
        allQuestionsBlocks.add(questionsBlock);

        for (QuestionsBlock questionsBlock1 : allQuestionsBlocks){
            if (questionsBlock1.getInterviewId().equals(appointmentId) && questionsBlock1.getUserId().equals(userID))
                return questionsBlock1;
        }
        return null;
    }

    @Override
    public QuestionsBlock getQuestionsBlockFromInterviewByQuestionsBlockId(String questionsBlockId) {
        Set<QuestionsBlock> allQuestionsBlocks = new HashSet<>();
        QuestionsBlock questionsBlock = new QuestionsBlock();
        Set<QuestionInformation> questionInformationsList = new HashSet<>();
        QuestionInformation questionInformation = new QuestionInformation();
        questionInformation.setQuestion("Question body");
        questionInformation.setAnswer("answer");
        questionInformation.setComment("normas");
        questionInformation.setMark(2);
        questionInformation.setWeight(1);
        questionInformation.setInterviewId("1");
        questionInformationsList.add(questionInformation);
        questionsBlock.setQuestions(questionInformationsList);
        questionsBlock.setInterviewId("1");
        questionsBlock.setUserId("1");
        questionsBlock.setId("1");
        allQuestionsBlocks.add(questionsBlock);

        for (QuestionsBlock questionsBlock1 : allQuestionsBlocks){
            if (questionsBlock1.getId().equals(questionsBlockId))
                return questionsBlock1;
        }
        return null;
    }

    @Override
    public String updateQuestionsBlock(QuestionsBlock newQuestionsBlock) {
        List<QuestionsBlock> allQuestionsBlocks = new ArrayList();
        QuestionsBlock questionsBlock = new QuestionsBlock();
        Set<QuestionInformation> questionInformationsList = new HashSet<>();
        QuestionInformation questionInformation = new QuestionInformation();
        questionInformation.setQuestion("Question body");
        questionInformation.setAnswer("answer");
        questionInformation.setComment("normas");
        questionInformation.setMark(2);
        questionInformation.setWeight(1);
        questionInformation.setInterviewId("1");
        questionInformationsList.add(questionInformation);
        questionsBlock.setQuestions(questionInformationsList);
        questionsBlock.setInterviewId("1");
        questionsBlock.setUserId("1");
        questionsBlock.setId("1");
        allQuestionsBlocks.add(questionsBlock);

        for (int i = 0; i < allQuestionsBlocks.size(); i++){
            if (allQuestionsBlocks.get(i).getId().equals(newQuestionsBlock.getId())){
                allQuestionsBlocks.add(i, newQuestionsBlock);
                return newQuestionsBlock.getId();
            }
        }
        return null;
    }

    @Override
    public QuestionsBlock getStandardQuestionsBlockFromInterview(String interviewId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QuestionsBlock getStandardQuestionsBlock() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
