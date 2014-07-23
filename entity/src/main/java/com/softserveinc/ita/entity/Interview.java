package com.softserveinc.ita.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Interview")
public class Interview implements Serializable {

    @Id
    @Column(name = "interview_id", unique = true)
    private String interviewId;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = QuestionsBlock.class)
    @Column(name = "InterviewQuestionBlocks")
//    @OneToMany(fetch=FetchType.EAGER, targetEntity=QuestionsBlock.class, cascade=CascadeType.ALL)
//    @JoinColumn(name = "interview_questionsBlockId", referencedColumnName="interview_id")
    private Set<QuestionsBlock> questionsBlocks = new HashSet<>();

    @Column(name = "InterviewType")
    private InterviewType type;

    public Interview() {
    }

    public Interview(String interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public Set<QuestionsBlock> getQuestionsBlocks() {
        return questionsBlocks;
    }

    public InterviewType getType() {
        return type;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public void setQuestionsBlocks(Set<QuestionsBlock> questionsBlocks) {
        this.questionsBlocks = questionsBlocks;
    }

    public void setType(InterviewType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interview)) return false;

        Interview interview = (Interview) o;

        if (interviewId != null ? !interviewId.equals(interview.interviewId) : interview.interviewId != null)
            return false;
        if (questionsBlocks != null ? !questionsBlocks.equals(interview.questionsBlocks) : interview.questionsBlocks != null)
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
                "interviewId='" + interviewId + '\'' +
                ", questionsBlocks=" + questionsBlocks +
                ", type=" + type +
                '}';
    }
}
