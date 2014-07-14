package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.QuestionInformation;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public interface QuestionInformationDAO {

    QuestionInformation getQuestionInformationById(String questionsInformationId);
    String updateQuestionInformation(QuestionInformation questionInformation);

}
