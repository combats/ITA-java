package com.softserveinc.ita.interviewfactory.factory;

import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.Interview;
import com.softserveinc.ita.entity.InterviewType;
import com.softserveinc.ita.entity.QuestionsBlock;
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
@Component("InterviewWithoutQuestions")
public class InterviewWithoutQuestions implements CreateInterviewStrategy {

    @Autowired
    UserService userService;

    @Autowired
    AppointmentService appointmentService;

    @Override
    public Interview create(String appointmentId) throws ApppoinmentNotFoundException, InvalidUserIDException {
        Interview interview = new Interview(appointmentId);
        List<QuestionsBlock> allQuestionsBlocks = new ArrayList<QuestionsBlock>();

        List<String> Users = appointmentService.getUsersListByAppointmentId(appointmentId);

        for (int i = 0; i < Users.size(); i++){
            QuestionsBlock userQuestionsBlock = new QuestionsBlock(userService.getUserByID(Users.get(i)));
            allQuestionsBlocks.add(userQuestionsBlock);
        }
        interview.setQuestionsBlocks(allQuestionsBlocks);
        interview.setType(InterviewType.InterviewWithoutQuestions);
        return interview;
    }
}
