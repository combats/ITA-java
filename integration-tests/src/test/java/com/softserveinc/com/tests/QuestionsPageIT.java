package com.softserveinc.com.tests;

import com.softserveinc.com.pageobjects.LogInPage;
import com.softserveinc.com.pageobjects.UiQuestionsPage;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuestionsPageIT extends BaseUIIntegrationTest {
    public static final String QUESTION = "HODOR!!!!!";
    public static final String NEW_QUESTION = "HODOR!!! HODOR!!!!! HODOR!!!!!1";
    public static final String MARK = "3";

//    @Test
    public void testAddQuestion() {
        getDrv().get(getSiteBase().toString());

        UiQuestionsPage questionsPage = new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToQuestions()
                .addQuestion(QUESTION, MARK);

        assertTrue(getDrv().getPageSource().contains(QUESTION));

        questionsPage.logOut();
    }

//    @Test
    public void testEditQuestion() {
        getDrv().get(getSiteBase().toString());

        UiQuestionsPage questionsPage = new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToQuestions()
                .editQuestion(QUESTION,
                              NEW_QUESTION);

        assertTrue(getDrv().getPageSource().contains(NEW_QUESTION));

        questionsPage.logOut();
    }

//    @Test
    public void testDeleteQuestion() {
        getDrv().get(getSiteBase().toString());

        UiQuestionsPage questionsPage = new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToQuestions().deleteQuestion(NEW_QUESTION);

        assertTrue(UiQuestionsPage.isAtQuestionsPage(getDrv(), getSiteBase()));
        assertFalse(getDrv().getPageSource().contains(NEW_QUESTION));

        questionsPage.logOut();
    }

    @Test
    public void runTests() {
        testAddQuestion();
        testEditQuestion();
        testDeleteQuestion();
    }
}
