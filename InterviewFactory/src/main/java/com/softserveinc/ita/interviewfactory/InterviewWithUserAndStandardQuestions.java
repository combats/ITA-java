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
public class InterviewWithUserAndStandardQuestions extends Interview implements InterviewCreator {

//    @Override
//    public void createInterview() {
//
//        QuestionInformation questionInformation1 = new QuestionInformation();
//        questionInformation1.setQuestion("What is your name?");
//        questionInformation1.setWeight(2);
//        QuestionInformation questionInformation2 = new QuestionInformation();
//        questionInformation2.setQuestion("How are you?");
//        questionInformation2.setWeight(3);
//
//        List<QuestionInformation> standardQuestionInformationList = new ArrayList<QuestionInformation>();
//        standardQuestionInformationList.add(questionInformation1);
//        standardQuestionInformationList.add(questionInformation2);
//
//        QuestionsBlock standardQuestionsBlock = new QuestionsBlock();
//        standardQuestionsBlock.setQuestions(standardQuestionInformationList);
//
//        List<QuestionsBlock> allQuestionsBlocks = new ArrayList<QuestionsBlock>();
//        allQuestionsBlocks.add(standardQuestionsBlock);
//
//        List<User> Users = getAppointment().getUsers();
//
//        for (int i = 0; i < Users.size(); i++){
//            QuestionsBlock userQuestionsBlock = new QuestionsBlock(Users.get(i));
//            List<QuestionInformation> userQuestionInformationList = new ArrayList<QuestionInformation>();
//            List<Question> Questions = Users.get(i).getQuestions();
//
//            for (Question question : Questions){
//                QuestionInformation questionInformation = new QuestionInformation();
//                questionInformation.setQuestion(question.getQuestionBody());
//                questionInformation.setWeight(question.getWeight());
//                userQuestionInformationList.add(questionInformation);
//            }
//            userQuestionsBlock.setQuestions(userQuestionInformationList);
//            allQuestionsBlocks.add(userQuestionsBlock);
//        }
//        setAnswerBlocks(allQuestionsBlocks);
//    }

 //   @Autowired
    UserService userService = new UserServiceMock();

 //   @Autowired
    AppointmentService appointmentService = new AppointmentServiceMock();

    public InterviewWithUserAndStandardQuestions(String appointmentId)
            throws InvalidUserIDException, ApppoinmentNotFoundException {
        super(appointmentId, InterviewType.InterviewWithUserAndStandardQuestions);
        createInterview();
    }

    @Override
    public void createInterview() throws InvalidUserIDException, ApppoinmentNotFoundException {
        QuestionInformation questionInformation1 = new QuestionInformation();
        questionInformation1.setQuestion("What is your name?");
        questionInformation1.setWeight(2);
        QuestionInformation questionInformation2 = new QuestionInformation();
        questionInformation2.setQuestion("How are you?");
        questionInformation2.setWeight(3);

        List<QuestionInformation> standardQuestionInformationList = new ArrayList<QuestionInformation>();
        standardQuestionInformationList.add(questionInformation1);
        standardQuestionInformationList.add(questionInformation2);

        QuestionsBlock standardQuestionsBlock = new QuestionsBlock();
        standardQuestionsBlock.setQuestions(standardQuestionInformationList);

        List<QuestionsBlock> allQuestionsBlocks = new ArrayList<QuestionsBlock>();
        allQuestionsBlocks.add(standardQuestionsBlock);

        String appointmentId = getAppointmentId();

        List<String> Users = appointmentService.getUsersListByAppointmentId(appointmentId);

        for (int i = 0; i < Users.size(); i++){
            QuestionsBlock userQuestionsBlock = new QuestionsBlock(userService.getUserByID(Users.get(i)));
            List<QuestionInformation> userQuestionInformationList = new ArrayList<QuestionInformation>();
            List<Question> Questions = userService.getUserByID(Users.get(i)).getQuestions();

            for (Question question : Questions){
                QuestionInformation questionInformation = new QuestionInformation();
                questionInformation.setQuestion(question.getQuestionBody());
                questionInformation.setWeight(question.getWeight());
                userQuestionInformationList.add(questionInformation);
            }
            userQuestionsBlock.setQuestions(userQuestionInformationList);
            allQuestionsBlocks.add(userQuestionsBlock);
        }
        setAnswerBlocks(allQuestionsBlocks);
    }
}
