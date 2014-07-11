package com.softserveinc.ita.interviewfactory.factory;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 26.06.14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
@Component("InterviewWithoutQuestions")
public class InterviewWithoutQuestions implements CreateInterviewStrategy {

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Override
    public Interview create(String interviewId) throws HttpRequestException {

        Interview interview = new Interview(interviewId);
        List<QuestionsBlock> allQuestionsBlocks = new ArrayList<QuestionsBlock>();
        Appointment appointment = httpRequestExecutor.getObjectByID(interviewId, Appointment.class);

        List<String> Users = appointment.getUserIdList();

        for (int i = 0; i < Users.size(); i++){
            QuestionsBlock userQuestionsBlock = new QuestionsBlock(httpRequestExecutor.getObjectByID(Users.get(i), User.class));
            userQuestionsBlock.setId(Users.get(i));   //присваивает QuestionsBlock айдишку юзера, когда будет база убрать!
            allQuestionsBlocks.add(userQuestionsBlock);
        }
        interview.setQuestionsBlocks(allQuestionsBlocks);
        interview.setType(InterviewType.InterviewWithoutQuestions);
        interview.setAppointmentId(appointment.getAppointmentId());
        return interview;
    }
}
