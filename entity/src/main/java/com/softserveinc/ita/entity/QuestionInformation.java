package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 18.06.14
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */
public class QuestionInformation {

    private String QuestionInformationID;
    @Expose
    private String question = "";
    @Expose
    private String answer = "";
    @Expose
    private int mark;
    @Expose
    private String comment = "";
    private int weight; //weight for each question

    public QuestionInformation() {
    }

    public QuestionInformation(String question, int weight) {
        this.question = question;
        this.weight = weight;
    }

    public QuestionInformation(String question, String answer, int mark, String comment, int weight) {
        this.question = question;
        this.answer = answer;
        this.mark = mark;
        this.comment = comment;
        this.weight = weight;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public int getWeight() {
        return weight;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getQuestionInformationID() {
        return QuestionInformationID;
    }

    public void setQuestionInformationID(String questionInformationID) {
        QuestionInformationID = questionInformationID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionInformation)) return false;

        QuestionInformation that = (QuestionInformation) o;

        if (mark != that.mark) return false;
        if (weight != that.weight) return false;
        if (QuestionInformationID != null ? !QuestionInformationID.equals(that.QuestionInformationID) : that.QuestionInformationID != null)
            return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = QuestionInformationID != null ? QuestionInformationID.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + mark;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "QuestionInformation{" +
                "QuestionInformationID='" + QuestionInformationID + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                ", weight=" + weight +
                '}';
    }
}
