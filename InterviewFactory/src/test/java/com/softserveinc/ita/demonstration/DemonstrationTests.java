package com.softserveinc.ita.demonstration;

import com.softserveinc.ita.entity.FinalComment;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.interviewfactory.service.questionInformationServices.QuestionsInformationServices;
import com.softserveinc.ita.interviewfactory.service.questionsBlockServices.QuestionsBlockServices;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 08.07.14
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class DemonstrationTests extends BaseInterviewingTests {

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

    Cookie[] cookies1 = new Cookie[] { new Cookie("userId", "1") };
    Cookie[] cookies2 = new Cookie[] { new Cookie("userId", "2") };
    Cookie[] cookies3 = new Cookie[] { new Cookie("userId", "3") };

    @Test
    public void testAddQuestionInformationAndExpectOk() throws Exception {

        //--------------------------MVC-------------------------------------------------------
        QuestionInformation questionInformation1 = new QuestionInformation();
        questionInformation1.setQuestion("Question body");
        questionInformation1.setAnswer("answer");
        questionInformation1.setComment("normas");
        questionInformation1.setMark(2);
        questionInformation1.setWeight(1);
        questionInformation1.setInterviewId("2");

        String questionInformationJson = jsonUtil.toJson(questionInformation1);

        MvcResult ExpectingObject = mockMvc.perform(
                post("/interviewing/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionInformationJson)
                        .cookie(cookies1)
        )
                .andExpect(status().isAccepted())
                .andReturn();

        String id1 = ExpectingObject.getResponse().getContentAsString();

        MvcResult ExpectingObject2 = mockMvc.perform(
                post("/interviewing/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionInformationJson)
                        .cookie(cookies2)
        )
                .andExpect(status().isAccepted())
                .andReturn();

        String id2 = ExpectingObject2.getResponse().getContentAsString();

        MvcResult ExpectingObject3 = mockMvc.perform(
                post("/interviewing/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(questionInformationJson)
                        .cookie(cookies3)
        )
                .andExpect(status().isAccepted())
                .andReturn();

        String id3 = ExpectingObject3.getResponse().getContentAsString();


        FinalComment finalComment1 = new FinalComment();
        finalComment1.setBonusPoints(3);
        finalComment1.setFinalComment("final comment");
        finalComment1.setInterviewId("2");

        String finalCommentJson = jsonUtil.toJson(finalComment1);

        mockMvc.perform(
                put("/interviewing/final_comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(finalCommentJson)
                        .cookie(cookies1)
        )
                .andExpect(status().isAccepted());

        mockMvc.perform(
                put("/interviewing/final_comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(finalCommentJson)
                        .cookie(cookies2)
        )
                .andExpect(status().isAccepted());

        mockMvc.perform(
                put("/interviewing/final_comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(finalCommentJson)
                        .cookie(cookies3)
        )
                .andExpect(status().isAccepted());

        MvcResult results = mockMvc.perform(
                get("/2/result")
        )
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(results.getResponse().getContentAsString());

        MvcResult interview = mockMvc.perform(
                get("/2")
        )
                .andExpect(status().isFound())
                .andReturn();

        System.out.println(interviewUtilJson.toJson(jsonUtil.fromJson(interview.getResponse().getContentAsString(), Interview.class)));

    }

}
