package com.softserveinc.ita.interviewfactory.dao.mock;

import com.softserveinc.ita.dao.QuestionInformationDAO;
import com.softserveinc.ita.entity.QuestionInformation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 15.07.14
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class QuestionInformationDOAMock implements QuestionInformationDAO {

    @Override
    public QuestionInformation getQuestionInformationById(String questionsInformationId) {
        Set<QuestionInformation> questionInformationsList = new HashSet<>();
        QuestionInformation questionInformation = new QuestionInformation();
        questionInformation.setQuestion("Question body");
        questionInformation.setAnswer("answer");
        questionInformation.setComment("normas");
        questionInformation.setMark(2);
        questionInformation.setWeight(1);
        questionInformation.setInterviewId("1");
        questionInformationsList.add(questionInformation);

        for (QuestionInformation questionInformation1 : questionInformationsList){
            if (questionInformation1.getId().equals(questionsInformationId)) return questionInformation1;
        }

        return null;
    }

    @Override
    public String updateQuestionInformation(QuestionInformation newQuestionInformation) {
        List<QuestionInformation> questionInformationsList = new ArrayList();
        QuestionInformation questionInformation = new QuestionInformation();
        questionInformation.setQuestion("Question body");
        questionInformation.setAnswer("answer");
        questionInformation.setComment("normas");
        questionInformation.setMark(2);
        questionInformation.setWeight(1);
        questionInformation.setInterviewId("1");
        questionInformationsList.add(questionInformation);

        for (int i = 0; i < questionInformationsList.size(); i++){
            if (questionInformationsList.get(i).getId().equals(newQuestionInformation.getId()))
                return questionInformationsList.get(i).getId();
        }
        return null;
    }
}
