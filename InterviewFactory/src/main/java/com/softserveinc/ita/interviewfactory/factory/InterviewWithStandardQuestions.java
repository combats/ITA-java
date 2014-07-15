package com.softserveinc.ita.interviewfactory.factory;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.service.questionsBlockServices.QuestionsBlockServices;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component("InterviewWithStandardQuestions")
public class InterviewWithStandardQuestions implements CreateInterviewStrategy {

    @Qualifier("httpRequestExecutor")
    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Qualifier("questionsBlockService")
    @Autowired
    QuestionsBlockServices questionsBlockServices;

    @Override
    public Interview create(String interviewId) throws HttpRequestException {
        Interview interview = new Interview(interviewId);
        Set<QuestionsBlock> allQuestionsBlocks = new HashSet<>();

        allQuestionsBlocks.add(questionsBlockServices.getStandardQuestionsBlock());

        Appointment appointment = httpRequestExecutor.getObjectByID(interviewId, Appointment.class);
        List<String> Users = appointment.getUserIdList();

        for (int i = 0; i < Users.size(); i++){
            QuestionsBlock userQuestionsBlock = new QuestionsBlock(Users.get(i));
            allQuestionsBlocks.add(userQuestionsBlock);
        }
        interview.setQuestionsBlocks(allQuestionsBlocks);
        interview.setType(InterviewType.InterviewWithStandardQuestions);
        interview.setInterviewId(appointment.getAppointmentId());
        return interview;
    }
}
