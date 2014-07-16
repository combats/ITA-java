package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "QuestionBlocks")
public class QuestionsBlock implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "questionsBlock_id", unique = true)
    private String Id;

    @Column(name = "UserId")
    String userId;

    @Column(name = "InterviewId")
    String interviewId;

    @OneToMany(fetch=FetchType.LAZY, targetEntity=QuestionInformation.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "questionsBlock_questionInformationId", referencedColumnName="questionsBlock_id")
    private Set<QuestionInformation> questions;

    @Column(name = "Final_comment")
    private String finalComment;

    @Column(name = "Bonus_points")
    private int bonusPoints;

    public QuestionsBlock() {
    }

    public QuestionsBlock(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return Id;
    }

    public String getUserId() {
        return userId;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public Set<QuestionInformation> getQuestions() {
        return questions;
    }

    public String getFinalComment() {
        return finalComment;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public void setQuestions(Set<QuestionInformation> questions) {
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
        if (Id != null ? !Id.equals(that.Id) : that.Id != null) return false;
        if (finalComment != null ? !finalComment.equals(that.finalComment) : that.finalComment != null) return false;
        if (interviewId != null ? !interviewId.equals(that.interviewId) : that.interviewId != null) return false;
        if (questions != null ? !questions.equals(that.questions) : that.questions != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (interviewId != null ? interviewId.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (finalComment != null ? finalComment.hashCode() : 0);
        result = 31 * result + bonusPoints;
        return result;
    }

    @Override
    public String toString() {
        return "QuestionsBlock{" +
                "Id='" + Id + '\'' +
                ", userId='" + userId + '\'' +
                ", interviewId='" + interviewId + '\'' +
                ", questions=" + questions +
                ", finalComment='" + finalComment + '\'' +
                ", bonusPoints=" + bonusPoints +
                '}';
    }
}
