package com.softserveinc.ita.integration;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.utils.JsonUtil;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 26.07.14
 * Time: 3:00
 * To change this template use File | Settings | File Templates.
 */
public class InterviewManagerIntegrationTests extends BaseIntegrationTests {

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Test
    public void testPostQuestionAndExpectEqualOne() throws Exception {
        boolean testsPassed = false;
        QuestionInformation questionInformation1 = new QuestionInformation();
        questionInformation1.setQuestion("Question body");
        questionInformation1.setAnswer("answer");
        questionInformation1.setComment("normas");
        questionInformation1.setMark(2);
        questionInformation1.setWeight(1);
        String questionInformationJson = jsonUtil.toJson(questionInformation1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "userId=1");
        headers.add("Cookie", "appointmentId=2");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(questionInformationJson,headers);

        String id = httpRequestExecutor.postNewObject("interviews/interviewing/answer", entity, String.class);

        Interview interview = httpRequestExecutor.getObjectByID("2", Interview.class);
        Set<QuestionsBlock> questionsBlockSet = interview.getQuestionsBlocks();
        Iterator<QuestionsBlock> it = questionsBlockSet.iterator();
        while(it.hasNext()){
            QuestionsBlock questionsBlockActual = it.next();
            Set<QuestionInformation> questionInformationSet = questionsBlockActual.getQuestions();
            Iterator<QuestionInformation> it2 = questionInformationSet.iterator();
            while(it2.hasNext()){
                QuestionInformation questionInformation = it2.next();
                if (questionInformation.getId().equals(id)) {
                    testsPassed = true;
                    return;
                }
            }
        }
        Assert.assertEquals(testsPassed, true);

    }

}
