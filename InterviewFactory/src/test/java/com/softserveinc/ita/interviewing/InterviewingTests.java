package com.softserveinc.ita.interviewing;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.interviewfactory.service.questionInformationServices.QuestionsInformationServices;
import com.softserveinc.ita.interviewfactory.service.questionsBlockServices.QuestionsBlockServices;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import exceptions.WrongCriteriaException;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 08.07.14
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class InterviewingTests extends BaseInterviewingTests {

    @Autowired
    InterviewFactory interviewFactory;

    @Autowired
    private JsonUtil interviewUtilJson;


    @Autowired
    private QuestionsBlockServices questionsBlockServices;

    @Autowired
    private QuestionsInformationServices questionsInformationServices;

    @Autowired
    InterviewService interviewService;



//    @Test
//    public void testPutInterviewAndExpectOk() throws HttpRequestException, WrongCriteriaException {
//
//        interviewService.putInterview("1", InterviewType.INTERVIEW_WITHOUT_QUESTIONS);
//        interviewService.putInterview("2", InterviewType.INTERVIEW_WITH_STANDARD_QUESTIONS);
//        interviewService.putInterview("3", InterviewType.INTERVIEW_WITH_USER_AND_STANDARD_QUESTIONS);
//
//    }

    @Test

    public void testAddQuestionInformationAndExpectOk() throws WrongCriteriaException, HttpRequestException {

        QuestionInformation questionInformation = new QuestionInformation();
        questionInformation.setQuestion("Question Body1");
        questionInformation.setAnswer("Answer");
        questionInformation.setInterviewId("1");
        questionInformation.setComment("norm");
        questionInformation.setMark(3);
        questionInformation.setWeight(1);

        questionsInformationServices.addQuestionInformation(questionInformation, "1");

        String id = questionsInformationServices.getQuestionInformationIdByQuestionInformationBody(questionInformation, "1");

        System.out.println(id);

        QuestionInformation questionInformation2 = new QuestionInformation();
        questionInformation2.setQuestion("Question Body1 Corrected");
        questionInformation2.setAnswer("Answer");
        questionInformation2.setInterviewId("1");
        questionInformation2.setComment("norm");
        questionInformation2.setMark(3);
        questionInformation2.setWeight(1);
        questionInformation2.setId(id);

         questionsInformationServices.updateQuestionInformation(questionInformation2);

        FinalComment finalComment = new FinalComment("final comment", 5, "1");
        questionsBlockServices.updateFinalCommentInQuestionsBlock(finalComment, "1");

        questionsInformationServices.deleteQuestionInformationById(id);


//        Interview interview1 = interviewService.getInterviewByAppointmentID("1");
//        System.out.println(interviewUtilJson.toJson(interview1));

    }

    @Test
    public void testGetInterviewAndPrint() throws HttpRequestException, WrongCriteriaException {
        QuestionsBlock questionsBlock2 = questionsBlockServices.getQuestionsBlockByQuestionsBlockId("8a84b07247449ea40147449ee5b90000");
        System.out.println(interviewUtilJson.toJson(questionsBlock2));

    }



}
