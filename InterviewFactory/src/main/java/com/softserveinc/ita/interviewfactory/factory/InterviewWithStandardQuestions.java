package com.softserveinc.ita.interviewfactory.factory;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;

import com.softserveinc.ita.exceptions.InvalidUserIDException;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.service.UserService;
import com.softserveinc.ita.service.mocks.AppointmentServiceMock;
import com.softserveinc.ita.service.mocks.UserServiceMock;
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
@Component("InterviewWithStandardQuestions")
public class InterviewWithStandardQuestions implements CreateInterviewStrategy {

    @Autowired
    UserService userService;

    @Autowired
    AppointmentService appointmentService;

    @Override
    public Interview create(String appointmentId) throws ApppoinmentNotFoundException, InvalidUserIDException {
        Interview interview = new Interview(appointmentId);
        List<QuestionsBlock> allQuestionsBlocks = new ArrayList<QuestionsBlock>();

        QuestionInformation questionInformation1 = new QuestionInformation();
        questionInformation1.setQuestion("What is your name?");
        questionInformation1.setWeight(2);
        QuestionInformation questionInformation2 = new QuestionInformation();
        questionInformation2.setQuestion("How are you?");
        questionInformation2.setWeight(3);

        List<QuestionInformation> QuestionInformationList = new ArrayList<QuestionInformation>();
        QuestionInformationList.add(questionInformation1);
        QuestionInformationList.add(questionInformation2);

        QuestionsBlock standardQuestionsBlock = new QuestionsBlock();
        standardQuestionsBlock.setQuestions(QuestionInformationList);

        allQuestionsBlocks.add(standardQuestionsBlock);

        List<String> Users = appointmentService.getUsersListByAppointmentId(appointmentId);

        for (int i = 0; i < Users.size(); i++){
            QuestionsBlock userQuestionsBlock = new QuestionsBlock(userService.getUserByID(Users.get(i)));
            allQuestionsBlocks.add(userQuestionsBlock);
        }
        interview.setQuestionsBlocks(allQuestionsBlocks);
        interview.setType(InterviewType.InterviewWithStandardQuestions);
        return interview;
    }
}
