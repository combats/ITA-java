package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.interviewfactory.dao.mock.QuestionsBlockDAOMock;
import com.softserveinc.ita.interviewfactory.service.questionInformationServices.QuestionsInformationServices;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 15.07.14
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class QuestionInformationServiceTests extends BaseServiceTests {

    @Autowired
    QuestionsInformationServices questionsInformationServices;

    @Test
    public void testAddQuestionInformationAndExpectOk() throws WrongCriteriaException, HttpRequestException {
        QuestionInformation questionInformation = new QuestionInformation();
        questionInformation.setQuestion("Question body");
        questionInformation.setAnswer("answer");
        questionInformation.setComment("normas");
        questionInformation.setMark(2);
        questionInformation.setWeight(1);
        questionInformation.setInterviewId("1");
        questionsInformationServices.addQuestionInformation(questionInformation, "1");
        QuestionsBlock questionsBlock = QuestionsBlockDAOMock.allQuestionsBlocks.get(0);
        Iterator<QuestionInformation> it = questionsBlock.getQuestions().iterator();

        QuestionInformation questionInformation1 = it.next();

        assertEquals(questionInformation1, questionInformation);


    }

}
