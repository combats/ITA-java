package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 18.06.14
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class Question {

    @Expose
    private String QuestionBody;
    @Expose
    private int weight;

    public Question(String questionBody, int weight) {
        QuestionBody = questionBody;
        this.weight = weight;
    }

    public String getQuestionBody() {
        return QuestionBody;
    }

    public int getWeight() {
        return weight;
    }

    public void setQuestionBody(String questionBody) {
        QuestionBody = questionBody;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
