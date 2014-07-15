package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.InterviewDAO;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;
import com.softserveinc.ita.interviewfactory.factory.InterviewFactory;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 15.07.14
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class InterviewServiceTests extends BaseServiceTests {

    @Autowired
    InterviewService interviewService;

    @Autowired
    InterviewFactory interviewFactory;

    @Test
    public void testGetInterviewByIdAndExpectEqual() throws HttpRequestException, WrongCriteriaException {

        Interview interviewActual = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
        Interview interviewExpected = interviewService.getInterviewByAppointmentID(interviewActual.getInterviewId());
        assertEquals(interviewActual, interviewExpected);

    }

    @Test
    public void testGetInterviewByIdAndExpectNewInterview() throws HttpRequestException, WrongCriteriaException {

        Interview interviewActual = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("3");
        Interview interviewExpected = interviewService.getInterviewByAppointmentID(interviewActual.getInterviewId());
        assertEquals(interviewActual, interviewExpected);

    }

    @Test
    public void testPutInterviewAndExpectOk() throws HttpRequestException, WrongCriteriaException {
        Interview interviewActual1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
        Interview interviewActual2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithStandardQuestions).create("2");
        Interview interviewActual3 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithUserAndStandardQuestions).create("3");

        String interviewExpected1 = interviewService.putInterview(interviewActual1.getInterviewId(), "InterviewWithoutQuestions");
        String interviewExpected2 = interviewService.putInterview(interviewActual2.getInterviewId(), "InterviewWithStandardQuestions");
        String interviewExpected3 = interviewService.putInterview(interviewActual3.getInterviewId(), "InterviewWithUserAndStandardQuestions");

        assertEquals(interviewActual1.getInterviewId(), interviewExpected1);
        assertEquals(interviewActual2.getInterviewId(), interviewExpected2);
        assertEquals(interviewActual3.getInterviewId(), interviewExpected3);

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPutInterviewAndExpectWrongCriteriaException() throws HttpRequestException, WrongCriteriaException {
        thrown.expect(WrongCriteriaException.class);
        thrown.expectMessage("Type is wrong");
        String interviewExpected1 = interviewService.putInterview("1", "InterviewWithoutQuestionssdsd");
    }

    @Test
    public void testGetInterviewByApplicantIdAndExpectOk() throws HttpRequestException, WrongCriteriaException {

        Interview interviewActual = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
        List<Interview> interviewExpectedList = interviewService.getInterviewByApplicantID("1");
        assertEquals(interviewActual, interviewExpectedList.get(0));

    }

    @Test
    public void testGetAllInterviews() throws HttpRequestException {

        List<Interview> interviewsListActual = new ArrayList<>();
        Interview interview1 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("1");
        Interview interview2 = interviewFactory.getInterviewWithType(InterviewType.InterviewWithoutQuestions).create("2");
        interviewsListActual.add(interview1);
        interviewsListActual.add(interview2);

        List<Interview> interviewsListExpected= interviewService.getAllInterviews();
        assertEquals(interviewsListExpected, interviewsListActual);

    }

}
