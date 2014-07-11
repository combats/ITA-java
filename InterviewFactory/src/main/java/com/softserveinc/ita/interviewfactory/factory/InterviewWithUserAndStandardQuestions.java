package com.softserveinc.ita.interviewfactory.factory;

import com.softserveinc.ita.entity.*;
import com.softserveinc.ita.interviewfactory.service.questionInformationServices.QuestionsInformationServices;
import com.softserveinc.ita.interviewfactory.service.questionsBlocksServices.QuestionsBlockServices;
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
@Component("InterviewWithUserAndStandardQuestions")
public class InterviewWithUserAndStandardQuestions implements CreateInterviewStrategy {

    @Autowired
    HttpRequestExecutor httpRequestExecutor;

    @Autowired
    QuestionsBlockServices questionsBlockServices;

    @Autowired
    QuestionsInformationServices questionsInformationServices;

    @Override
    public Interview create(String interviewId) throws HttpRequestException {
        Interview interview = new Interview(interviewId);
        List<QuestionsBlock> allQuestionsBlocks = new ArrayList<QuestionsBlock>();

        allQuestionsBlocks.add(questionsBlockServices.getStandardQuestionsBlock());

        Appointment appointment = httpRequestExecutor.getObjectByID(interviewId, Appointment.class);
        List<String> Users = appointment.getUserIdList();

        for (int i = 0; i < Users.size(); i++){
            User user = httpRequestExecutor.getObjectByID(Users.get(i), User.class);
            QuestionsBlock userQuestionsBlock = new QuestionsBlock(user);
            List<QuestionInformation> userQuestionInformationList = new ArrayList<QuestionInformation>();
            List<Question> Questions = user.getQuestions();

            for (int j = 0; j < Questions.size(); j++){
                QuestionInformation questionInformation = new QuestionInformation();
                questionInformation.setQuestion(Questions.get(j).getQuestionBody());
                questionInformation.setWeight(Questions.get(j).getWeight());
                questionInformation.setId(String.valueOf(j));//присваиваем айдишку вопросу, убрать когда будет база
                questionInformation.setQuestionsBlockId(Users.get(i));//привсваиваем вопросу айдишку блока, убрать когда будет база
                userQuestionInformationList.add(questionInformation);
            }
            userQuestionsBlock.setQuestions(userQuestionInformationList);
            userQuestionsBlock.setId(Users.get(i));   //присваивает QuestionsBlock айдишку юзера, когда будет база убрать!
            allQuestionsBlocks.add(userQuestionsBlock);
        }
        interview.setQuestionsBlocks(allQuestionsBlocks);
        interview.setType(InterviewType.InterviewWithUserAndStandardQuestions);
        interview.setAppointmentId(appointment.getAppointmentId());
        return interview;
    }
}
