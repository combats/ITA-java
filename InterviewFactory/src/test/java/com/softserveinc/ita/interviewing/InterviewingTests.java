package com.softserveinc.ita.interviewing;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.dao.mock.InterviewDAOMock;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.interviewfactory.service.questionInformationServices.QuestionsInformationServices;
import com.softserveinc.ita.interviewfactory.service.questionsBlockServices.QuestionsBlockServices;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import exceptions.WrongCriteriaException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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

    @Autowired
    private JsonUtil jsonUtil;

    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    Cookie[] cookies = new Cookie[] { new Cookie("userId", "1") };

    @Test
    public void testAddQuestionInformationAndExpectOk() throws Exception {

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
        System.out.println(questionsInformationServices.getQuestionInformationById(id));

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

        System.out.println(interviewUtilJson.toJson(questionsInformationServices.getQuestionInformationById(id)));

        questionsInformationServices.deleteQuestionInformationById(id);

        QuestionsBlock questionsBlock = new QuestionsBlock();
        questionsBlock.setInterviewId("1");
        questionsBlock.setUserId("4");
        questionsBlock.setFinalComment("new questions block");

        questionsBlockServices.addQuestionsBlock(questionsBlock);
        String id2 = questionsBlockServices.getQuestionsBlockIdByQuestionsBlockBody(questionsBlock, "4");
        System.out.println(interviewUtilJson.toJson(questionsBlockServices.getQuestionsBlockByQuestionsBlockId(id2)));

        questionsBlock.setFinalComment("new questions block comment");
        questionsBlock.setId(id2);
        questionsBlockServices.updateQuestionsBlock(questionsBlock, "4");
        questionsBlockServices.deleteQuestionsBlockById(id2);

        QuestionInformation questionInformation3 = new QuestionInformation();
        questionInformation3.setQuestion("Question Body1");
        questionInformation3.setAnswer("Answer");
        questionInformation3.setInterviewId("2");
        questionInformation3.setComment("norm");
        questionInformation3.setMark(3);
        questionInformation3.setWeight(1);

        questionsInformationServices.addQuestionInformation(questionInformation3, "2");

        Interview interview1 = interviewService.getInterviewByAppointmentID("2");
        System.out.println(interviewUtilJson.toJson(interview1));

        System.out.println(interviewService.getAllInterviewsId());
        System.out.println(interviewUtilJson.toJson(interviewService.getAllInterviews()));

        System.out.println(interviewUtilJson.toJson(interviewService.getInterviewByApplicantID("1")));

        interviewService.removeInterviewByAppointmentId("1");
        interviewService.removeInterviewByAppointmentId("2");
        //--------------------------MVC-------------------------------------------------------
        QuestionInformation questionInformation4 = new QuestionInformation();
        questionInformation4.setQuestion("Question body");
        questionInformation4.setAnswer("answer");
        questionInformation4.setComment("normas");
        questionInformation4.setMark(2);
        questionInformation4.setWeight(1);
        questionInformation4.setInterviewId("1");

        String questionInformationJson = jsonUtil.toJson(questionInformation4);

        MvcResult ExpectingObject = mockMvc.perform(
                post("/interviewing/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionInformationJson)
                        .cookie(cookies)
        )
                .andExpect(status().isAccepted())
                .andReturn();

        String id3 = ExpectingObject.getResponse().getContentAsString();

        String questionInformationJson3 = jsonUtil.toJson(questionInformation4);

        mockMvc.perform(
                post("/interviewing/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionInformationJson3)
                        .cookie(cookies)
        )
                .andExpect(status().isAccepted())
                .andReturn();


        FinalComment finalComment2 = new FinalComment();
        finalComment2.setBonusPoints(3);
        finalComment2.setFinalComment("final comment");
        finalComment2.setInterviewId("1");

        String finalCommentJson = jsonUtil.toJson(finalComment2);

        mockMvc.perform(
                put("/interviewing/final_comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(finalCommentJson)
                        .cookie(cookies)
        )
                .andExpect(status().isAccepted());



        QuestionInformation questionInformation5 = new QuestionInformation();
        questionInformation5.setQuestion("Question body corrected");
        questionInformation5.setAnswer("answer");
        questionInformation5.setComment("normas");
        questionInformation5.setMark(2);
        questionInformation5.setWeight(1);
        questionInformation5.setInterviewId("1");
        questionInformation5.setId(id3);

        String questionInformationJson2 = jsonUtil.toJson(questionInformation5);

        mockMvc.perform(
                put("/interviewing/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionInformationJson2)
        )
                .andExpect(status().isAccepted());


        mockMvc.perform(
                delete("/interviewing/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(id3)
        )
                .andExpect(status().isOk());

    }

}
