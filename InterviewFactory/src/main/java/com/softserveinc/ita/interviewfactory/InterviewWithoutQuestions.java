package com.softserveinc.ita.interviewfactory;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.exceptions.ApppoinmentNotFoundException;

import com.softserveinc.ita.exceptions.InvalidUserIDException;
import com.softserveinc.ita.service.AppointmentService;
import com.softserveinc.ita.service.UserService;
import com.softserveinc.ita.service.mocks.AppointmentServiceMock;
import com.softserveinc.ita.service.mocks.UserServiceMock;

import exceptions.WrongCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 20.06.14
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */
public class InterviewWithoutQuestions extends Interview implements InterviewCreator {

//    @Override
//    public void createInterview() {
//
//        List<QuestionsBlock> allQuestionsBlocks = new ArrayList<QuestionsBlock>();
//
//        List<User> Users = getAppointment().getUsers();
//
//        for (int i = 0; i < Users.size(); i++){
//            QuestionsBlock userQuestionsBlock = new QuestionsBlock(Users.get(i));
//            allQuestionsBlocks.add(userQuestionsBlock);
//        }
//    }

    public InterviewWithoutQuestions(String appointmentId) throws InvalidUserIDException, ApppoinmentNotFoundException{
        super(appointmentId, InterviewType.InterviewWithoutQuestions);
        createInterview();
    }

    //   @Autowired
    UserService userService = new UserServiceMock();

    //   @Autowired
    AppointmentService appointmentService = new AppointmentServiceMock();

    @Override
    public void createInterview() throws InvalidUserIDException, ApppoinmentNotFoundException {
        List<QuestionsBlock> allQuestionsBlocks = new ArrayList<QuestionsBlock>();
        String appointmentId = getAppointmentId();
        List<String> Users = appointmentService.getUsersListByAppointmentId(appointmentId);

        for (int i = 0; i < Users.size(); i++){
            QuestionsBlock userQuestionsBlock = new QuestionsBlock(userService.getUserByID(Users.get(i)));
            allQuestionsBlocks.add(userQuestionsBlock);
        }
    }
}
