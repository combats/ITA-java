package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QuestionBlocks")
public class QuestionsBlock implements Serializable {

    @Expose
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "questionsBlock_id", unique = true)
    private String Id;

    @Expose
    @Column(name = "UserId")
    String userId;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="interview_id")
    private Interview interview;

    @Expose
    @OneToMany(mappedBy="questionsBlock")
//    @JoinTable(name = "QuestionInformationQuestionBlock", joinColumns = {
//            @JoinColumn(name = "QuestionsBlockId", referencedColumnName = "Id")}, inverseJoinColumns = {
//            @JoinColumn(name = "QuestionInformationId", referencedColumnName = "Id")})
    private List<QuestionInformation> questions;

    @Expose
    @Column(name = "Final_comment")
    private String finalComment;

    @Expose
    @Column(name = "Bonus_points")
    private int bonusPoints;

    public QuestionsBlock() {
    }

    public QuestionsBlock(String userId) {
        this.userId = userId;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionsBlock)) return false;

        QuestionsBlock that = (QuestionsBlock) o;

        if (bonusPoints != that.bonusPoints) return false;
        if (Id != null ? !Id.equals(that.Id) : that.Id != null) return false;
        if (finalComment != null ? !finalComment.equals(that.finalComment) : that.finalComment != null) return false;
        if (interview != null ? !interview.equals(that.interview) : that.interview != null) return false;
        if (questions != null ? !questions.equals(that.questions) : that.questions != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (interview != null ? interview.hashCode() : 0);
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
                ", interview=" + interview +
                ", questions=" + questions +
                ", finalComment='" + finalComment + '\'' +
                ", bonusPoints=" + bonusPoints +
                '}';
    }
}
