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
@Table(name = "Appointments")
public class Interview {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "InterviewId", unique = true)
    @Expose
    private String interviewId;
    @Expose
    private String appointmentId;
    @Expose
    private List<QuestionsBlock> answerBlocks = new ArrayList<>();
    @Expose
    private String finalComment = "";

    private InterviewType type;

    @Expose
    private int totalPoint = 0;

    public Interview() {
    }

    public Interview(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public InterviewType getType() {
        return type;
    }

    public void setType(InterviewType type) {
        this.type = type;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public List<QuestionsBlock> getQuestionsBlocks() {
        return answerBlocks;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setQuestionsBlocks(List<QuestionsBlock> answerBlocks) {
        this.answerBlocks = answerBlocks;
    }

    public String getFinalComment() {
        return finalComment;
    }

    public void setFinalComment(String finalComment) {
        this.finalComment = finalComment;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interview)) return false;

        Interview interview = (Interview) o;

        if (totalPoint != interview.totalPoint) return false;
        if (answerBlocks != null ? !answerBlocks.equals(interview.answerBlocks) : interview.answerBlocks != null)
            return false;
        if (appointmentId != null ? !appointmentId.equals(interview.appointmentId) : interview.appointmentId != null)
            return false;
        if (finalComment != null ? !finalComment.equals(interview.finalComment) : interview.finalComment != null)
            return false;
        if (interviewId != null ? !interviewId.equals(interview.interviewId) : interview.interviewId != null)
            return false;
        if (type != interview.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interviewId != null ? interviewId.hashCode() : 0;
        result = 31 * result + (appointmentId != null ? appointmentId.hashCode() : 0);
        result = 31 * result + (answerBlocks != null ? answerBlocks.hashCode() : 0);
        result = 31 * result + (finalComment != null ? finalComment.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + totalPoint;
        return result;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId='" + interviewId + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", answerBlocks=" + answerBlocks +
                ", finalComment='" + finalComment + '\'' +
                ", type=" + type +
                ", totalPoint=" + totalPoint +
                '}';
    }
}
