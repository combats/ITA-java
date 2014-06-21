package com.softserveinc.ita.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 17.06.14
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBlock {

    @Expose
    User user;
    @Expose
    private List<QuestionInformation> questions = new ArrayList<>();
    @Expose
    private String finalComment = "";
    @Expose
    private int bonusPoints;

    public QuestionsBlock() {
    }

    public QuestionsBlock(User user) {
        this.user = user;
    }

    public List<QuestionInformation> getQuestions() {
        return questions;
    }

    public String getFinalComment() {
        return finalComment;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setQuestions(List<QuestionInformation> questions) {
        this.questions = questions;
    }

    public void setFinalComment(String finalComment) {
        this.finalComment = finalComment;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionsBlock)) return false;

        QuestionsBlock that = (QuestionsBlock) o;

        if (bonusPoints != that.bonusPoints) return false;
        if (finalComment != null ? !finalComment.equals(that.finalComment) : that.finalComment != null) return false;
        if (questions != null ? !questions.equals(that.questions) : that.questions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questions != null ? questions.hashCode() : 0;
        result = 31 * result + (finalComment != null ? finalComment.hashCode() : 0);
        result = 31 * result + bonusPoints;
        return result;
    }

    @Override
    public String toString() {
        return "QuestionsBlock{" +
                "questions=" + questions +
                ", finalComment='" + finalComment + '\'' +
                ", bonusPoints=" + bonusPoints +
                '}';
    }
}
