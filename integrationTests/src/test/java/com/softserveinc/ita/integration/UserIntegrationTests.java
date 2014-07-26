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
 * Time: 2:59
 * To change this template use File | Settings | File Templates.
 */
public class UserIntegrationTests extends BaseIntegrationTests {

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Test
    public void testPostNewUserAndExpectEqualOne() throws Exception {
        User testUser = new User("Vasya", "Pupkin");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(jsonUtil.toJson(testUser),headers);
        User user = httpRequestExecutor.postNewObject("users/", entity, User.class);
        String userId = user.getId();
        User userExpected = httpRequestExecutor.getObjectByID(userId, User.class);
        testUser.setId(userId);
        List<Question> questionList = new ArrayList<>();
        testUser.setQuestions(questionList);
        Assert.assertEquals(testUser, userExpected);
    }

    @Test
    public void testPostNewUserWithQuestionsAndExpectEqualOne() throws Exception {
        User testUser = new User("Vasya", "Pupkin");
        Question question = new Question();
        question.setQuestionBody("questionBody");
        question.setWeight(2);
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        testUser.setQuestions(questionList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(jsonUtil.toJson(testUser),headers);
        User user = httpRequestExecutor.postNewObject("users/", entity, User.class);
        String userId = user.getId();
        User userExpected = httpRequestExecutor.getObjectByID(userId, User.class);
        testUser.setId(userId);
        Assert.assertEquals(testUser, userExpected);
    }

}
