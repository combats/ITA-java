package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.*;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 14.07.14
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsBlockDAOTests extends BaseDAOTest {

    @Autowired
    private QuestionsBlockDAO questionsBlockDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private InterviewDAO interviewDAO;

    @Test
    public void testGetQuestionsBlockFromInterviewByQuestionsBlockId()  {

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

        Interview interviewExpected = new Interview();
        allQuestionsBlocks.add(questionsBlock);
        interviewExpected.setInterviewId("1");

        interviewExpected.setQuestionsBlocks(allQuestionsBlocks);
        interviewExpected.setType(InterviewType.InterviewWithoutQuestions);
        String interviewId = (String) sessionFactory.getCurrentSession().save(interviewExpected);
        Interview interviewActual = interviewDAO.getInterviewByAppointmentId(interviewId);
        Set<QuestionsBlock> allQuestionsBlocksActual = interviewActual.getQuestionsBlocks();
        Iterator<QuestionsBlock> it = allQuestionsBlocksActual.iterator();
        QuestionsBlock questionsBlockActual = it.next();
        String questionsBlockId = questionsBlockActual.getId();

        QuestionsBlock questionsBlockExpected = questionsBlockDAO.getQuestionsBlockFromInterviewByQuestionsBlockId(questionsBlockId);
        assertEquals(questionsBlockExpected, questionsBlockActual);

    }

    @Test
    public void testUpdateQuestionsBlockFromInterviewByQuestionsBlockId()  {

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

        Interview interviewExpected = new Interview();
        allQuestionsBlocks.add(questionsBlock);
        interviewExpected.setInterviewId("1");

        interviewExpected.setQuestionsBlocks(allQuestionsBlocks);
        interviewExpected.setType(InterviewType.InterviewWithoutQuestions);
        String interviewId = (String) sessionFactory.getCurrentSession().save(interviewExpected);
        Interview interviewActual = interviewDAO.getInterviewByAppointmentId(interviewId);
        Set<QuestionsBlock> allQuestionsBlocksActual = interviewActual.getQuestionsBlocks();
        Iterator<QuestionsBlock> it = allQuestionsBlocksActual.iterator();
        QuestionsBlock questionsBlockActual = it.next();
        questionsBlockActual.setUserId("3");
        String questionsBlockId = questionsBlockActual.getId();

        String questionsBlockExpectedId = questionsBlockDAO.updateQuestionsBlock(questionsBlockActual);
        assertEquals(questionsBlockExpectedId, questionsBlockId);

    }

    @Test
    public void testGetQuestionsBlockFromInterviewByUserId()  {

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
        questionsBlock.setUserId("2");
        questionsBlock.setInterviewId("1");
        questionsBlock.setBonusPoints(12);
        questionsBlock.setFinalComment("wefwef");

        Interview interviewExpected = new Interview();
        allQuestionsBlocks.add(questionsBlock);
        interviewExpected.setInterviewId("1");

        interviewExpected.setQuestionsBlocks(allQuestionsBlocks);
        interviewExpected.setType(InterviewType.InterviewWithoutQuestions);
        String interviewId = (String) sessionFactory.getCurrentSession().save(interviewExpected);
        Interview interviewActual = interviewDAO.getInterviewByAppointmentId(interviewId);
        Set<QuestionsBlock> allQuestionsBlocksActual = interviewActual.getQuestionsBlocks();
        Iterator<QuestionsBlock> it = allQuestionsBlocksActual.iterator();
        QuestionsBlock questionsBlockActual = it.next();
        String questionsBlockUserId = questionsBlockActual.getUserId();

        QuestionsBlock questionsBlockExpected = questionsBlockDAO.getQuestionsBlockByInterviewIdAndUserId(questionsBlockUserId, interviewId);
        assertEquals(questionsBlockExpected, questionsBlockActual);

    }

}