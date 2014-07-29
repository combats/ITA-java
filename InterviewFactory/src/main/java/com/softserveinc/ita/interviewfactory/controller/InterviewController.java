package com.softserveinc.ita.interviewfactory.controller;


import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.service.interviewServices.InterviewService;
import com.softserveinc.ita.interviewfactory.service.questionInformationServices.QuestionsInformationServices;
import com.softserveinc.ita.interviewfactory.service.questionsBlockServices.QuestionsBlockServices;
import com.softserveinc.ita.service.exception.HttpRequestException;
import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "applicants/{applicantId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public List<Interview> getInterviewByApplicantId(@PathVariable("applicantId") String applicantId) throws WrongCriteriaException, HttpRequestException {
        return interviewService.getInterviewByApplicantID(applicantId);
    }

    @RequestMapping(value = "{interviewId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public Interview getInterviewByAppointmentId(@PathVariable("interviewId") String interviewId)
            throws WrongCriteriaException, HttpRequestException {
        return interviewService.getInterviewByAppointmentID(interviewId);
    }

    @RequestMapping(value = "{interviewId}/result", method = RequestMethod.GET, produces = "application/json")
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

    @RequestMapping(value = "update", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInterviewByAppointmentId(@RequestBody Interview interview) throws HttpRequestException {
        interviewService.updateInterview(interview);
    }

    @RequestMapping(value = "interviewing/answer", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public String addQuestionInformation(@RequestBody QuestionInformation questionInformation,
                                         @CookieValue("userId") String userId1,
                                         @CookieValue("appointmentId") String appointmentId1,
                                         @RequestParam String appointmentId,
                                         @RequestParam String userId)
            throws WrongCriteriaException, HttpRequestException {
        String userId3;
        String appointmentId3;
        if (userId1 == null && appointmentId1 == null){
            userId3 = userId;
            appointmentId3 = appointmentId;
        }
        else{
            userId3 = userId1;
            appointmentId3 = appointmentId1;
        }

        questionInformation.setInterviewId(appointmentId);
        interviewService.getInterviewByAppointmentID(appointmentId3);
        questionsInformationServices.addQuestionInformation(questionInformation, userId3);
        return questionsInformationServices.getQuestionInformationIdByQuestionInformationBody(questionInformation, userId3);
    }

    @RequestMapping(value = "interviewing/final_comment", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateFinalCommentInQuestionsBlock(@RequestBody FinalComment finalComment,
                                                   @CookieValue("userId") String userId1,
                                                   @CookieValue("appointmentId") String appointmentId1,
                                                   @RequestParam String appointmentId,
                                                   @RequestParam String userId)
            throws WrongCriteriaException, HttpRequestException {
        String userId3;
        String appointmentId3;
        if (userId1 == null && appointmentId1 == null){
            userId3 = userId;
            appointmentId3 = appointmentId;
        }
        else{
            userId3 = userId1;
            appointmentId3 = appointmentId1;
        }
        finalComment.setInterviewId(appointmentId3);
        interviewService.getInterviewByAppointmentID(appointmentId3);
        questionsBlockServices.updateFinalCommentInQuestionsBlock(finalComment, userId3);
    }

    @RequestMapping(value = "interviewing/answer", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public String updateQuestionInformation(@RequestBody QuestionInformation questionInformation,
                                            @CookieValue("appointmentId") String appointmentId1,
                                            @RequestParam String appointmentId){
        if (appointmentId1 == null) questionInformation.setInterviewId(appointmentId);
        else questionInformation.setInterviewId(appointmentId1);
        questionInformation.setInterviewId(appointmentId1);
        return questionsInformationServices.updateQuestionInformation(questionInformation);
    }

    @RequestMapping(value = "interviewing/answer", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestionInformationById(@RequestBody String questionsInformationId) {

        questionsInformationServices.deleteQuestionInformationById(questionsInformationId);
    }
}

