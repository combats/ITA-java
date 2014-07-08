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
    private String questionsBlockID;
    @Expose
    private String interviewId;
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

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
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

    public String getQuestionsBlockID() {
        return questionsBlockID;
    }

    public void setQuestionsBlockID(String questionsBlockID) {
        this.questionsBlockID = questionsBlockID;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionsBlock)) return false;

        QuestionsBlock that = (QuestionsBlock) o;

        if (bonusPoints != that.bonusPoints) return false;
        if (questionsBlockID != null ? !questionsBlockID.equals(that.questionsBlockID) : that.questionsBlockID != null)
            return false;
        if (finalComment != null ? !finalComment.equals(that.finalComment) : that.finalComment != null) return false;
        if (questions != null ? !questions.equals(that.questions) : that.questions != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (questionsBlockID != null ? questionsBlockID.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (finalComment != null ? finalComment.hashCode() : 0);
        result = 31 * result + bonusPoints;
        return result;
    }

    @Override
    public String toString() {
        return "QuestionsBlock{" +
                "user=" + user +
                ", QuestionsBlockID='" + questionsBlockID + '\'' +
                ", questions=" + questions +
                ", finalComment='" + finalComment + '\'' +
                ", bonusPoints=" + bonusPoints +
                '}';
    }
}
