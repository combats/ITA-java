package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 17.06.14
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "Interview")
public class Interview implements Serializable {

    @Expose
    @Id
    @Column(name = "interview_id", unique = true)
    private String interviewId;

    @Expose
    @OneToMany(mappedBy="interview")
//    @JoinTable(name = "InterviewQuestionBlocks", joinColumns = {
//            @JoinColumn(name = "InterviewId", referencedColumnName = "interviewId")}, inverseJoinColumns = {
//            @JoinColumn(name = "QuestionsBlockId", referencedColumnName = "Id")})
    private List<QuestionsBlock> questionsBlocks;

    @Column(name = "InterviewType")
    private InterviewType type;

    public Interview() {
    }

    public Interview(String interviewId) {
        this.interviewId = interviewId;
    }

    public InterviewType getType() {
        return type;
    }

    public void setType(InterviewType type) {
        this.type = type;
    }

    public List<QuestionsBlock> getQuestionsBlocks() {
        return questionsBlocks;
    }

    public String getAppointmentId() {
        return interviewId;
    }

    public void setAppointmentId(String interviewId) {
        this.interviewId = interviewId;
    }

    public void setQuestionsBlocks(List<QuestionsBlock> answerBlocks) {
        this.questionsBlocks = answerBlocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interview)) return false;

        Interview interview = (Interview) o;

        if (questionsBlocks != null ? !questionsBlocks.equals(interview.questionsBlocks) : interview.questionsBlocks != null)
            return false;
        if (interviewId != null ? !interviewId.equals(interview.interviewId) : interview.interviewId != null)
            return false;
        if (type != interview.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interviewId != null ? interviewId.hashCode() : 0;
        result = 31 * result + (questionsBlocks != null ? questionsBlocks.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "appointmentId='" + interviewId + '\'' +
                ", questionsBlocks=" + questionsBlocks +
                ", type=" + type +
                '}';
    }
}
