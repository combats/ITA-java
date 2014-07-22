package com.softserveinc.ita.interviewfactory.factory;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.service.questionsBlockServices.QuestionsBlockServices;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component("INTERVIEW_WITH_STANDARD_QUESTIONS")
public class InterviewWithStandardQuestions implements CreateInterviewStrategy {

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Autowired
    QuestionsBlockServices questionsBlockServices;

    @Override
    public Interview create(String interviewId) throws HttpRequestException {
        Interview interview = new Interview(interviewId);
        Set<QuestionsBlock> allQuestionsBlocks = new HashSet<>();
        QuestionsBlock standardQuestionsBlock = questionsBlockServices.getStandardQuestionsBlock();
        standardQuestionsBlock.setInterviewId(interviewId);

        Set<QuestionInformation> questionInformationSet2 = standardQuestionsBlock.getQuestions();
        for (QuestionInformation aQuestionInformationSet2 : questionInformationSet2) {
            aQuestionInformationSet2.setInterviewId(interviewId);
        }
        standardQuestionsBlock.setQuestions(questionInformationSet2);

        allQuestionsBlocks.add(standardQuestionsBlock);

        Appointment appointment = httpRequestExecutor.getObjectByID(interviewId, Appointment.class);
        List<String> Users = appointment.getUserIdList();

        for (String User : Users) {
            QuestionsBlock userQuestionsBlock = new QuestionsBlock(User);
            Set<QuestionInformation> questionInformationSet = new HashSet<>();
            userQuestionsBlock.setQuestions(questionInformationSet);
            userQuestionsBlock.setInterviewId(interviewId);
            allQuestionsBlocks.add(userQuestionsBlock);
        }
        interview.setQuestionsBlocks(allQuestionsBlocks);
        interview.setType(InterviewType.INTERVIEW_WITH_STANDARD_QUESTIONS);
        interview.setInterviewId(appointment.getAppointmentId());
        return interview;
    }
}
