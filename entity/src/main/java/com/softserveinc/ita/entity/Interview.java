package com.softserveinc.ita.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
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
public class Interview {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "InterviewId", unique = true)
    @Expose
    private String interviewId;
    @Expose
    private List<QuestionsBlock> answerBlocks = new ArrayList<>();

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
        return answerBlocks;
    }

    public String getAppointmentId() {
        return interviewId;
    }

    public void setAppointmentId(String interviewId) {
        this.interviewId = interviewId;
    }

    public void setQuestionsBlocks(List<QuestionsBlock> answerBlocks) {
        this.answerBlocks = answerBlocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interview)) return false;

        Interview interview = (Interview) o;

        if (answerBlocks != null ? !answerBlocks.equals(interview.answerBlocks) : interview.answerBlocks != null)
            return false;
        if (interviewId != null ? !interviewId.equals(interview.interviewId) : interview.interviewId != null)
            return false;
        if (type != interview.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interviewId != null ? interviewId.hashCode() : 0;
        result = 31 * result + (answerBlocks != null ? answerBlocks.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "appointmentId='" + interviewId + '\'' +
                ", answerBlocks=" + answerBlocks +
                ", type=" + type +
                '}';
    }
}
