package com.softserveinc.ita.interviewfactory.controller;


import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.interviewfactory.service.questionInformationServices.QuestionsInformationServices;
import com.softserveinc.ita.interviewfactory.service.questionsBlockServices.QuestionsBlockServices;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class InterviewController {

    @Autowired
    InterviewService interviewService;
    @Autowired
    QuestionsBlockServices questionsBlockServices;
    @Autowired
    QuestionsInformationServices questionsInformationServices;

    @RequestMapping(value = "applicants/{applicantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public List<Interview> getInterviewByApplicantId(@PathVariable("applicantId") String applicantId) throws WrongCriteriaException, HttpRequestException {
        return interviewService.getInterviewByApplicantID(applicantId);
    }

    @RequestMapping(value = "{interviewId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public Interview getInterviewByAppointmentId(@PathVariable("interviewId") String interviewId)
            throws WrongCriteriaException, HttpRequestException {
        return interviewService.getInterviewByAppointmentID(interviewId);
    }

    @RequestMapping(value = "{interviewId}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public InterviewResults getInterviewResultsByInterviewId(@PathVariable("interviewId") String interviewId)
            throws WrongCriteriaException, HttpRequestException {
        return interviewService.getInterviewResultsByInterviewId(interviewId);
    }

    @RequestMapping(value = "{interviewId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeInterviewByAppointmentId(@PathVariable("interviewId") String interviewId) throws HttpRequestException {
        interviewService.removeInterviewByAppointmentId(interviewId);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInterviewByAppointmentId(@RequestBody Interview interview) throws HttpRequestException {
        interviewService.updateInterview(interview);
    }

    @RequestMapping(value = "interviewing/answer", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public String addQuestionInformation(@RequestBody QuestionInformation questionInformation,
                                         @CookieValue(value = "userId", defaultValue = "1") String userId,
                                         @CookieValue(value = "appointmentId", defaultValue = "2") String appointmentId)
            throws WrongCriteriaException, HttpRequestException {
        questionInformation.setInterviewId(appointmentId);
        questionsInformationServices.addQuestionInformation(questionInformation, userId);
        return questionsInformationServices.getQuestionInformationIdByQuestionInformationBody(questionInformation, userId);
    }

    @RequestMapping(value = "interviewing/final_comment", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateFinalCommentInQuestionsBlock(@RequestBody FinalComment finalComment,
                                                   @CookieValue(value = "userId", defaultValue = "1") String userId,
                                                   @CookieValue(value = "appointmentId", defaultValue = "2") String appointmentId) throws WrongCriteriaException, HttpRequestException {
        finalComment.setInterviewId(appointmentId);
        questionsBlockServices.updateFinalCommentInQuestionsBlock(finalComment, userId);
    }

    @RequestMapping(value = "interviewing/answer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public String updateQuestionInformation(@RequestBody QuestionInformation questionInformation,
                                            @CookieValue(value = "appointmentId", defaultValue = "2") String appointmentId){
        questionInformation.setInterviewId(appointmentId);
        return questionsInformationServices.updateQuestionInformation(questionInformation);
    }

    @RequestMapping(value = "interviewing/answer/{questionId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestionInformationById(@PathVariable("questionId") String questionsInformationId) {

        questionsInformationServices.deleteQuestionInformationById(questionsInformationId);
    }
}

